package com.meituan.food.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.Collections;

public final class SsoUtils {

    public static String getSsoId() {
        JSONObject authParams = new JSONObject();
        authParams.put("user", "test_meishi_qa");
        authParams.put("password", "meishi_qa1234");
        authParams.put("clientId", "com.sankuai.ep.meishi.monthreport");
        authParams.put("deviceType", "mobile");
        authParams.put("loginIp", "10.20.62.107");
        authParams.put("singleDevice", true);
        JSONObject authRes = HttpUtils.doPost("http://api.sso-in.sankuai.com/api/oauth2.0/v2/auth/light",
                authParams.toJSONString(),
                JSONObject.class,
                ImmutableMap.of("Content-Type", HttpUtils.JSON_CONTENT_TYPE));
        String code = authRes.getJSONObject("data").getString("code");

        String tokenParams = "grant_type=code&client_id=com.sankuai.ep.meishi.monthreport&client_secret=0bad7dd9728c46ff8a7b96b6e0c1ee85&code=" + code;

        JSONObject tokenRes = HttpUtils.doGet("http://api.sso-in.sankuai.com/oauth2.0/accessToken?" + tokenParams,
                JSONObject.class,
                Collections.emptyMap());
        return tokenRes.getString("access_token");
    }

    private SsoUtils() {
    }

    public static void main(String[] args) {
        System.out.println(getSsoId());
    }
}
