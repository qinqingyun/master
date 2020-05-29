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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "hui/ordercheck.hui",
        type = "mapi",des = "距离弹出(0开头用例表示点评，1表示美团)")
@Slf4j
@HTTPAPI(apiPath = "hui/ordercheck.hui")
public class TestOrderCheck extends TestDPLogin{
    private String _APIPATH = "hui/ordercheck.hui";
//    private String _CASEFOLDER0 = "Hui/ordercheck";


    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "弹出距离弹窗")
    public void ms_c_ordercheck_01(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "在店铺内进行买单")
    public void ms_c_ordercheck_02(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "shopid非法")
    public void ms_c_ordercheck_03(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "经纬度为空")
    public void ms_c_ordercheck_04(String token,String userAgent,String caseId)throws Exception{

        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "lat-纬度大于90")
    public void ms_c_ordercheck_05(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "lng-经度小于-180")
    public void ms_c_ordercheck_06(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);

		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-14",updateTime = "2017-11-14",des = "shopid不存在")
    public void ms_c_ordercheck_07(String token,String userAgent,String caseId)throws Exception{
        ResponseMap response = commonCall(token,userAgent,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = new JSONObject();
        try{
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    public ResponseMap commonCall(String token, String userAgent, String caseId){
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-os",userAgent);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        return response;
    }
}
