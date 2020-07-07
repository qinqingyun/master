package com.meituan.food.extract.impl;

import com.meituan.food.mapper.McdCoePOMapper;
import com.meituan.food.mapper.McdCoeTodoPOMapper;
import com.meituan.food.mapper.OrgDaxiangPOMapper;
import com.meituan.food.mapper.OrgMcdIdPOMapper;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoeTodoPO;
import com.meituan.food.po.McdCoeTodoPOExample;
import com.meituan.food.po.OrgMcdIdPO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class AllDDCoePushEctracter {

    @Resource
    public McdCoePOMapper mcdCoePOMapper;

    @Resource
    public DDCoeDataPushExtracter ddCoeDataPushExtracter;

    @Resource
    public McdCoeTodoPOMapper mcdCoeTodoPOMapper;

    @Resource
    public OrgDaxiangPOMapper orgDaxiangPOMapper;

    @Resource
    public OrgMcdIdPOMapper orgMcdIdPOMapper;

    public void pushData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        LocalDate firstDay=LocalDate.now().minusDays(30);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date firstDate = sdf.parse(firstDayStr +" 00:00:00");

        LocalDate secondDay=LocalDate.now().minusDays(1);
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date secondDate = sdf.parse(secondDayStr +" 23:59:59");
        Map<String,String> orgCoeContext=new HashMap<>();
        Map<Long,String> daxiangPushMap=new HashMap<>();

        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(firstDate, secondDate);

        getCoeContext(orgCoeContext,mcdCoePOList);

        List<Long> daxiangIdList = orgDaxiangPOMapper.selectAllDaxiangId();
        for (Long daxiangId : daxiangIdList) {

            String context="";

            List<Integer> orgIdList = orgDaxiangPOMapper.selectByDaxiangId(daxiangId);
            for (int integer : orgIdList) {
                OrgMcdIdPO orgMcdIdPO = orgMcdIdPOMapper.selectByOrgId(integer);
                String orgName = orgMcdIdPO.getOrgName();
                String orgContext=orgCoeContext.get(orgName);
                if (orgContext!=null){
                    context=context+"\n"+orgCoeContext.get(orgName);
                }
            }
            if (!context.equals("")){
                if (!daxiangPushMap.keySet().contains(daxiangId)){
                    daxiangPushMap.put(daxiangId,context);
                }else {
                    daxiangPushMap.put(daxiangId,daxiangPushMap.get(daxiangId)+"\n"+context);
                }
            }
        }

        for(Long key:daxiangPushMap.keySet()){
            DaXiangUtils.pushToPerson("群ID："+key+"\n您关注的组织架构在"+firstDayStr+"~"+secondDayStr+"期间的COE情况如下：\n"+daxiangPushMap.get(key),"guomengyao");
        }
    }

    public Map<String,String> getCoeContext(Map<String,String> orgCoeContext,List<McdCoePO> mcdCoePOList) throws ParseException {
        for (McdCoePO mcdCoePO : mcdCoePOList) {
            String context="△【["+mcdCoePO.getBrief()+"|"+mcdCoePO.getCoeLink()+"]】";
            if (mcdCoePO.getCreateTime()!=null&&mcdCoePO.getFindTime()!=null){
                int dayCount = daysBetween(mcdCoePO.getCreateTime(), mcdCoePO.getFindTime());
                if (dayCount<=1){
                    context=context+"\n● COE创建很及时，点赞哦";
                }else {
                    context=context+"\n● COE创建时效不及时（需在问题发现后1天内创建）";
                }
            }else {
                context=context+"\n● COE创建时效未知";
            }
            Map<String, String> pushList=new HashMap<>();
            ddCoeDataPushExtracter.getPushContext(pushList,mcdCoePO,0,context);
            if (pushList.size()!=0){
                int secondDayCount=daysBetween(new Date(),mcdCoePO.getCreateTime());
                if (secondDayCount>7){
                    context=context+"\n● COE仍有内容未完善，请及时处理!";
                }else {
                    context=context+"\n● COE未在7天内完善信息，请及时处理!";
                }

            }else {
                context=context+"\n● COE信息已完善，复盘";
            }

            McdCoeTodoPOExample example=new McdCoeTodoPOExample();
            example.createCriteria().andCoeIdEqualTo(mcdCoePO.getCoeId());
            List<McdCoeTodoPO> mcdCoeTodoPOS = mcdCoeTodoPOMapper.selectByExample(example);
            int todoCount = mcdCoeTodoPOS.size();
            int unfinishCount=0;
            String context2="";
            if (todoCount!=0){
                for (McdCoeTodoPO mcdCoeTodoPO : mcdCoeTodoPOS) {
                    if (mcdCoeTodoPO.getIsDelay()==true){
                        unfinishCount++;
                        context2=context2 +"\n● 逾期TODO:"+mcdCoeTodoPO.getOnesLink();
                    }else if (mcdCoeTodoPO.getIsFinish()==false){
                        unfinishCount++;
                    }
                }
                context=context+"\n● 未完成|总数="+unfinishCount+"|"+todoCount+context2+"\n";
            }else {
                context=context+"\n● 该COE无TODO信息\n";
            }

            String orgName="美团/到店事业群/平台技术部/" + mcdCoePO.getOrgName();
            if (!orgCoeContext.keySet().contains(orgName)){
                orgCoeContext.put(orgName,context);
            }else {
                orgCoeContext.put(orgName,orgCoeContext.get(orgName)+"\n"+context+"\n");
            }
        }

        return orgCoeContext;
    }

    public  int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
}