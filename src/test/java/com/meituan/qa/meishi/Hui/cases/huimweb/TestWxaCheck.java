package com.meituan.qa.meishi.Hui.cases.huimweb;

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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/mm/wxacheck",
        type = "https",des="美团微信小程序距离检查。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxacheck")
public class TestWxaCheck extends TestDPLogin {
    private String _APIPATH = "/hui/mm/wxacheck";

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "弹出距离弹窗")
    public void ms_c_wxaCheck_01(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "在店铺内进行买单")
    public void ms_c_wxaCheck_02(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "纬度为空")
    public void ms_c_wxaCheck_03(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "经度为空")
    public void ms_c_wxaCheck_04(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "经纬度错误")
    public void ms_c_wxaCheck_05(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "shopid不存在")
    public void ms_c_wxaCheck_06(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("message");add("status");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$.data"),JSONCompareMode.LENIENT,keyList,true);
    }

    public ResponseMap callService(String token, String client, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		return  DBCaseRequestUtil.get("env.api.meishi.weixin.hui.maiton.host", request);
    }
}
