package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;

public class HuiMWebLoopCheck {

    private String huiHostName = "env.api.meishi.hui.host";

    @LoopCheck(desc = "",interval = 500,timeout = 500 * 10)
    public ResponseMap getLoopQuery(JSONObject request,String userAgent,String token){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        request.getJSONObject("headers").put("accept","application/json");
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);

            return response;
        }catch (Exception e){
            return null;
        }
    }
}
