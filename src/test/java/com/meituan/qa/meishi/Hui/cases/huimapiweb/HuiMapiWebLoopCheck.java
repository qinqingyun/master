package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;

public class HuiMapiWebLoopCheck {
    @LoopCheck(desc = "创建订单请求轮询",interval = 500, timeout = 500*10)
    public ResponseMap createOrderLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request body(请求体)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送post请求，并获取返回结果
        try{
            ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host",request);

            if(response.getStatusCode() != 200){
                return null;
            }
            return response;
        }catch (Exception e){
            return null;
        }
    }
}
