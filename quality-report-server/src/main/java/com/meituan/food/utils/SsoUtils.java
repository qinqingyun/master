package com.meituan.food.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;

public final class SsoUtils {

    public static String getSsoId() {
       /* JSONObject authParams = new JSONObject();
        authParams.put("user", "test_meishi_qa");
        authParams.put("password", "meishi_qa1234");
        authParams.put("clientId", "com.sankuai.meishi.qa.meishireport");
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
        return tokenRes.getString("access_token");*/

       //线上sso_host
        String sso_host="https://sso.vip.sankuai.com/sson";
        //线下sso_host
       // String sso_host="http://ssosv.it.beta.sankuai.com/sson";

        //线上clientId
        String clientId="0d6cf99067";
        //线上AppSecret
        String appSecret="e77b663737ae4a938b0673818a0e68ad";

        //线下clientId
      //  String clientId="cc6049a1ee";
        //线下AppSecret
       // String appSecret="55777cf8eda44eaca7ffeedcc806d246";

        JSONObject authParams=new JSONObject();
        authParams.put("loginName","test_meishi_qa");
       authParams.put("password","meishi_qa1234");
      // authParams.put("password","Ceshi123");
       authParams.put("isSingleDevice",false);

       JSONObject authRes=HttpUtils.doPostWithAuth(URI.create(sso_host+"/api/auth"),
               authParams.toJSONString(),
               JSONObject.class,
               clientId,appSecret);
       String refreshToken=authRes.getJSONObject("data").getString("refreshToken");

       JSONObject authParam=new JSONObject();
       authParam.put("refreshToken",refreshToken);
       JSONObject ssoRes=HttpUtils.doPostWithAuth(URI.create(sso_host+"/oauth2.0/refresh-token"),
               authParam.toJSONString(),
               JSONObject.class,
               clientId,appSecret);

       String ssoId=ssoRes.getJSONObject("data").getString("accessToken");

       return ssoId;


     //  return "a3e8a0911a*84b069e6b16d2dd1daef4";
    }

    private SsoUtils() {
    }

    public static void main(String[] args) {
        System.out.println(getSsoId());
    }
}
