package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "hui/cashier/ajaxcreateorder",
        type = "http",des="m站下单")
@Slf4j
@HTTPAPI(apiPath = "hui/cashier/ajaxcreateorder")
public class TestAjaxCreateOrder {
    protected final Log logger = LogFactory.getLog(this.getClass());

    String apiPath = "hui/cashier/ajaxcreateorder";
//String _HOST ="localhost";
    String token = CommonLoginUtil.ms_c_DPLogin_02();
    String mtToken = CommonLoginUtil.ms_c_MTLogin_01();
    String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
    int couponofferId = CreateOrderUtil.loadUnicashier(token,userAgent,"ms_c_loadunicashier_01");
    Map<String,String> orderCreateResult = new HashMap();

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "正确创单")
    public void ms_c_ajaxcreateorder_01(JSONObject request, JSONObject expect){
        orderCreateResult = CreateOrderUtil.ajaxCreateOrder(token,userAgent,"ms_c_ajaxcreateorder_01",couponofferId);
        log.info(orderCreateResult.toString());
        Assert.assertTrue("创单成功".equals(orderCreateResult.get("msg")));
        Assert.assertTrue(!orderCreateResult.get("payToken").isEmpty());
        Assert.assertTrue(!orderCreateResult.get("tradeNo").isEmpty());
        Assert.assertTrue(!orderCreateResult.get("serializedId").isEmpty());
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "shopid小于等于0")
    public void ms_c_ajaxcreateorder_02(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_02",couponofferId,"");
        Assert.assertTrue("shopId不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "userAmount为null")
    public void ms_c_ajaxcreateorder_03(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_03",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "originAmount为null")
    public void ms_c_ajaxcreateorder_04(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_04",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "originAmount小于1元")
    public void ms_c_ajaxcreateorder_05(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_05",couponofferId,"");
        Assert.assertTrue("消费金额不能小于1元哦".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "userAmount精度超过2位小数")
    public void ms_c_ajaxcreateorder_06(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_06",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "userAmount小于0")
    public void ms_c_ajaxcreateorder_07(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_07",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "originAmount精度超过2位小数")
    public void ms_c_ajaxcreateorder_08(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_08",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "noDiscountAmount精度超过2位小数")
    public void ms_c_ajaxcreateorder_09(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_09",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "noDiscountAmount小于0")
    public void ms_c_ajaxcreateorder_10(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_10",couponofferId,"");
        Assert.assertTrue("输入金额不正确！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "noDiscountAmount金额超过originAmount")
    public void ms_c_ajaxcreateorder_11(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_11",couponofferId,"");
        Assert.assertTrue("您输入的总金额小于不参与优惠金额，请确认输入！".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "点评门店没有买单")
    public void ms_c_ajaxcreateorder_12(JSONObject request, JSONObject expect){
        ResponseMap response = callService(token,userAgent,"ms_c_ajaxcreateorder_12",couponofferId,"");
        Assert.assertTrue("下单失败".equals(response.getValueByJsonPath("$.msg")));
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-12-25",updateTime = "2018-12-25",des = "美团门店没有买单")
    public void ms_c_ajaxcreateorder_13(JSONObject request, JSONObject expect){
//        ResponseMap response = callService(mtToken,userAgent,"ms_c_ajaxcreateorder_13",0,"");

        String cookie = "mt_token="+mtToken;
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
//        Map<String,String> params =  new HashMap<>();
//        params.put("mt_userId","1231");
//        request.setRequestUrlParams(params);
//        request.setRequestBody(params);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        Assert.assertTrue("下单失败".equals(response.getValueByJsonPath("$.msg")));
    }

    public ResponseMap callService(String token, String userAgent, String caseId, int coupOfferId, String dpDealString){
        JSONObject request = DBDataProvider.getRequest(apiPath,caseId);
        String cookie = "dper="+token;
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
        Map<String,String> param = new HashMap<>();
        String offerIdStr = "";
        if(coupOfferId > 0){
            offerIdStr = "[{\"offerId\":"+ coupOfferId +",\"productType\":202}]";
            param.put("offerIdStr",offerIdStr);
        }
        if(!dpDealString.isEmpty()){
            param.put("dpDealString",dpDealString);
        }
        if(coupOfferId > 0 || !dpDealString.isEmpty()){
			JsonPathUtil.setJsonPathVaule(request, "$.body.offerIdStr",offerIdStr);
			JsonPathUtil.setJsonPathVaule(request, "$.body.dpDealString",dpDealString);
        }
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        return response;
    }
}
