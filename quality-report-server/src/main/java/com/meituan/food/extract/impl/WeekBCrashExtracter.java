package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneWeekCrashExtract;
import com.meituan.food.extract.IOneWeekEightDataExtract;
import com.meituan.food.mapper.WeekBCrashPOMapper;
import com.meituan.food.po.BCrashRatePO;
import com.meituan.food.po.WeekBCrashPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.meituan.food.utils.YunTuBa;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class WeekBCrashExtracter implements IOneWeekCrashExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/widget-2ac24095-2853-47a6-b2bf-70088c0f5f22/data/page";

    @Resource
    private WeekBCrashPOMapper weekBCrashPOMapper;

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {
        Period next = Period.between(firstDay, lastDay);

        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String dateRange = firstDayStr + "~" + lastDayStr;

        JSONObject param = new JSONObject();
        param.put("orgId", "100047");
        param.put("start_date", firstDayStr);
        param.put("end_date", lastDayStr);
        param.put("dateDim", "DAY_DATE");
        param.put("env", "prod");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/widget-2ac24095-2853-47a6-b2bf-70088c0f5f22/data/page", "POST");
        MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
        urlVariables.add("widgetId", "widget-2ac24095-2853-47a6-b2bf-70088c0f5f22");
        urlVariables.add("params", param);
        urlVariables.add("dashKey", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59");
        urlVariables.add("index", 1);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
        JSONObject response = restTemplate.postForEntity(URL, httpEntity, JSONObject.class).getBody();

        JSONArray result = response.getJSONObject("data").getJSONObject("resData").getJSONObject("data").getJSONArray("data");

        if (result.size() != 0 && result.size() != 1) {
            WeekBCrashPO weekBCrashPO = new WeekBCrashPO();
            for (Object o : result) {
<<<<<<< HEAD
                JSONArray dataArr= JSON.parseArray(JSONObject.toJSONString(o));
                if ((dataArr.getString(0).equals("Android")) && (dataArr.getString(1).equals("开店宝"))) {
                    weekBCrashPO.setbCrashCount(dataArr.getInteger(3));
                    weekBCrashPO.setbCrashRate(dataArr.getBigDecimal(2));
                } else if ((dataArr.getString(0).equals("Android")) && (dataArr.getString(1).equals("点评管家"))) {
                    weekBCrashPO.setbDianpingCrashCount(dataArr.getInteger(3));
                    weekBCrashPO.setbDianping(dataArr.getBigDecimal(2));
                } else if ((dataArr.getString(0).equals("Android")) && (dataArr.getString(1).equals("外卖商家"))) {
                    weekBCrashPO.setbWaimaiCrashCount(dataArr.getInteger(3));
                    weekBCrashPO.setbWaimai(dataArr.getBigDecimal(2));
=======
                JSONArray dataArray = JSON.parseArray(JSONObject.toJSONString(o));
                if (dataArray.getString(0).equals("Android") && dataArray.getString(1).equals("开店宝")) {
                    weekBCrashPO.setbCrashCount(dataArray.getInteger(3));
                    weekBCrashPO.setbCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("Android")) && (dataArray.getString(1).equals("点评管家"))) {
                    weekBCrashPO.setbDianpingCrashCount(dataArray.getInteger(3));
                    weekBCrashPO.setbDianping(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("Android")) && (dataArray.getString(1).equals("外卖商家"))) {
                    weekBCrashPO.setbWaimaiCrashCount(dataArray.getInteger(3));
                    weekBCrashPO.setbWaimai(dataArray.getBigDecimal(2));
>>>>>>> c6ff92e4b3bff8de2c382c4e2eb4cb62b426ece9
                }
            }
            weekBCrashPO.setPlatform("开店宝");
            weekBCrashPO.setOs("Android");
            weekBCrashPO.setStartDate(firstDayStr);
            weekBCrashPO.setEndDate(lastDayStr);
            Date now = new Date();
            weekBCrashPO.setCreatedAt(now);
            weekBCrashPO.setDateRange(dateRange);
            if (next.getDays() > 9) {
                weekBCrashPO.setFlag(1);
            } else {
                weekBCrashPO.setFlag(0);
            }
            weekBCrashPOMapper.insert(weekBCrashPO);


            WeekBCrashPO bCrashRatePOIos = new WeekBCrashPO();

            for (Object o : result) {
<<<<<<< HEAD
                JSONArray dataArr= JSON.parseArray(JSONObject.toJSONString(o));
                if ((dataArr.getString(0).equals("iPhone")) && (dataArr.getString(1).equals("开店宝"))) {
                    bCrashRatePOIos.setbCrashCount(dataArr.getInteger(3));
                    bCrashRatePOIos.setbCrashRate(dataArr.getBigDecimal(2));
                } else if ((dataArr.getString(0).equals("iPhone")) && (dataArr.getString(1).equals("点评管家"))) {
                    bCrashRatePOIos.setbDianpingCrashCount(dataArr.getInteger(3));
                    bCrashRatePOIos.setbDianping(dataArr.getBigDecimal(2));
                } else if ((dataArr.getString(0).equals("iPhone")) && (dataArr.getString(1).equals("外卖商家"))) {
                    bCrashRatePOIos.setbWaimaiCrashCount(dataArr.getInteger(3));
                    bCrashRatePOIos.setbWaimai(dataArr.getBigDecimal(2));
=======
                JSONArray dataArray = JSON.parseArray(JSONObject.toJSONString(o));
                if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("开店宝"))) {
                    bCrashRatePOIos.setbCrashCount(dataArray.getInteger(3));
                    bCrashRatePOIos.setbCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("点评管家"))) {
                    bCrashRatePOIos.setbDianpingCrashCount(dataArray.getInteger(3));
                    bCrashRatePOIos.setbDianping((dataArray.getBigDecimal(2)));
                } else if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("外卖商家"))) {
                    bCrashRatePOIos.setbWaimaiCrashCount(dataArray.getInteger(3));
                    bCrashRatePOIos.setbWaimai(dataArray.getBigDecimal(2));
>>>>>>> c6ff92e4b3bff8de2c382c4e2eb4cb62b426ece9
                }
            }
            bCrashRatePOIos.setPlatform("开店宝");
            bCrashRatePOIos.setOs("iPhone");
            bCrashRatePOIos.setStartDate(firstDayStr);
            bCrashRatePOIos.setEndDate(lastDayStr);
            bCrashRatePOIos.setDateRange(dateRange);

            Date nowIos = new Date();
            bCrashRatePOIos.setCreatedAt(nowIos);
            if (next.getDays() > 9) {
                bCrashRatePOIos.setFlag(1);
            } else {
                bCrashRatePOIos.setFlag(0);
            }
            weekBCrashPOMapper.insert(bCrashRatePOIos);
        }
    }
}
