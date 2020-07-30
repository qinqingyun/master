package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderByReceiptRequest;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;


public class HuiMapiWebApi {
    @HTTPAPI()
    /**
     * 创建订单
     */
    public ResponseMap CreateOrderResponse(JSONObject request, String token, String userAgent){
        // 构造request body(请求体)
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
//        request.getJSONObject("body").put("bizorderid",bizOrderId);
//        request.getJSONObject("body").put("bookrecordid",bizOrderId);
        // 发送post请求，并获取返回结果
        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host",request);
        return response;
    }

}
