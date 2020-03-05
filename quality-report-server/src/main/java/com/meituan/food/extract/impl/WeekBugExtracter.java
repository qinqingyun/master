package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meituan.food.extract.IWeekBugDataExtract;
import com.meituan.food.mapper.WeekBugDetailPOMapper;
import com.meituan.food.mapper.WeekBugTotalCountPOMapper;
import com.meituan.food.po.WeekBugDetailPO;
import com.meituan.food.po.WeekBugTotalCountPO;
import com.meituan.food.utils.YunTuBa;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class WeekBugExtracter implements IWeekBugDataExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data/page";

    @Resource
    private WeekBugDetailPOMapper weekBugDetailPOMapper;

    @Resource
    private WeekBugTotalCountPOMapper weekBugTotalCountPOMapper;

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {

        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad/data/page", "POST");

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
            param.put("env", "prod");

            WeekBugTotalCountPO po = new WeekBugTotalCountPO();
            int totalCount = 0;
            int blockerCount = 0;
            int majorCount = 0;
            int minorCount = 0;
            int criticalCount = 0;
            int trivialCount = 0;
            String blockerLink = "";


            MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
            urlVariables.add("widgetId", "widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad");
            urlVariables.add("index", 1);
            urlVariables.add("params", param);
            urlVariables.add("dashKey", "dashboard-01311578-119a-4b41-a7cb-38b4b03cf538");
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
            JSONObject response = restTemplate.postForEntity(URL, httpEntity, JSONObject.class).getBody();

            int index = response.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");
            for (int i = 1; i <= index; i++) {

                MultiValueMap<String, Object> partUrlVariables = new LinkedMultiValueMap();
                partUrlVariables.add("widgetId", "widget-2d28ed8c-6d83-4c6b-92cf-8562760aa0ad");
                partUrlVariables.add("index", i);
                partUrlVariables.add("params", param);
                partUrlVariables.add("dashKey", "dashboard-01311578-119a-4b41-a7cb-38b4b03cf538");
                JSONObject partResponse = restTemplate.postForEntity(URL, httpEntity, JSONObject.class).getBody();

                JSONArray partResult = partResponse.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
                for (int j = 1; j < partResult.size(); j++) {
                    WeekBugDetailPO weekBugDetailPO = new WeekBugDetailPO();
                    JSONArray dataArray = JSON.parseArray(JSONObject.toJSONString(partResult.get(j)));
                    String bugLevel =dataArray.getString(1);
<<<<<<< HEAD
                            //  String bugLevel = ((JSONArray) (partResult.get(j))).getString(1);
=======
>>>>>>> c6ff92e4b3bff8de2c382c4e2eb4cb62b426ece9
                    String all = dataArray.getString(0);
                    String createdTimeStr = dataArray.getString(6);
                    weekBugDetailPO.setAllTitle("");
                    weekBugDetailPO.setBugLevel(bugLevel);
                    weekBugDetailPO.setReason(dataArray.getString(2));
                    weekBugDetailPO.setCreator(dataArray.getString(3));
                    weekBugDetailPO.setReceiver(dataArray.getString(4));
                    weekBugDetailPO.setCreatedTime(createdTimeStr);
                    weekBugDetailPO.setBugStatus(dataArray.getString(5));
                    weekBugDetailPO.setOrgid(key);
                    weekBugDetailPO.setOrgname(orgMap.get(key));
                    weekBugDetailPO.setTimeFlag(Long.valueOf(timestamp));
                    weekBugDetailPO.setStartDate(firstDayStr);
                    weekBugDetailPO.setEndDate(lastDayStr);
                    String link = all.substring(all.indexOf("href=") + 5, all.indexOf(">"));

                    weekBugDetailPO.setBugLink(link);

                    String bugDetail = all.substring(all.indexOf(">") + 1, all.indexOf("</a>"));
                    weekBugDetailPO.setTitle(bugDetail);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    ParsePosition pos = new ParsePosition(0);
                    Date createdBugDateTime = formatter.parse(createdTimeStr, pos);
                    weekBugDetailPO.setCreatedTimeDate(createdBugDateTime);

                    weekBugDetailPOMapper.insert(weekBugDetailPO);

                    if (bugLevel.equals("Blocker")) {
                        blockerCount++;
                        blockerLink = blockerLink + link + "、";
                    } else if (bugLevel.equals("Major")) {
                        majorCount++;
                    } else if (bugLevel.equals("Critical")) {
                        criticalCount++;
                    } else if (bugLevel.equals("Minor") || bugLevel.equals("Normal")) {
                        minorCount++;
                    } else if (bugLevel.equals("Trivial")) {
                        trivialCount++;
                    }
                    totalCount++;
                }
            }
            po.setBlockerCount(blockerCount);
            po.setMajorCount(majorCount);
            po.setCriticalCount(criticalCount);
            po.setMinorCount(minorCount);
            po.setTrivialCount(trivialCount);
            po.setTotalCount(totalCount);
            po.setTimeFlag(Long.valueOf(timestamp));
            po.setGroupName(orgMap.get(key));
            po.setStartDate(firstDayStr);
            po.setEndDate(lastDayStr);
            po.setBugLink(blockerLink);
            SimpleDateFormat formatter_2 = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos_2 = new ParsePosition(0);
            Date bugDat = formatter_2.parse(firstDayStr, pos_2);
            po.setBugDate(bugDat);

            weekBugTotalCountPOMapper.insert(po);
        }

    }
}
