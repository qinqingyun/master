package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;

@ClassAnnotation(author = "liukang", depart = "C", apiName = "/hui/querymopaystatus.bin",
        type = "mapi", des = "查询支付状态(0开头用例表示点评，1表示美团)")
@Slf4j
@HTTPAPI(apiPath = "hui/querymopaystatus.bin")
public class TestQueryMOpayStatus extends TestDPLogin {
    private String _APIPATH = "hui/querymopaystatus.bin";
    //    private String _CASEFOLDER0 = "Hui/querymopaystatus";

    /*@Test(groups = {"P3", "mapi"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2017-11-16", updateTime = "2017-11-16", des = "已支付状态")
    public void ms_c_querymopaystatus_01(String token, String userAgent, String caseId) {
        ResponseMap response = commonCall(token, userAgent, caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>() {{
            add("StatusMsg");
        }};
        AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }*/
/*
    @Test(groups = {"P3", "mapi"})
    @MethodAnotation(author = "liukang", createTime = "2017-11-16", updateTime = "2017-11-16", des = "订单未支付")
    public void ms_c_querymopaystatus_02(String token, String userAgent, String caseId) {
        ResponseMap response = commonCall(dpToken, dpClient, "ms_c_querymopaystatus_002");
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>() {{
            add("StatusMsg");
            add("ErrorMsg");
        }};
        AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }*/


    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2017-11-16", updateTime = "2017-11-16", des = "serializedid为空")
    public void ms_c_querymopaystatus_03(String token, String userAgent, String caseId) throws JSONException {
        ResponseMap response = commonCall(token, userAgent, caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>() {{
            add("Content");
        }};
        try {
            AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()), expect, JSONCompareMode.LENIENT, keyList, true);
        }catch (JSONException e){
            log.error(e.getMessage());
        }
    }

    /*@Test(groups = {"P3", "mapi"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2017-11-16", updateTime = "2017-11-16", des = "serializedid非法")
    public void ms_c_querymopaystatus_04(String token, String userAgent, String caseId) {
        ResponseMap response = commonCall(token, userAgent, caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>() {{
            add("Content");
        }};

        AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }*/

    //以下为B扫c
 /*   @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "B扫C查询团购券支付结果")
    public void ms_c_querymopaystatus_05(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);

        ArrayList<String> keyList = new ArrayList<String>(){{add("StatusMsg");}};
        assertCall(response,"$.receiptVerify","$.receiptVerify",keyList);
    }
*//*
    @Test(groups = {"test"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-10-11", updateTime = "2018-10-11", des = "B扫C查询商家券支付结果")
    public void ms_c_querymopaystatus_06(String token, String userAgent, String caseId) throws Exception{
        ResponseMap response = commonCall(token, userAgent, caseId);
        log.info(response.getResponseBody());
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
//        ArrayList<String> keyList = new ArrayList<String>(){{add("StatusMsg");}};
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.couponDescription");
    }
*/
    public ResponseMap commonCall(String token, String userAgent, String caseId) {
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token", token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent", userAgent);*/
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        return response;
    }
}
