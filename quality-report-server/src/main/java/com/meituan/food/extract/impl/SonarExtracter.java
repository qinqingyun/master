package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneQuarterDataExtract;
import com.meituan.food.job.vo.SonarGroup;
import com.meituan.food.mapper.SonarPOMapper;
import com.meituan.food.po.SonarPO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component
public class SonarExtracter implements IOneQuarterDataExtract {

    private static final String url = "http://sonar.sankuai.com/api/sonarProjectList?filter=";

    @Resource
    SonarPOMapper sonarPOMapper;


    @Override
    public void extractData4Quarter(LocalDate firstDay, LocalDate lastDay) {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<SonarGroup> sonarGroups = new ArrayList<SonarGroup>();

        Map<String, String> projects = new HashMap<>();
        projects.put("闪惠", "meishi_b_hui");
        projects.put("闪电上单(B)", "meishi_b_scp");
        projects.put("销售与运营管理(B)", "meishi_b_crm");
        projects.put("数据产品(B)", "meishi_b_data");
        projects.put("商家后台(B)", "meishi_b_merchant");
        projects.put("Web端(B)", "meishi_b_fe");
        projects.put("美团iOS(C)", "meishi_b_c_ios");
        projects.put("美团Android(C)", "meishi_b_c_android");
        projects.put("信息组(C)", "meishi_b_c_info");
        projects.put("订单组(C)", "meishi_b_c_order");
        projects.put("促销组(C)", "meishi_b_c_campaign");
        projects.put("商家财务（B）", "meishi_b_finance");
        projects.put("代理商(B)", "meishi_b_agent");
        projects.put("运营组(C)", "meishi_b_c_luffy");
        projects.put("新预订", "meishi_b_resv");
        projects.put("提成(B)", "meishi_b_achiever");
        projects.put("策略组", "meishi_b_dcgstgy");
        projects.put("餐饮学院", "meishi_b_dccyxy");
        projects.put("盘古移动端", "meishi_b_moma_android");
        projects.put("美食i版c端", "meishi_b_c_fe_meishi");
        projects.put("美食I版B端", "meishi_b_b_fe_meishi");

        List<String> cFeList = new ArrayList<>();
        cFeList.add("美团iOS(C)");
        cFeList.add("美团Android(C)");
        cFeList.add("美食I版B端");
        cFeList.add("美食i版c端");

        sonarGroups.add(new SonarGroup("C大前端", cFeList, "王康"));

        List<String> cServerList = new ArrayList<>();
        cServerList.add("闪惠");
        cServerList.add("信息组(C)");
        cServerList.add("订单组(C)");
        cServerList.add("促销组(C)");
        cServerList.add("运营组(C)");
        cServerList.add("餐饮学院");
        // groupProjects.put("C服务端",cServerList);
        sonarGroups.add(new SonarGroup("C服务端", cServerList, "姚磊"));

        List<String> scpList = new ArrayList<>();
        scpList.add("闪电上单(B)");
        scpList.add("销售与运营管理(B)");
        scpList.add("代理商(B)");
        scpList.add("提成(B)");
        // groupProjects.put("供应链",scpList);
        sonarGroups.add(new SonarGroup("供应链", scpList, "付超群"));

        List<String> bList = new ArrayList<>();
        bList.add("商家后台(B)");
        bList.add("商家财务（B）");
        //   groupProjects.put("商家端",bList);
        sonarGroups.add(new SonarGroup("商家端", bList, "阳纯飞"));

        List<String> bFeList = new ArrayList<>();
        bFeList.add("Web端(B)");
        bFeList.add("盘古移动端");
        // groupProjects.put("B大前端",bFeList);
        sonarGroups.add(new SonarGroup("B大前端", bFeList, "李晓阳"));

        List<String> bDataList = new ArrayList<>();
        bDataList.add("数据产品(B)");
        //  groupProjects.put("数据产品",bDataList);
        sonarGroups.add(new SonarGroup("数据产品", bDataList, "刘强"));

        List<String> dcgstgyList = new ArrayList<>();
        dcgstgyList.add("策略组");
        // groupProjects.put("策略组",dcgstgyList);
        sonarGroups.add(new SonarGroup("策略组", dcgstgyList, "刘丁"));

        List<String> resvList = new ArrayList<>();
        resvList.add("新预订");
        // groupProjects.put("预定",resvList);
        sonarGroups.add(new SonarGroup("预定", resvList, "董光兵"));


        for (SonarGroup sonarGroup : sonarGroups) {
            int blocker = 0;
            int critical = 0;
            String subProjects = "";
            for (String s : sonarGroup.getSubGroupName()) {
                String URL = url + projects.get(s);
                JSONArray response = HttpUtils.doGet(URL, JSONArray.class, ImmutableMap.of("Authorization", "Basic c29uYXI6ZXBlcE1UTVRA"));

                for (Object o : response) {
                    blocker = blocker + ((JSONObject) o).getInteger("blocker_violations");
                    critical = critical + ((JSONObject) o).getInteger("critical_violations");

                }
                subProjects = subProjects + " " + s;
            }

            SonarPO sonarPO = new SonarPO();
            sonarPO.setProject(sonarGroup.getGroupName());
            sonarPO.setIncludesubproject(subProjects);
            sonarPO.setBlocker(blocker);
            sonarPO.setCritical(critical);
            sonarPO.setLeader(sonarGroup.getLeaderName());
            Date now = new Date();
            sonarPO.setCreatedAt(now);
            sonarPO.setUpdatedAt(now);
            sonarPO.setSonarDate(lastDayStr);
            sonarPO.setLink("");
            sonarPO.setGroupCount(Long.valueOf(timestamp));
            sonarPOMapper.insert(sonarPO);
        }
    }

}
