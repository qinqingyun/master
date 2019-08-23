package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.utils.HttpUtils;

public class COEDataExtracter implements ICOEDataExtract {

    private static final String url="http://coe.sankuai.com/api/v1.0/query/incidents";

    @Override
    public void getCOEData(String dateStr) {
        String department="dianping.dc";
        JSONObject params=new JSONObject();
        params.put("occur_start",dateStr);
        params.put("occur_end",dateStr);
        params.put("department",department);

        JSONObject resp=HttpUtils.doPost(url,params.toJSONString(),JSONObject.class,ImmutableMap.of("content-type", "application/json","Accept","text/plain, text/html,application/json","Authorization", "Bearer 9767c6e3488c88135f5032bc6da7339084b1a05b"));
        resp.toJSONString();
    }

    public static void main(String[] args) {
        ICOEDataExtract a= new COEDataExtracter();
        a.getCOEData("2019-08-10");
    }
}
