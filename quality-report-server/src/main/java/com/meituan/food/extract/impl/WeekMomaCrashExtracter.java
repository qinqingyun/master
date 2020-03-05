package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meituan.food.extract.IOneWeekCrashExtract;
import com.meituan.food.mapper.WeekMomaCrashPOMapper;
import com.meituan.food.po.WeekMomaCrashPO;
import com.meituan.food.utils.YunTuBa;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class WeekMomaCrashExtracter implements IOneWeekCrashExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/widget-2ac24095-2853-47a6-b2bf-70088c0f5f22/data/page";

    @Resource
    private WeekMomaCrashPOMapper weekMomaCrashPOMapper;

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
            WeekMomaCrashPO weekMomaCrashPO = new WeekMomaCrashPO();
            for (Object o : result) {
                JSONArray dataArray = JSON.parseArray(JSONObject.toJSONString(o));
                if ((dataArray.getString(0).equals("Android")) && (dataArray.getString(1).equals("MOMA"))) {
                    weekMomaCrashPO.setMomaCrashCount(dataArray.getInteger(3));
                    weekMomaCrashPO.setMomaCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("Android")) && (dataArray.getString(1).equals("阿波罗"))) {
                    weekMomaCrashPO.setAboluoCrashCount(dataArray.getInteger(3));
                    weekMomaCrashPO.setAboluoCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("Android")) && (dataArray.getString(1).equals("蜜蜂"))) {
                    weekMomaCrashPO.setBeeCrashCount(dataArray.getInteger(3));
                    weekMomaCrashPO.setBeeCrashRate(dataArray.getBigDecimal(2));
                }
            }
            weekMomaCrashPO.setPlatform("MOMA");
            weekMomaCrashPO.setOs("Android");
            weekMomaCrashPO.setStartDate(firstDayStr);
            weekMomaCrashPO.setEndDate(lastDayStr);
            weekMomaCrashPO.setDateRange(dateRange);
            Date now = new Date();
            weekMomaCrashPO.setCreatedAt(now);
            if (next.getDays() > 9) {
                weekMomaCrashPO.setFlag(1);
            } else {
                weekMomaCrashPO.setFlag(0);
            }
            weekMomaCrashPOMapper.insert(weekMomaCrashPO);


            WeekMomaCrashPO momaCrashRatePOIos = new WeekMomaCrashPO();

            for (Object o : result) {
                JSONArray dataArray = JSON.parseArray(JSONObject.toJSONString(o));
                if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("MOMA"))) {
                    momaCrashRatePOIos.setMomaCrashCount(dataArray.getInteger(3));
                    momaCrashRatePOIos.setMomaCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("阿波罗"))) {
                    momaCrashRatePOIos.setAboluoCrashCount(dataArray.getInteger(3));
                    momaCrashRatePOIos.setAboluoCrashRate(dataArray.getBigDecimal(2));
                } else if ((dataArray.getString(0).equals("iPhone")) && (dataArray.getString(1).equals("蜜蜂"))) {
                    momaCrashRatePOIos.setBeeCrashCount(dataArray.getInteger(3));
                    momaCrashRatePOIos.setBeeCrashRate(dataArray.getBigDecimal(2));
                }
            }
            momaCrashRatePOIos.setPlatform("MOMA");
            momaCrashRatePOIos.setOs("iPhone");
            momaCrashRatePOIos.setStartDate(firstDayStr);
            momaCrashRatePOIos.setEndDate(lastDayStr);
            momaCrashRatePOIos.setDateRange(dateRange);

            Date nowIos = new Date();
            momaCrashRatePOIos.setCreatedAt(nowIos);
            if (next.getDays() > 9) {
                momaCrashRatePOIos.setFlag(1);
            } else {
                momaCrashRatePOIos.setFlag(0);
            }
            weekMomaCrashPOMapper.insert(momaCrashRatePOIos);
        }
    }
}