package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.utils.HttpUtils;
import retrofit2.http.PUT;

public class COEDataExtracter implements ICOEDataExtract {

    private static final String url="http://coe.sankuai.com/api/v1.0/query/incidents";

    @Override
    public void getCOEData(String dateStr) {
        JSONObject params=new JSONObject();
        params.put("occur_start","2019-7-10");
        params.put("occur_end","2019-8-22");
        params.put("org",70);
        params.put("category","");
        params.put("page",1);
        params.put("page_size",10);
        params.put("sort","desc");
        params.put("sort_by","create_at");
        params.put("list_type","all");

        JSONObject resp=HttpUtils.doPost(url,params.toJSONString(),JSONObject.class,ImmutableMap.of("content-type", "application/json","Accept","text/plain, text/html,application/json","Authorization", "Bearer 9767c6e3488c88135f5032bc6da7339084b1a05b"));
        resp.toJSONString();
    }

    public static void main(String[] args) {
        ICOEDataExtract a= new COEDataExtracter();
        a.getCOEData("2019-08-10");
    }
}
