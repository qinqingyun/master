package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.toolchain.mario.annotation.HTTPAPI;
//import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
//import com.meituan.toolchain.mario.model.ResponseMap;
//import com.meituan.toolchain.mario.util.JsonPathUtil;
//import com.meituan.toolchain.mario.annotation.ThriftAPI;
//import com.meituan.qa.meishi.util.ClassAnnotation;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.toolchain.mario.annotation.HTTPAPI;
//import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
//import com.meituan.toolchain.mario.model.ResponseMap;
//import com.meituan.toolchain.mario.util.JsonPathUtil;
//import com.meituan.toolchain.mario.annotation.ThriftAPI;
//import com.meituan.qa.meishi.util.MethodAnotation;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/api/tradeelectric/gopay",
//        type = "http",des="餐电商待支付下单接口" +
//        "文档：https://km.sankuai.com/page/68441055")
//@Slf4j
//@HTTPAPI(apiPath = "") 
//public class TestGoPay {
//    protected final Log logger = LogFactory.getLog(this.getClass());
//    String casePath = "/api/tradeelectric/gopay";
//    String mtToken = CommonLoginUtil.ms_c_MTLogin_01();
//    private RequestsFromDB request;
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "正常获取支付token")
//    public void ms_c_goPay_1(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response: " + response);
//        Assert.assertTrue(!response.getValueByJsonPath("$.data.pay_token").isEmpty());
//        Assert.assertTrue(!response.getValueByJsonPath("$.data.trade_no").isEmpty());
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "token错误")
//    public void ms_c_goPay_2(JSONObject request, JSONObject expect){
////        request = new RequestsFromDB(Constants.Protocol.http,host,"",casePath,"ms_c_goPay_2");
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("token","1231");
//		JsonPathUtil.setJsonPathVaule(request, "$.params.token","1231");
//		ResponseMap response = DBCaseRequestUtil.post("env.tradePlatform.createOrder.host", request);
//        log.info("response: " + response);
//        Assert.assertTrue("用户未登陆".equals(response.getValueByJsonPath("$.msg")));
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "订单已过期")
//    public void ms_c_goPay_3(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response: " + response);
//        Assert.assertTrue("订单已过期".equals(response.getValueByJsonPath("$.msg")));
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "订单已支付")
//    public void ms_c_goPay_4(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response: " + response);
//        Assert.assertTrue("订单状态不正确".equals(response.getValueByJsonPath("$.msg")));
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "订单不存在")
//    public void ms_c_goPay_5(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response: " + response);
//        Assert.assertTrue("未找到订单".equals(response.getValueByJsonPath("$.msg")));
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "订单为代理商订单")
//    public void ms_c_goPay_6(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response: " + response);
//        Assert.assertTrue("未找到订单".equals(response.getValueByJsonPath("$.msg")));
//    }
//
//
//
//    private ResponseMap callService(JSONObject request){
////        request = new RequestsFromDB(Constants.Protocol.http,host,"",casePath,caseId);
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("token",mtToken);
//		JsonPathUtil.setJsonPathVaule(request, "$.params.token",mtToken);
//
//		return request.post(); = DBCaseRequestUtil.post("env.tradePlatform.createOrder.host", request);
//    }
//}
