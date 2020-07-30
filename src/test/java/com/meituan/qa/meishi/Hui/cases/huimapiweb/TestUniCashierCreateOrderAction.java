package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.cases.param.Api;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;

import lombok.extern.slf4j.Slf4j;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scala.util.parsing.combinator.testing.Str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi", des = "点评、美团创建订单，给 >= 7.8.1 版本的APP专用")
@Slf4j
@HTTPAPI(apiPath = "hui/unicashiercreateorder.bin")
public class TestUniCashierCreateOrderAction extends TestDPLogin{

    private static Long orderId;
    private static String serializedId;

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-29",updateTime = "2020-07-29",des = "正确用例")
    public void ms_c_unicashiercreateorderaction_01(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");

        log.info("入参：", JSON.toJSONString(request));
        // 构造request body(请求体)
        String token = mtToken;
        String userAgent = mtClient;
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
//        request.getJSONObject("body").put("bizorderid",bizOrderId);
//        request.getJSONObject("body").put("bookrecordid",bizOrderId);
        // 发送post请求，并获取返回结果
        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host",request);

        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        // 1 检验状态码
        AssertUtil.assertHttp200(response);
//        orderId = response.getValueByJsonPath("$.orderId");
//        serializedId = response.getValueByJsonPath("$.serializedId");

//        System.out.println(orderId);
//        System.out.println(serializedId);
//        System.out.println(ex_orderId);
//        System.out.println(ex_serializedId);
        // 2 校验实际结果与预期结果
        // 2.1 判断JSONPath是否存在
        for(String path:path_list ){
            AssertUtil.assertJSONPathExists(response,path,"返回结果中的"+path+"字段不存在");
        }

        // 2.2 判断是否为空
        for(String path:path_list) {
            AssertUtil.assertNotNull(response,path,"返回结果中的"+path+"为null/空");
        }

    }

}
