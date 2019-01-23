package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayFourteenExtract;
import com.meituan.food.mapper.TaskDurationPOMapper;
import com.meituan.food.po.TaskDurationPO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TaskDurationExtracter  implements IOneDayFourteenExtract {

    private static final String url="https://yuntu.sankuai.com/api/widget/widget-f13dc271-4fa2-469c-90f0-c8236e908916/data?params=";

    @Resource
    private TaskDurationPOMapper taskDurationPOMapper;

    @Resource
    private EmpService empService;

    @Override
    public void extractData4Day(LocalDate firstDay, LocalDate lastDay) throws MDMThriftException {
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TaskDurationPO> taskDurationPOS=new ArrayList<>();
        for(OrgEnum e:OrgEnum.values()){
            JSONObject param=new JSONObject();
            param.put("startDate",firstDayStr);
            param.put("endDate",lastDayStr);
            param.put("orgId",e.getOrgId());
            String encodeParam=UrlUtils.encode(param.toJSONString());
            JSONObject response=HttpUtils.doGet(url+encodeParam+"&index=1&useCache=true",JSONObject.class,ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
            int index=response.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");
            for(int i=1;i<=index;i++){
                JSONObject partResponse=HttpUtils.doGet(url+encodeParam+"&useCache=true&index="+i,JSONObject.class,ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
                JSONArray result=partResponse.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
                for (int j=1;j<result.size();j++){
                    TaskDurationPO taskDurationPO=new TaskDurationPO();
                    String linkAndName=((JSONArray)(result.get(j))).getString(0);
                    String personalLink = linkAndName.substring(linkAndName.indexOf("href=") + 5, linkAndName.lastIndexOf("}")+1);
                    String name= linkAndName.substring(linkAndName.indexOf("}>") + 2, linkAndName.lastIndexOf("</a>"));
                    BigDecimal taskDuration=((JSONArray)(result.get(j))).getBigDecimal(1);
                    taskDurationPO.setDashboard(personalLink);
                    taskDurationPO.setMisid(name);
                    taskDurationPO.setStartDate(firstDayStr);
                    taskDurationPO.setEndDate(lastDayStr);
                    taskDurationPO.setOrgId(e.getOrgId());
                    taskDurationPO.setOrgGroup(e.getOrgName());
                    taskDurationPO.setDuration(taskDuration);
                    taskDurationPO.setSecondLeader(e.getLeaderMis());
                    Date now=new Date();
                    taskDurationPO.setCreatedAt(now);
                    BigDecimal lowDuration=new BigDecimal("32.0");
                    BigDecimal highDuration=new BigDecimal("48.0");
                //    Emp emp = empService.queryByMis(name, null);
                    taskDurationPO.setRealName("");
                    taskDurationPO.setFirstLeader("");
                    if((taskDuration.compareTo(lowDuration)==1 || taskDuration.compareTo(lowDuration)==0)&&(taskDuration.compareTo(highDuration)==0||taskDuration.compareTo(highDuration)==-1)) {
                        taskDurationPO.setIsnormal(true);
                    }else {
                        taskDurationPO.setIsnormal(false);

                    }
                    taskDurationPOMapper.insert(taskDurationPO);
                    taskDurationPOS.add(taskDurationPO);
                   /* if(emp!=null){
                        taskDurationPO.setRealName(emp.getName());
                        if (emp.getReportEmpMis()!=e.getLeaderMis()&&(!name.equals(e.getLeaderMis()))){
                            taskDurationPO.setFirstLeader(emp.getReportEmpMis());
                        }else if(name.equals(e.getLeaderMis())){
                            taskDurationPO.setFirstLeader(name);
                        }
                        else {
                            taskDurationPO.setFirstLeader(name);
                        }
                        if((taskDuration.compareTo(lowDuration)==1 || taskDuration.compareTo(lowDuration)==0)&&(taskDuration.compareTo(highDuration)==0||taskDuration.compareTo(highDuration)==-1)) {
                            taskDurationPO.setIsnormal(true);
                        }else {
                            taskDurationPO.setIsnormal(false);

                        }
                        taskDurationPOMapper.insert(taskDurationPO);
                        taskDurationPOS.add(taskDurationPO);
                    }else {
                        taskDurationPO.setRealName("");
                        taskDurationPO.setFirstLeader("");
                        taskDurationPO.setIsnormal(true);
                    }*/
                }
            }
/*
            EmpHierarchyCond empCond = new EmpHierarchyCond();
            empCond = empCond.jobStatusIdET(15);//在职
            Paging paging = new Paging();
            paging.setSize(1000);

            EmpItems empItems = empService.queryEmp(e.getOrgId(), 3, empCond, paging);
            List<Emp> items = empItems.getItems();

            List<String> nameList=new ArrayList<>();
            for (TaskDurationPO taskDurationPO : taskDurationPOS) {
                nameList.add(taskDurationPO.getMisid());
            }
            for (Emp item : items) {
                if(nameList.contains(item.getMis())==false){
                    TaskDurationPO po=new TaskDurationPO();
                    po.setMisid(item.getMis());
                    po.setRealName(item.getName());
                    if(item.getReportEmpMis()!=e.getLeaderMis()&&(!item.getMis().equals(e.getLeaderMis()))){
                        po.setFirstLeader(item.getReportEmpMis());
                    }else if(item.getMis().equals(e.getLeaderMis())){
                        po.setFirstLeader(item.getMis());
                    }else {
                        po.setFirstLeader(item.getMis());
                    }
                    po.setSecondLeader(e.getLeaderMis());
                    po.setIsnormal(false);
                    Date now=new Date();
                    po.setCreatedAt(now);
                    po.setStartDate(firstDayStr);
                    po.setEndDate(lastDayStr);
                    po.setOrgId(e.getOrgId());
                    po.setOrgGroup(e.getOrgName());
                    po.setDuration(new BigDecimal("0.0"));
                    po.setDashboard("无数据");

                    taskDurationPOS.add(po);
                    taskDurationPOMapper.insert(po);
                }
            }*/
        }

        for (TaskDurationPO po : taskDurationPOS) {
            if(po.getIsnormal()==false){
                DaXiangUtils.pushToPerson("啊哦，您【"+firstDayStr+ "~"+lastDayStr+"】工时数据为:"+po.getDuration()+", 存在异常请及时处理~,地址："+po.getDashboard(),po.getMisid());
            //    DaXiangUtils.pushToPerson(po.getMisid()+"啊哦，您【"+firstDayStr+ "~"+lastDayStr+"】工时数据为:"+po.getDuration()+", 存在异常请及时处理~,地址："+po.getDashboard(),"guomengyao");

            }
        }
       /* List<String> allFirstLeader=new ArrayList<>();
        if(taskDurationPOS!=null){
            for (TaskDurationPO po : taskDurationPOS) {
                if(allFirstLeader.contains(po.getFirstLeader())==false && po.getFirstLeader()!=null && (!po.getFirstLeader().equals(""))){
                //    allFirstLeader.add(po.getFirstLeader());
                }
            }
        }

        for (String s : allFirstLeader) {
            String nameList="";
            for (TaskDurationPO po : taskDurationPOS) {
                if(po.getFirstLeader()!=null && (!po.getFirstLeader().equals(""))) {
                    if (po.getFirstLeader().equals(s) && po.getIsnormal() == false) {
                        nameList = nameList + po.getRealName() + " ";
                    }
                }
            }
            if(nameList!="") {
            //    DaXiangUtils.pushToPerson(nameList + "的工时数据存在异常，请及时督促处理~", s);
             //       DaXiangUtils.pushToPerson(nameList + "的工时数据存在异常，请及时督促处理~", "guomengyao");

            }
        }*/

        for(OrgEnum e:OrgEnum.values()) {
            String secondNameList="";
            for (TaskDurationPO po : taskDurationPOS) {
                if(po.getSecondLeader().equals(e.getLeaderMis())&&po.getIsnormal()==false){
                    secondNameList=secondNameList+po.getMisid()+" ";
                }
            }
            if(secondNameList!=""){
                DaXiangUtils.pushToPerson(secondNameList + "的工时数据存在异常，请及时督促处理~", e.getLeaderMis());
             //      DaXiangUtils.pushToPerson(secondNameList + "的工时数据存在异常，请及时督促处理~","guomengyao");

            }
        }

    }

    public enum OrgEnum{
        QA_ORG("质量组", "104638", "wuhaibo"),
        SUPPLY_ORG("供应链组", "100058", "fuchaoqun"),
        DATA_ORG("数据组","100061", "liuqiang24"),
        B_ORG("商家组","104077","yangchunfei"),
        STRATEGY_ORG("策略组","105434","liuding"),
        B_FE_ORG("B端大前端组","110621","lixiaoyang02"),
        C_ORG("用户组","110620","yaolei"),
        C_FE_ORG("C端大前端组","112308","wangkang");


        private String orgName;
        private String orgId;
        private String leaderMis;


        OrgEnum(String orgName, String orgId, String leaderMis) {
            this.orgName = orgName;
            this.orgId = orgId;
            this.leaderMis = leaderMis;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getLeaderMis() {
            return leaderMis;
        }

        public void setLeaderMis(String leaderMis) {
            this.leaderMis = leaderMis;
        }
    }

    public static void main(String[] args) {
        DaXiangUtils.pushToPerson("test","jiangyu07");
        DaXiangUtils.pushToPerson("test","lixuechao");

        DaXiangUtils.pushToPerson("test","guomengyao");

    }

}
