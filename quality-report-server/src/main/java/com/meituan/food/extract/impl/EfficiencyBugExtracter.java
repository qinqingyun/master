package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.meituan.food.extract.IOneDayDataExtract;
import com.meituan.food.extract.IOneDayEffDataEx;
import com.meituan.food.mapper.EfficiencyBugNumPOMapper;
import com.meituan.food.po.EfficiencyBugNumPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.meituan.food.utils.YunTuBa;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class EfficiencyBugExtracter implements IOneDayEffDataEx {

    @Resource
    private EfficiencyBugNumPOMapper efficiencyBugNumPOMapper;

    //  private static final String URL = "https://yuntu.sankuai.com/api/widget/widget-f4949af3-3bbc-4d46-9a85-30768be352c8/data?params=";
    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data/page";


    @Override
    public void extractData4EffDay(LocalDate day) {

        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //测试中心orgid
        String orgId = "114606";
        JSONObject param = new JSONObject();
        param.put("orgId", orgId);
        param.put("startDate", firstDayStr);
        param.put("endDate", firstDayStr);
        param.put("dateDim", "DAY_DATE");
        param.put("env", "prod");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data/page", "POST");
        MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
        urlVariables.add("widgetId", "widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad");
        urlVariables.add("index", 1);
        urlVariables.add("params", param);
        urlVariables.add("dashKey", "dashboard-01311578-119a-4b41-a7cb-38b4b03cf538");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
        JSONObject response = restTemplate.postForEntity(URL, httpEntity, JSONObject.class).getBody();

        int index = response.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");

        Map<String, BugCount> bugCountMap = Maps.newHashMap();

        for (int i = 1; i <= index; i++) {
            MultiValueMap<String, Object> partUrlVariables = new LinkedMultiValueMap();
            partUrlVariables.add("widgetId", "widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad");
            partUrlVariables.add("index", i);
            partUrlVariables.add("params", param);
            partUrlVariables.add("dashKey", "dashboard-01311578-119a-4b41-a7cb-38b4b03cf538");
            HttpEntity<MultiValueMap<String, Object>> partHttpEntity = new HttpEntity(partUrlVariables, headers);
            JSONObject partResponse = restTemplate.postForEntity(URL, partHttpEntity, JSONObject.class).getBody();
            JSONArray partResult = partResponse.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
            for (int j = 1; j < partResult.size(); j++) {
                JSONArray partResultArr = JSON.parseArray(JSONObject.toJSONString(partResult.get(j)));
                String createAllName = partResultArr.getString(3);
                if (createAllName != null && (!createAllName.equals("无"))) {

                    String createMis = createAllName.substring(createAllName.indexOf("(") + 1, createAllName.lastIndexOf(")"));

                    // 创建
                    BugCount createBugCount = bugCountMap.get(createMis);
                    if (createBugCount == null) {
                        createBugCount = BugCount.zero();
                        bugCountMap.put(createMis, createBugCount);
                    }
                    createBugCount.increaseCreateBugNum();
                }
                String acceptAllName = partResultArr.getString(4);
                if (acceptAllName != null && (!acceptAllName.equals("无"))) {

                    String acceptMis = acceptAllName.substring(acceptAllName.indexOf("(") + 1, acceptAllName.lastIndexOf(")"));

                    // 接受
                    BugCount acceptBugCount = bugCountMap.get(acceptMis);
                    if (acceptBugCount == null) {
                        acceptBugCount = BugCount.zero();
                        bugCountMap.put(acceptMis, acceptBugCount);
                    }
                    acceptBugCount.increaseAcceptBugNum();
                }
            }
        }

//        System.out.println(bugCountMap.toString());
        for (String key : bugCountMap.keySet()) {
            EfficiencyBugNumPO efficiencyBugNumPO = new EfficiencyBugNumPO();
            efficiencyBugNumPO.setMis(key);
            efficiencyBugNumPO.setCreateNum(bugCountMap.get(key).getCreateBugNum());
            efficiencyBugNumPO.setAcceptNum(bugCountMap.get(key).getAcceptBugNum());
            efficiencyBugNumPO.setEfficiencyDate(firstDayStr);
            Date now = new Date();
            efficiencyBugNumPO.setCreatedAt(now);
            efficiencyBugNumPO.setUpdatedAt(now);

            efficiencyBugNumPOMapper.insert(efficiencyBugNumPO);
        }


    }

    @Data
    static class BugCount {
        private int createBugNum;
        private int acceptBugNum;

        public static BugCount zero() {
            BugCount bugCount = new BugCount();
            bugCount.setCreateBugNum(0);
            bugCount.setAcceptBugNum(0);
            return bugCount;
        }

        public void increaseCreateBugNum() {
            this.createBugNum++;
        }

        public void increaseAcceptBugNum() {
            this.acceptBugNum++;
        }
    }
}
