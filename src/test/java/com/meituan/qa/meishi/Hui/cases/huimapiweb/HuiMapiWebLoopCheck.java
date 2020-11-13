package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;


public class HuiMapiWebLoopCheck {

    private String huiHostName = "env.api.meishi.hui.host";

    /**
     * hui-mapi-web服务下请求方式为get的接口轮询
     * @param token         登录的美团/点评用户对应的有效token
     * @param userAgent     美团用户使用美团app的client，点评用户使用点评app的client
     * @param request       已经封装好params的请求
     * @return  接口的返回结果
     */

    @LoopCheck(desc = "通用轮询函数-get请求", interval = 500, timeout = 500*10)
    public ResponseMap getLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);
            if(response.getStatusCode() != 200){
                return null;
            }
            return response;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * hui-mapi-web服务下请求方式为post的接口轮询
     * @param token         登录的美团/点评用户对应的有效token
     * @param userAgent     美团用户使用美团app的client，点评用户使用点评app的client
     * @param request       已经封装好params和body的请求
     * @return  接口的返回结果
     */
    @LoopCheck(desc = "通用轮询函数-post请求", interval = 500, timeout = 500*10)
    public ResponseMap postLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送post请求，并获取返回结果
        try{
            ResponseMap response = DBCaseRequestUtil.post(huiHostName,request);
            if(response.getStatusCode() != 200){
                return null;
            }
            return response;
        }catch (Exception e){
            return null;
        }
    }

}
