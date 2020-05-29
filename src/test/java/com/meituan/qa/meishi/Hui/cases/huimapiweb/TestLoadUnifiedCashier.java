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
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "hui/loadunifiedcashier.bin",
        type = "mapi",des = "进入收银台(0开头用例表示点评，1表示美团)")
@Slf4j
@HTTPAPI(apiPath = "hui/loadunifiedcashier.bin")
public class TestLoadUnifiedCashier extends TestDPLogin {
    private String _APIPATH = "hui/loadunifiedcashier.bin";
//    private String _CASEFOLDER0 = "Hui/loadunifiedcashier";
    private Boolean flag = true ;//点评美团返回结果不同，通过标识来判断点评结果还是美团结果，true为点评



    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-10-26",updateTime = "2017-10-26",des = "进入收银台")
    public void ms_c_loadunifiedcashier_01(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.CouponProducts[0]"),expect.getJSONObject("$.CouponProducts[0]"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-9",updateTime = "2017-11-9",des = "店铺没有买单和没有买单优惠")
    public void ms_c_loadunifiedcashier_02(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
//        if(flag) {
            ArrayList<String> keyList = new ArrayList<String>() {{
                add("Content");
            }};
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
			AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);//        }else {
////            ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
////            response.assertResponseFileds("$","$.response");
////            response.assertResponseContent("$","$.response",keyList);
//            Assert.assertTrue(response.getValueByJsonPath("$.HasAvailableDiscount").equals("false"));
//        }
//        flag = !flag;
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-9",updateTime = "2017-11-9",des = "shopid非法")
    public void ms_c_loadunifiedcashier_03(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-9",updateTime = "2017-11-9",des = "shopid缺失")
    public void ms_c_loadunifiedcashier_04(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-9",updateTime = "2017-11-10",des = "bizordertype为0")
    public void ms_c_loadunifiedcashier_05(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.CouponProducts[0]"),expect.getJSONObject("$.CouponProducts[0]"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-13",updateTime = "2017-11-13",des = "没有有效用户")
    public void ms_c_loadunifiedcashier_06(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall("errorkey",userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-18",updateTime = "2018-09-18",des = "nosidcountAmout字段缺失")
    public void ms_c_loadunifiedcashier_07(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.CouponProducts[0]"),expect.getJSONObject("$.CouponProducts[0]"),JSONCompareMode.LENIENT,keyList,true);
    }

    public ResponseMap commonCall(String token, String userAgent, String caseId){
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        return response;
    }
}
