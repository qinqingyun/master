package com.meituan.qa.meishi.Hui.cases.testsuite;

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

import org.testng.annotations.Test;

@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi", des = "点评、美团创建订单，给 >= 7.8.1 版本的APP专用")
@Slf4j
@HTTPAPI(apiPath = "hui/unicashiercreateorder.bin")
public class createTest {

    private static Long orderId;
    private static String serializedId;

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-29",updateTime = "2020-07-29",des = "正确用例")
    public void ms_c_unicashiercreateorderaction_01(JSONObject request, JSONObject expect){

//        System.out.print("入参 ");
//        System.out.println(JSON.toJSONString(request));
        log.info("入参：", JSON.toJSONString(request));

        String token = "e9moU411-HnvMgOvpQexp2rNCKoAAAAACe4BABk0Qd0VzZK5I2mzjVzYIgW-vr431UL7NUgGHPoXCkV-ZQ0c5eLA3QSw-_8oa6UTcw";
        String userAgent = "MApi 1.3 (mtscope 11.1.200 appstore; iPhone 12.2 iPhone10,1; a0d0)";
        String bizOrderId = "";
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
//        request.getJSONObject("body").put("bizorderid",bizOrderId);
//        request.getJSONObject("body").put("bookrecordid",bizOrderId);

        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host",request);

//        System.out.print("结果返回 ");
        //System.out.println(JSON.toJSONString(request));
        log.info("结果返回：{}",JSON.toJSONString(response));

        AssertUtil.assertHttp200(response);
        //Assert.assertEquals(response.toString(),expect.toJSONString());


    }

}