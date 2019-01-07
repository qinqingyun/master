package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.mapper.MomaCrashRatePOMapper;
import com.meituan.food.po.MomaCrashRatePO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Order(4)
@Component
public class MomaCrashRateExtracter implements IOneMonthDataExtract {

    private static final String URL="https://yuntu.sankuai.com/api/widget/widget-2ac24095-2853-47a6-b2bf-70088c0f5f22/data?";

    @Resource
    private MomaCrashRatePOMapper momaCrashRatePOMapper;

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


        if(result.size()!=0 && result.size()!=1) {
            MomaCrashRatePO momaCrashRatePO = new MomaCrashRatePO();
            for (Object o : result) {
                if ((((JSONArray) o).getString(0).equals("Android")) && (((JSONArray) o).getString(1).equals("MOMA"))) {
                    momaCrashRatePO.setMomaCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePO.setMomaCrashRate(((JSONArray) o).getBigDecimal(2));
                } else if ((((JSONArray) o).getString(0).equals("Android")) && (((JSONArray) o).getString(1).equals("阿波罗"))) {
                    momaCrashRatePO.setAboluoCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePO.setAboluoCrashRate(((JSONArray) o).getBigDecimal(2));
                } else if ((((JSONArray) o).getString(0).equals("Android")) && (((JSONArray) o).getString(1).equals("蜜蜂"))) {
                    momaCrashRatePO.setBeeCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePO.setBeeCrashRate(((JSONArray) o).getBigDecimal(2));
                }
            }
            momaCrashRatePO.setPlatform("MOMA");
            momaCrashRatePO.setOs("Android");
            momaCrashRatePO.setMonth(crashMonth);
            Date now = new Date();
            momaCrashRatePO.setCreatedAt(now);
            momaCrashRatePO.setUpdatedAt(now);
            momaCrashRatePOMapper.insert(momaCrashRatePO);


            MomaCrashRatePO momaCrashRatePOIos = new MomaCrashRatePO();

            for (Object o : result) {
                if ((((JSONArray) o).getString(0).equals("iPhone")) && (((JSONArray) o).getString(1).equals("MOMA"))) {
                    momaCrashRatePOIos.setMomaCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePOIos.setMomaCrashRate(((JSONArray) o).getBigDecimal(2));
                } else if ((((JSONArray) o).getString(0).equals("iPhone")) && (((JSONArray) o).getString(1).equals("阿波罗"))) {
                    momaCrashRatePOIos.setAboluoCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePOIos.setAboluoCrashRate(((JSONArray) o).getBigDecimal(2));
                } else if ((((JSONArray) o).getString(0).equals("iPhone")) && (((JSONArray) o).getString(1).equals("蜜蜂"))) {
                    momaCrashRatePOIos.setBeeCrashCount(((JSONArray) o).getInteger(3));
                    momaCrashRatePOIos.setBeeCrashRate(((JSONArray) o).getBigDecimal(2));
                }
            }
            momaCrashRatePOIos.setPlatform("MOMA");
            momaCrashRatePOIos.setOs("iPhone");
            momaCrashRatePOIos.setMonth(crashMonth);

            Date nowIos = new Date();
            momaCrashRatePOIos.setCreatedAt(nowIos);
            momaCrashRatePOIos.setUpdatedAt(nowIos);
            momaCrashRatePOMapper.insert(momaCrashRatePOIos);
        }
    }
}
