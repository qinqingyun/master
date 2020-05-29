package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/receiptverify.bin",
        type = "mapi",des="买单自助验券")
@Slf4j
@HTTPAPI(apiPath = "/hui/receiptverify.bin")
public class TestReceiptVerify extends TestDPLogin {
    ResponseMap response = null;
    private String _APIPATH = "/hui/receiptverify.bin";

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "正确用例")
    public void ms_c_receiptverify_01(String token, String userAgent, String caseId) throws Exception {

        autoCreateOrder(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "券码错误")
    public void ms_c_receiptverify_02(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.errorTips[0]");
    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "验券数超过拥有券码数")
    public void ms_c_receiptverify_03(String token, String userAgent, String caseId) throws Exception {

        autoCreateOrder(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.errorTips[0]");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "已验券再次进行验证")
    public void ms_c_receiptverify_04(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.errorTips[0]");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "券码与门店不符")
    public void ms_c_receiptverify_05(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.errorTips[0]");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "shopid非法")
    public void ms_c_receiptverify_06(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Title");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "receiptnum为小数或负数")
    public void ms_c_receiptverify_07(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Title");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "receiptnum为0")
    public void ms_c_receiptverify_08(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Title");

    }

    @Test(groups = {"P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "receipt字段缺失")
    public void ms_c_receiptverify_09(String token, String userAgent, String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Title");

    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "grouponorderid与门店不对应")
    public void ms_c_receiptverify_10(String token, String userAgent, String caseId) throws Exception {
        if(envpath.contains("test")){
            Map<String,Object> result = CreateOrderUtil.ms_c_tgCreateOrder("ms_c_tgCreateOrder_01");
//            Map<String,Object> result = CreateOrderUtil.ms_c_tgDPCreateOrder(dpToken,"ms_c_tgCreateOrder_02");
            List<String> coupons =  (List<String>)result.get("couponList");
            response = commonCallNew(token,userAgent,caseId,"2556356384",coupons.get(0));
        }else{
            response = commonCall(token,userAgent,caseId);
        }
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        AssertUtil.assertJsonPathValueEquals(response, expect,"$.statusMsg");

    }

    public ResponseMap commonCall(String token, String userAgent, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        return response;
    }

    public ResponseMap commonCallNew(String token, String userAgent, String caseId, String orderId, String coupon){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
      
		JsonPathUtil.setJsonPathVaule(request, "$.body.receipt",coupon);
		JsonPathUtil.setJsonPathVaule(request, "$.body.grouponorderid",orderId);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        return response;
    }

    public void autoCreateOrder(String token,String userAgent,String caseId) throws Exception {
        if(envpath.contains("test")){
            Map<String,Object> result = CreateOrderUtil.ms_c_tgCreateOrder("ms_c_tgCreateOrder_01");
//            Map<String,Object> result = CreateOrderUtil.ms_c_tgDPCreateOrder(dpToken,"ms_c_tgCreateOrder_02");

            String orderid = (String)result.get("orderid");
            List<String> coupons =  (List<String>)result.get("couponList");
            response = commonCallNew(token,userAgent,caseId,orderid,coupons.get(0));
        }else{
            response = commonCall(token,userAgent,caseId);
        }
    }
}
