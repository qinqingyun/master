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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/mm/wxaresult",
        type = "https",des="美团微信支付成功页。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxaresult")
public class TestWxaResult extends TestDPLogin {
    private String _APIPATH = "/hui/mm/wxaresult";

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "订单支付成功")
    public void ms_c_wxaResult_01(String token,String client,String caseId){

        ResponseMap response = callService(token,client,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.orderMessage", "$.orderMessage","");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "订单支付中")
    public void ms_c_wxaResult_02(String token,String client,String caseId){

        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.orderMessage", "$.orderMessage","");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "orderid缺失或为空")
    public void ms_c_wxaResult_03(String token,String client,String caseId){

        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "orderid错误")
    public void ms_c_wxaResult_04(String token,String client,String caseId){

        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

   /* @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "token缺失")
    public void ms_c_wxaResult_05(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(null,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "token错误")
    public void ms_c_wxaResult_06(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService("errorKey",client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "serializedOrderId错误或缺失")
    public void ms_c_wxaResult_07(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

   /* @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "ispoll字段校验为1")
    public void ms_c_wxaResult_08(String token,String client,String caseId)throws Exception{
        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        ArrayList<String> keyList = new ArrayList<String>(){{add("orderMessage");add("isPoll");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.data"),expect.getJSONObject("$"),JSONCompareMode.LENIENT,keyList,true);
    }*/
//只能线下模拟
  //  @Test)
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "支付失败")
    public void ms_c_wxaResult_09(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        System.out.println(response);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data", "$","");
    }

    public ResponseMap callService(String token, String client, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		return DBCaseRequestUtil.get("env.api.meishi.weixin.hui.maiton.host", request);
    }
}
