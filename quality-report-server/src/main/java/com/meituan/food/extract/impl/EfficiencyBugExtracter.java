package com.meituan.food.extract.impl;

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
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class EfficiencyBugExtracter implements IOneDayEffDataEx {

    @Resource
    private EfficiencyBugNumPOMapper efficiencyBugNumPOMapper;

    //  private static final String URL = "https://yuntu.sankuai.com/api/widget/widget-f4949af3-3bbc-4d46-9a85-30768be352c8/data?params=";
    private static final String URL = "https://yuntu.sankuai.com/api/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data?params=";


    @Override
    public void extractData4EffDay(LocalDate day) {

        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String orgId = "114606";
        JSONObject param = new JSONObject();
        param.put("orgId", orgId);
        param.put("startDate", firstDayStr);
        param.put("endDate", firstDayStr);
        param.put("dateDim", "DAY_DATE");

        String encodedParam = UrlUtils.encode(param.toJSONString());

        JSONObject response = HttpUtils.doGet(URL + encodedParam + "&index=1&useCache=true", JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));
        JSONArray result = response.getJSONObject("data").getJSONObject("resData").getJSONArray("data");

        int index = response.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");
        System.out.println("index=" + index);

        Map<String, BugCount> bugCountMap = Maps.newHashMap();


        for (int i = 1; i <= index; i++) {
            String url = URL + encodedParam + "&index=" + i + "&useCache=true";
            JSONObject partResponse = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));

            JSONArray partResult = partResponse.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
            for (int j = 1; j < partResult.size(); j++) {
                String createAllName = ((JSONArray) (partResult.get(j))).getString(3);
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
                String acceptAllName = ((JSONArray) (partResult.get(j))).getString(4);
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

    public static void main(String[] args) {
       /* LocalDate day = LocalDate.now().minusDays(2);
        IOneDayDataExtract a = new EfficiencyBugExtracter();
        a.extractData4Day(day);
       */

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
