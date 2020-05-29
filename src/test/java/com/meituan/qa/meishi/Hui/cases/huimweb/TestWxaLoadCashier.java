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

@ClassAnnotation(author = "liukang", depart = "C", apiName = "/hui/mm/wxaloadcashier",
        type = "https", des = "美团微信小程序加载收银台。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxaloadcashier")
public class TestWxaLoadCashier extends TestDPLogin {
    private String _APIPATH = "/hui/mm/wxaloadcashier";
    private Boolean flag = true;//点评美团返回结果不同，通过标识来判断点评结果还是美团结果，true为点评

    @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-08", updateTime = "2018-03-08", des = "正确加载收银台")
    public void ms_c_wxaLoadCashier_01(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.wxaCashierCouponVO.offers[0].title", "$.title","");
    }

   /* @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "shopType字段缺失")
    public void ms_c_wxaLoadCashier_02(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
        if (flag) {
			AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
        } else {
			AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.wxaCashierCouponVO.offers[0].title", "$.title","");
        }
        flag = !flag;
    }*/

    //测试环境6258521门店为上午10：30-12：00可用(线上不能上方案所以没有这样的门店)
   /* @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "门店优惠不可用")
    public void ms_c_wxaLoadCashier_03(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

    @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "shopid错误")
    public void ms_c_wxaLoadCashier_04(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

   /* @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "门店没有优惠")
    public void ms_c_wxaLoadCashier_05(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

    @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "shopType为错误值")
    public void ms_c_wxaLoadCashier_06(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

  /*  //输入代码中没有的类型，经沟通可以暂时不关注该字段，默认只有闪惠
    @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "bizOrderType非指定类型")
    public void ms_c_wxaLoadCashier_07(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.wxaCashierCouponVO.offers[0].title", "$.title","");
    }*/

    @Test(groups = {"weixin", "P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-03-11", updateTime = "2018-03-11", des = "token缺失或为空")
    public void ms_c_wxaLoadCashier_08(String token, String client, String caseId) {
        ResponseMap response = callService("", client, caseId);
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }

    //以下为点评小程序接入商家券
   /* @Test(groups = {"weixin", "P3"})
    @MethodAnotation(author = "liukang", createTime = "2018-09-27", updateTime = "2018-09-27", des = "门店为智能支付门店")
    public void ms_c_wxaLoadCashier_09(String token, String client, String caseId) {
        ResponseMap response = callService(dpToken, dpClient, "ms_c_wxaLoadCashier_009");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.supportDpDiscount");
    }*/

  /*  @Test(groups = {"weixin", "P3"})
    @MethodAnotation(author = "liukang", createTime = "2018-09-27", updateTime = "2018-09-27", des = "门店为非智能支付门店")
    public void ms_c_wxaLoadCashier_10(String token, String client, String caseId) {
        ResponseMap response = callService(dpToken, dpClient, "ms_c_wxaLoadCashier_010");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.data.supportDpDiscount");
    }*/

    public ResponseMap callService(String token, String client, String caseId) {
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		return  DBCaseRequestUtil.get("env.api.meishi.weixin.hui.maiton.host", request);
    }
}
