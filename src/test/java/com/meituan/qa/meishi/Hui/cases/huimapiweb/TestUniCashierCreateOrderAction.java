package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;

import lombok.extern.slf4j.Slf4j;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi", des = "点评、美团创建订单，给 >= 7.8.1 版本的APP专用")
@Slf4j
@HTTPAPI(apiPath = "hui/unicashiercreateorder.bin")
public class TestUniCashierCreateOrderAction extends TestDPLogin{

    private HuiMapiWebLoopCheck hmwLoop = new HuiMapiWebLoopCheck();

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-29",updateTime = "2020-07-31",des = "正确用例-使用折扣买单")
    public void ms_c_unicashiercreateorderaction_01(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");

        log.info("入参：", JSON.toJSONString(request));

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);      // mtToken,mtClient来自继承的类TestDPLogin
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
       createOrderAssert(response,path_list);
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-07-31",des = "正确用例-使用满减方案买单")
    public void ms_c_unicashiercreateorderaction_02(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：", JSON.toJSONString(request));

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);      // mtToken,mtClient来自继承的类TestDPLogin
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list);
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-07-31",des = "正确用例-使用商家抵用券买单")
    public void ms_c_unicashiercreateorderaction_03(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：", JSON.toJSONString(request));

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);      // mtToken,mtClient来自继承的类TestDPLogin
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list);
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-07-31",des = "正确用例-原价买单")
    public void ms_c_unicashiercreateorderaction_04(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：", JSON.toJSONString(request));

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);      // mtToken,mtClient来自继承的类TestDPLogin
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list);
    }


    /**
     * 校验创建订单接口的返回结果，包括是否JSONPath是否存在、值是否为空、OrderId是否大于0
     * @param response：接口返回结果
     * @param path_list：需要进行校验的字段的JSONPath
     */
    public void createOrderAssert(ResponseMap response,List<String> path_list){
        // 校验返回结果
        // 0 校验返回结果不为空
        AssertUtil.assertNotNull(response,"下单失败：返回结果为null/空");

        // 1 校验状态码
        AssertUtil.assertHttp200(response);

        // 2 校验实际结果与预期结果
        // 2.1 判断JSONPath是否存在
        for(String path:path_list ){
            AssertUtil.assertJSONPathExists(response,path,"下单失败：返回结果中的字段不存在");
        }

        // 2.2 判断是否为空
        for(String path:path_list) {
            AssertUtil.assertNotNull(response,path,"下单失败：返回结果中的"+path+"为null/空");
        }

        // 2.3 其他判断
        Long orderId = response.getValueByJsonPath("$.OrderId");
        AssertUtil.assertTrue(orderId>0,"下单失败：OrderId小于0");
    }


}
