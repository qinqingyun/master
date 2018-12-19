package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.mapper.BCrashRatePOMapper;
import com.meituan.food.po.BCrashRatePO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


@Slf4j
@Order(3)
@Component
public class BCrashRateExtracter implements IOneMonthDataExtract {

    private static final String URL="https://yuntu.sankuai.com/api/widget/widget-2ac24095-2853-47a6-b2bf-70088c0f5f22/data?";

    @Resource
    private BCrashRatePOMapper bCrashRatePOMapper;

    @Override
    public void extractData4Month(String firstDay, String lastDay) {
        JSONObject param=new JSONObject();
        param.put("orgId","100047");
        param.put("start_date",firstDay);
        param.put("end_date",lastDay);

        String crashMonth=firstDay.substring(0, firstDay.indexOf("-", firstDay.indexOf("-") + 1));
        String encodedParam=UrlUtils.encode(param.toJSONString());

        JSONObject response=HttpUtils.doGet(URL+"params="+encodedParam+"&index=1",JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));


        JSONArray result=response.getJSONObject("data").getJSONObject("resData").getJSONObject("data").getJSONArray("data");


        BCrashRatePO bCrashRatePO=new BCrashRatePO();
        for (Object o : result) {
            if((((JSONArray)o).getString(0).equals("Android"))&&(((JSONArray)o).getString(1).equals("开店宝"))){
                bCrashRatePO.setbCrashCount(((JSONArray)o).getInteger(3));
                bCrashRatePO.setbCrashRate(((JSONArray) o).getBigDecimal(2));
            }else if((((JSONArray)o).getString(0).equals("Android"))&&(((JSONArray)o).getString(1).equals("点评管家"))){
                bCrashRatePO.setbDianpingCrashCount(((JSONArray) o).getInteger(3));
                bCrashRatePO.setbDianping(((JSONArray) o).getBigDecimal(2));
            }else if((((JSONArray)o).getString(0).equals("Android"))&&(((JSONArray)o).getString(1).equals("外卖商家"))){
                bCrashRatePO.setbWaimaiCrashCount(((JSONArray) o).getInteger(3));
                bCrashRatePO.setbWaimai(((JSONArray) o).getBigDecimal(2));
            }
        }
        bCrashRatePO.setPlatfrom("开店宝");
        bCrashRatePO.setOs("Android");
        bCrashRatePO.setMonth(crashMonth);
        Date now=new Date();
        bCrashRatePO.setCreatedAt(now);
        bCrashRatePO.setUpdatedAt(now);
        bCrashRatePOMapper.insert(bCrashRatePO);


        BCrashRatePO bCrashRatePOIos=new BCrashRatePO();

        for (Object o : result) {
            if((((JSONArray)o).getString(0).equals("iPhone"))&&(((JSONArray)o).getString(1).equals("开店宝"))){
                bCrashRatePOIos.setbCrashCount(((JSONArray)o).getInteger(3));
                bCrashRatePOIos.setbCrashRate(((JSONArray) o).getBigDecimal(2));
            }else if((((JSONArray)o).getString(0).equals("iPhone"))&&(((JSONArray)o).getString(1).equals("点评管家"))){
                bCrashRatePOIos.setbDianpingCrashCount(((JSONArray) o).getInteger(3));
                bCrashRatePOIos.setbDianping(((JSONArray) o).getBigDecimal(2));
            }else if((((JSONArray)o).getString(0).equals("iPhone"))&&(((JSONArray)o).getString(1).equals("外卖商家"))){
                bCrashRatePOIos.setbWaimaiCrashCount(((JSONArray) o).getInteger(3));
                bCrashRatePOIos.setbWaimai(((JSONArray) o).getBigDecimal(2));
            }
        }
        bCrashRatePOIos.setPlatfrom("开店宝");
        bCrashRatePOIos.setOs("iPhone");
        bCrashRatePOIos.setMonth(crashMonth);

        Date nowIos=new Date();
        bCrashRatePOIos.setCreatedAt(nowIos);
        bCrashRatePOIos.setUpdatedAt(nowIos);
        bCrashRatePOMapper.insert(bCrashRatePOIos);

    }

    public static void main(String[] args) {
        IOneMonthDataExtract a=new BCrashRateExtracter();
        a.extractData4Month("2018-11-01","2018-11-30");
    }
}
