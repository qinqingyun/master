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
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 场景：点评、美团APP，买单订单支付完成后调用查询订单支付结果，轮询接口"
* 返回结果部分参数说明：
*   支付的状态(0 未支付，1支付成功，-1 支付失败 -10 创建之后发起支付失败，不对用户显示)，2（退款中），3（已退款）
* */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi",des = "查询订单支付状态")
@Slf4j
@HTTPAPI(apiPath = "hui/querymopaystatus.bin")
public class TestQueryMOPayStatusAction extends TestDPLogin {
    private HuiMapiWebLoopCheck loopCheck = new HuiMapiWebLoopCheck();

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为支付中的订单")
    public void ms_c_querymopaystatusaction_01(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",mtToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);                 // 美团用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    //支付成功
    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为支付成功的订单")
    public void ms_c_querymopaystatusaction_02(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",mtToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);      // 美团用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为已经退款的订单")
    public void ms_c_querymopaystatusaction_03(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",mtToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);      // 美团用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

       queryMOPayStatusAssert(responseMap,expect);
    }

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-（点评侧）查询订单状态为支付中的订单")
    public void ms_c_querymopaystatusaction_101(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",dpToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(dpToken,dpClient,request);                 // 点评用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        JSONObject body = JSON.parseObject(responseMap.getResponseBody());
        queryMOPayStatusAssert(responseMap, expect);
    }

    //支付成功
    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-（点评侧）查询订单状态为支付成功的订单")
    public void ms_c_querymopaystatusaction_102(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",dpToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(dpToken,dpClient,request);                 // 点评用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-（点评侧）查询订单状态为已经退款的订单")
    public void ms_c_querymopaystatusaction_103(JSONObject request,JSONObject expect){

        request.getJSONObject("params").put("token",dpToken);

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = loopCheck.getLoopQuery(dpToken,dpClient,request);                 // 点评用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    /**
     * 校验查询订单状态接口的返回结果，核对订单描述信息（StatusMsg）、错误信息（ErrorMsg）、订单状态码（Status）
     * @param responseMap   接口实际返回结果
     * @param expect        期望返回结果
     */
    public void queryMOPayStatusAssert(ResponseMap responseMap,JSONObject expect){
        List<String> key_list = Arrays.asList("ErrorMsg","StatusMsg","Status");

        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);

        AssertUtil.assertJSONPathExists(responseMap,"$.StatusMsg");
        AssertUtil.assertJSONPathExists(responseMap,"$.ErrorMsg");
        AssertUtil.assertJSONPathExists(responseMap,"$.Status");

        AssertUtil.assertNotNull(JSONObject.parseObject(responseMap.getResponseBody()).get("StatusMsg"),"订单状态描述（StatusMsg）为空");
        AssertUtil.assertTrue(JSONObject.parseObject(responseMap.getResponseBody()).getIntValue("Status")>= 0,"支付状态码（Status）不是大于0的整数！");

        AssertUtil.assertJsonEquals(responseMap,expect, JSONCompareMode.LENIENT,key_list,"实际返回与期望返回不符！",true);

    }

}
