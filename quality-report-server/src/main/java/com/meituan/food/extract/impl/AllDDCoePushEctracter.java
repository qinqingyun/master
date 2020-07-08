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
import org.apache.lucene.analysis.sinks.TeeSinkTokenFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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
        Map<String,CoePushDataVO> orgCoeContext=new HashMap<>();
        Map<Long,CoePushDataVO> daxiangPushMap=new HashMap<>();

        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(firstDate, secondDate);

        getOrgCoeContext(orgCoeContext,mcdCoePOList);

        List<Long> daxiangIdList = orgDaxiangPOMapper.selectAllDaxiangId();
        for (Long daxiangId : daxiangIdList) {
/*
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
            }*/

            CoePushDataVO daxiangPushDataVO=new CoePushDataVO();
            daxiangPushDataVO.newVO();
            List<Integer> orgIdList = orgDaxiangPOMapper.selectByDaxiangId(daxiangId);
            for (int integer : orgIdList) {
                OrgMcdIdPO orgMcdIdPO = orgMcdIdPOMapper.selectByOrgId(integer);
                String orgName = orgMcdIdPO.getOrgName();
                CoePushDataVO coePushDataVO = orgCoeContext.get(orgName);
                if (coePushDataVO!=null){
                    daxiangPushDataVO.setS1Conut(daxiangPushDataVO.getS1Conut()+coePushDataVO.getS1Conut());
                    daxiangPushDataVO.setS2Conut(daxiangPushDataVO.getS2Conut()+coePushDataVO.getS2Conut());
                    daxiangPushDataVO.setS3Conut(daxiangPushDataVO.getS3Conut()+coePushDataVO.getS3Conut());
                    daxiangPushDataVO.setS4Count(daxiangPushDataVO.getS4Count()+coePushDataVO.getS4Count());
                    daxiangPushDataVO.setS9Count(daxiangPushDataVO.getS9Count()+coePushDataVO.getS9Count());
                    daxiangPushDataVO.setOtherCount(daxiangPushDataVO.getOtherCount()+coePushDataVO.getOtherCount());
                    daxiangPushDataVO.setAllCount(daxiangPushDataVO.getAllCount()+coePushDataVO.getAllCount());
                    daxiangPushDataVO.setIncompleteCount(daxiangPushDataVO.getIncompleteCount()+coePushDataVO.getIncompleteCount());
                    daxiangPushDataVO.setOverdueTodoCount(daxiangPushDataVO.getOverdueTodoCount()+coePushDataVO.getOverdueTodoCount());
                    daxiangPushDataVO.setCoeMessage(daxiangPushDataVO.getCoeMessage()+coePushDataVO.getCoeMessage());
                    daxiangPushDataVO.setIncompleteMessage(daxiangPushDataVO.getIncompleteMessage()+coePushDataVO.getIncompleteMessage());
                    daxiangPushDataVO.setOverdueTodo(daxiangPushDataVO.getOverdueTodo()+coePushDataVO.getOverdueTodo());

                }
            }

            if (daxiangPushDataVO.getAllCount()!=0){
                daxiangPushMap.put(daxiangId,daxiangPushDataVO);
            }
        }

        for(Long key:daxiangPushMap.keySet()){
            CoePushDataVO vo = daxiangPushMap.get(key);
            String text="共新增"+vo.getAllCount()+"个COE，其中S1-"+vo.getS1Conut()+"个，S2-"+vo.getS2Conut()+"个，S3-"+vo.getS3Conut()+"个，S4-"+vo.getS4Count()+"个，S9-"+vo.getS9Count()+"个，其他-"+vo.getOtherCount()+"个\n\n";
            text=text+vo.getCoeMessage()+"\n截止目前COE的SOP落地情况如下：\n";
            if (vo.getIncompleteCount()!=0){
                text=text+"问题发生1周内未完善的COE共"+vo.getIncompleteCount()+"个，请及时完善，明细如下:\n"+vo.getIncompleteMessage();
            }else {
                text=text+"不存在未完善的COE，为你的团队点赞哦👍";
            }
            DaXiangUtils.pushToPerson("群ID："+key+"\n您关注的组织架构在"+firstDayStr+"~"+secondDayStr+"期间的COE情况如下：\n"+text,"guomengyao");
        }
    }

    public Map<String,CoePushDataVO> getOrgCoeContext(Map<String,CoePushDataVO> orgCoeContext,List<McdCoePO> mcdCoePOList) throws ParseException {

        for (McdCoePO mcdCoePO : mcdCoePOList) {
            String orgName="美团/到店事业群/平台技术部/" + mcdCoePO.getOrgName();
            if (!orgCoeContext.keySet().contains(orgName)){
                CoePushDataVO coePushDataVO=new CoePushDataVO();
                coePushDataVO.newVO();
                coeContext(mcdCoePO,coePushDataVO);
                coePushDataVO.setCoeMessage(orgName+"\n"+coePushDataVO.getCoeMessage());
                orgCoeContext.put(orgName,coePushDataVO);
            }else{
                coeContext(mcdCoePO,orgCoeContext.get(orgName));
                orgCoeContext.put(orgName,orgCoeContext.get(orgName));

            }
        }

        return orgCoeContext;

    }

    public void coeContext(McdCoePO mcdCoePO,CoePushDataVO coePushDataVO)throws ParseException {

        //各等级个数
        String level=mcdCoePO.getLevel();
        if (level!=null&&(!" ".equals(level))){
            if (level.equals("S1")){
                coePushDataVO.setS1Conut(coePushDataVO.getS1Conut()+1);
            }else if (level.equals("S2")){
                coePushDataVO.setS2Conut(coePushDataVO.getS2Conut()+1);
            }else if (level.equals("S3")){
                coePushDataVO.setS3Conut(coePushDataVO.getS3Conut()+1);
            }else if (level.equals("S4")){
                coePushDataVO.setS4Count(coePushDataVO.getS4Count()+1);
            }else if (level.equals("S9")){
                coePushDataVO.setS9Count(coePushDataVO.getS9Count()+1);
            }else {
                coePushDataVO.setOtherCount(coePushDataVO.getOtherCount()+1);
            }
        }else {
            coePushDataVO.setOtherCount(coePushDataVO.getOtherCount()+1);
        }
        coePushDataVO.setAllCount(coePushDataVO.getAllCount()+1);
        coePushDataVO.setCoeMessage(coePushDataVO.getCoeMessage()+"△【"+mcdCoePO.getLevel()+"-["+mcdCoePO.getBrief()+"|"+mcdCoePO.getCoeLink()+"]】"+"\n");

        //未完善
        String context="△【"+mcdCoePO.getLevel()+"-["+mcdCoePO.getBrief()+"|"+mcdCoePO.getCoeLink()+"]】";
        Map<String, String> pushList=new HashMap<>();
        ddCoeDataPushExtracter.getPushContext(pushList,mcdCoePO,0,context);
        if (pushList.size()!=0){
            int secondDayCount=daysBetween(mcdCoePO.getCreateTime(),new Date());
            if (secondDayCount>7){
                coePushDataVO.setIncompleteCount(coePushDataVO.getIncompleteCount()+1);
                String pushText = pushList.get(mcdCoePO.getOwnerMis()).substring(0,pushList.get(mcdCoePO.getOwnerMis()).indexOf("\n[点此处理|"));
                coePushDataVO.setIncompleteMessage(coePushDataVO.getIncompleteMessage()+pushText+"\n");
            }
        }
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