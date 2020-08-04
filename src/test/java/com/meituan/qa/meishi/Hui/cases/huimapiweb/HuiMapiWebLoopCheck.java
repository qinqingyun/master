package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;


public class HuiMapiWebLoopCheck {

    private String huiHostName = "env.api.meishi.hui.host";

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

            return response;
        }catch (Exception e){
            return null;
        }

    }

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

            return response;
        }catch (Exception e){
            return null;
        }
    }

    @LoopCheck(desc = "创建订单请求轮询",interval = 500, timeout = 500*10)
    public ResponseMap createOrderLoopQuery(String token,String userAgent,JSONObject request){
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
/*
    @LoopCheck(desc = "统一收银台接口", interval = 500, timeout = 500*10)
    public ResponseMap loadUnifiedCashierLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);

            return response;
        }catch (Exception e){
            return null;
        }

    }

    @LoopCheck(desc = "点评侧在商户详情页获取MOPay商户优惠信息", interval = 500, timeout = 500*10)
    public ResponseMap getMOPayPromosInfoLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);

            return response;
        }catch (Exception e){
            return null;
        }

    }

    @LoopCheck(desc = "用户与门店间距离检查", interval = 500, timeout = 500*10)
    public ResponseMap orderCheckLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);

            return response;
        }catch (Exception e){
            return null;
        }

    }

    @LoopCheck(desc = "用户与门店间距离检查", interval = 500, timeout = 500*10)
    public ResponseMap orderCheckLoopQuery(String token,String userAgent,JSONObject request){
        // 构造request headers(请求头部)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        // 发送get请求
        try{
            ResponseMap response = DBCaseRequestUtil.get(huiHostName,request);

            return response;
        }catch (Exception e){
            return null;
        }

    }

 */
}
