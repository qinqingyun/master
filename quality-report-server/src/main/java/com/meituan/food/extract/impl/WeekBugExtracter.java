package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IWeekBugDataExtract;
import com.meituan.food.mapper.WeekBugDetailPOMapper;
import com.meituan.food.po.WeekBugDetailPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeekBugExtracter implements IWeekBugDataExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data?params=";

    @Resource
    private WeekBugDetailPOMapper weekBugDetailPOMapper;

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {

        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mSsoid = SsoUtils.getSsoId();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Map<String, String> orgMap = new HashMap<>();
        orgMap.put("106452", "服务端测试组");
        orgMap.put("106453", "客户端测试组");
        orgMap.put("106454", "商家平台测试组");
        orgMap.put("106456", "客户平台测试组");
        orgMap.put("106457", "到餐平台测试组");

        for (String key : orgMap.keySet()) {
            JSONObject param = new JSONObject();
            param.put("orgId", key);
            param.put("startDate", firstDayStr);
            param.put("endDate", lastDayStr);
            param.put("dateDim", "DAY_DATE");
            String encodedParam = UrlUtils.encode(param.toJSONString());

            String test=URL + encodedParam + "&index=1&useCache=true";


            JSONObject response = HttpUtils.doGet(URL + encodedParam + "&index=1&useCache=true", JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));
            int index = response.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");

            for (int i = 1; i <= index; i++) {
                String url = URL + encodedParam + "&index=" + i + "&useCache=true";
                JSONObject partResponse = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));

                JSONArray partResult = partResponse.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
                for (int j = 1; j < partResult.size(); j++) {
                    WeekBugDetailPO weekBugDetailPO=new WeekBugDetailPO();
                    String all=((JSONArray) (partResult.get(j))).getString(0);
                    weekBugDetailPO.setAllTitle(all);
                    weekBugDetailPO.setBugLevel(((JSONArray) (partResult.get(j))).getString(1));
                    weekBugDetailPO.setReason(((JSONArray) (partResult.get(j))).getString(2));
                    weekBugDetailPO.setCreator(((JSONArray) (partResult.get(j))).getString(3));
                    weekBugDetailPO.setReceiver(((JSONArray) (partResult.get(j))).getString(4));
                    weekBugDetailPO.setCreatedTime(((JSONArray) (partResult.get(j))).getString(5));
                    weekBugDetailPO.setBugStatus(((JSONArray) (partResult.get(j))).getString(6));
                    weekBugDetailPO.setOrgid(key);
                    weekBugDetailPO.setOrgname(orgMap.get(key));
                    weekBugDetailPO.setTimeFlag(Long.valueOf(timestamp));

                    String link = all.substring(all.indexOf("href=") + 5, all.indexOf(">"));
                    weekBugDetailPO.setBugLink(link);

                    String bugDetail=all.substring(all.indexOf(">")+1,all.indexOf("</a>"));
                    weekBugDetailPO.setTitle(bugDetail);

                    weekBugDetailPOMapper.insert(weekBugDetailPO);
                }
            }
        }

    }
}
