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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang", depart = "C", apiName = "/hui/mm/wxacreateorder",
        type = "https", des = "美团微信小程序创单。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxacreateorder")
public class TestWxaCreateOrder extends TestDPLogin {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private String _APIPATH = "/hui/mm/wxacreateorder";
    private Boolean flag = true;//点评美团返回结果不同，通过标识来判断点评结果还是美团结果，true为点评


    @Test(groups = {"P1"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "正常下单")
    public void ms_c_wxacCreateOrder_01(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        //AssertUtil.assertJSONPathExists(response, "$");
        //Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
        // Assert.assertTrue(!"0".equals(response.getJSONObjectByJsonPath("data").getString("orderId")));
    }

   /* @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-11",updateTime = "2018-09-11",des = "优惠过期不存在")
    public void ms_c_wxacCreateOrder_02(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        log.info(response.toString());
		AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("您所选择的优惠已过期，请重新支付".equals(response.getValueByJsonPath("$.msg")));
    }*/


    @Test(groups = {"P1"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "originamount传入小于等于0")
    public void ms_c_wxacCreateOrder_03(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("消费金额不能小于1元哦".equals(response.getValueByJsonPath("$.msg")));
    }

    @Test(groups = {"P2"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "originamount传入3为小数值")
    public void ms_c_wxacCreateOrder_04(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }


    @Test(groups = {"P2"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "originamount小于nodiscountamout金额")
    public void ms_c_wxacCreateOrder_05(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("您输入的总金额小于不参与优惠金额，请确认输入！".equals(response.getValueByJsonPath("$.msg")));
    }

   /* @Test(groups = {"weixin","P3"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-11",updateTime = "2018-09-11",des = " nodiscountamount传入与原价相同金额")
    public void ms_c_wxacCreateOrder_06(String token,String client,String caseId){
        ResponseMap response = callService(token,client,caseId);
        log.info(response.toString());
		AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
    }*/

    @Test(groups = {"P2"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "useramount缺失")
    public void ms_c_wxacCreateOrder_07(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

    @Test(groups = {"P2"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "originamount缺失")
    public void ms_c_wxacCreateOrder_08(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }


    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "shopid缺失")
    public void ms_c_wxacCreateOrder_09(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertNotEquals(0, response.getValueByJsonPath("$.code"));
    }

    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "错误或没有买单方案门店")
    public void ms_c_wxacCreateOrder_10(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("当前门店不可买单".equals(response.getValueByJsonPath("$.msg")));
    }


    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "shopType字段传入非指定值")
    public void ms_c_wxacCreateOrder_11(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("门店类型不正确".equals(response.getValueByJsonPath("$.msg")));
    }


    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "useramount传入3为小数值")
    public void ms_c_wxacCreateOrder_12(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "nodiscountamount传入3为小数值")
    public void ms_c_wxacCreateOrder_13(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "nodiscountamount小于0")
    public void ms_c_wxacCreateOrder_14(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

    /*  @Test(groups = {"weixin","P3"},dataProvider = "param")
      @MethodAnotation(author = "liukang",createTime = "2018-09-11",updateTime = "2018-09-11",des = "nofferIdStr字段缺失")
      public void ms_c_wxacCreateOrder_15(String token,String client,String caseId){
          ResponseMap response = callService(token,client,caseId);
          log.info(response.toString());
          AssertUtil.assertJSONPathExists(response, "$");
          Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
      }
  */

    @Test(groups = {"P3"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "openid字段缺失")
    public void ms_c_wxacCreateOrder_16(String token, String client, String caseId) {
        ResponseMap response = callService(token, client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("openid不正确".equals(response.getValueByJsonPath("$.msg")));
    }


    @Test(groups = {"P1"}, dataProvider = "param")
    @MethodAnotation(author = "liukang", createTime = "2018-09-11", updateTime = "2018-09-11", des = "token错误")
    public void ms_c_wxacCreateOrder_17(String token, String client, String caseId) {
        ResponseMap response = callService(token + "aaa", client, caseId);
        log.info(response.toString());
        AssertUtil.assertJSONPathExists(response, "$");
        Assert.assertTrue("用户未找到".equals(response.getValueByJsonPath("$.msg")));
    }
//以下为点评小程序商家券
   /* @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-27",updateTime = "2018-09-27",des = "使用商家抵用券下单")
    public void ms_c_wxacCreateOrder_18(String token,String client,String caseId){
        ResponseMap response = callService(dpToken ,dpClient,"ms_c_wxacCreateOrder_018","+xqrtEinhODeoGXFchLsqXC3QFH9y5cHj+Mp25v5S298EJ6l4A84wwDVJZmu01DZ");
        log.info(response.toString());
        Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
    }*/

  /*  @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-27",updateTime = "2018-09-27",des = "不满足用券金额进行下单")
    public void ms_c_wxacCreateOrder_19(String token,String client,String caseId){
        ResponseMap response = callService(dpToken ,dpClient,"ms_c_wxacCreateOrder_019","lYi4GWRiN1zFJS32DjjciHC3QFH9y5cHj+Mp25v5S282iKA0OCEYneHEeNIsgvmz#VZ7KrkHf3UeCAjMEbtCumzWJwi0iay3XmHTujcXiyQw=");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

  /*  @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-27",updateTime = "2018-09-27",des = "买单优惠与商家券同时使用")
    public void ms_c_wxacCreateOrder_20(String token,String client,String caseId){
        ResponseMap response = callService(dpToken ,dpClient,"ms_c_wxacCreateOrder_020","lYi4GWRiN1zFJS32DjjciHC3QFH9y5cHj+Mp25v5S282iKA0OCEYneHEeNIsgvmz#VZ7KrkHf3UeCAjMEbtCumzWJwi0iay3XmHTujcXiyQw=");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

  /*  @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-27",updateTime = "2018-09-27",des = "已使用的商家券，再次使用下单")
    public void ms_c_wxacCreateOrder_21(String token,String client,String caseId){
        ResponseMap response = callService(dpToken ,dpClient,"ms_c_wxacCreateOrder_021","lYi4GWRiN1zFJS32DjjciHC3QFH9y5cHj+Mp25v5S282iKA0OCEYneHEeNIsgvmz");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }
*/
   /* @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-27",updateTime = "2018-09-27",des = "智能支付门店使用商家券")
    public void ms_c_wxacCreateOrder_22(String token,String client,String caseId){
        ResponseMap response = callService(dpToken ,dpClient,"ms_c_wxacCreateOrder_021","lYi4GWRiN1zFJS32DjjciHC3QFH9y5cHj+Mp25v5S282iKA0OCEYneHEeNIsgvmz");
        log.info(response.toString());
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.msg");
    }*/

    public ResponseMap callService(String token, String client, String caseId) {
        return callService(token, client, caseId, null);
    }

    public ResponseMap callService(String token, String client, String caseId, String dpDealString) {
        JSONObject request = DBDataProvider.getRequest(_APIPATH, caseId);
        //JsonPathUtil.setJsonPathVaule(request, "$.body.token",token);
        request.getJSONObject("body").put("token", token);
        if (dpDealString != null) {
            // JsonPathUtil.setJsonPathVaule(request, "$.body.dpDealString", dpDealString);
            request.getJSONObject("body").put("dpDealString", dpDealString);
        }
        return DBCaseRequestUtil.post("env.api.meishi.weixin.hui.maiton.host", request);
    }
}
