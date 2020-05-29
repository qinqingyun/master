package com.meituan.qa.meishi.Hui.cases.huimweb;

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
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/mm/wxadetail",
        type = "https",des="美团微信小程序订单详情页。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxadetail")
public class TestWxaDetail extends TestDPLogin {
    private String _APIPATH = "/hui/mm/wxadetail";

    /*@Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-11",updateTime = "2018-03-11",des = "查看订单详情")
    public void ms_c_wxaDetail_01(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.statusMsg", "$.statusMsg","");
    }*/

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-11",updateTime = "2018-03-11",des = "orderId与用户不匹配")
    public void ms_c_wxaDetail_02(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-11",updateTime = "2018-03-11",des = "orderId缺失或错误")
    public void ms_c_wxaDetail_03(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-11",updateTime = "2018-03-11",des = "token缺失或错误")
    public void ms_c_wxaDetail_04(String token,String client,String caseId){
        ResponseMap response = callService("",client,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    public ResponseMap callService(String token, String client, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		return DBCaseRequestUtil.get("env.api.meishi.weixin.hui.maiton.host", request);
    }
}
