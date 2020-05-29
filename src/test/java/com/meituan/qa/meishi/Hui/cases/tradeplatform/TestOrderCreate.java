package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//import com.api.PayApi;
//import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
//import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
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
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/api/tradeelectric/ordercreate",
//        type = "http",des="餐电商业务下单接口" +
//        "文档：https://km.sankuai.com/page/63976547")
//@Slf4j
//@HTTPAPI(apiPath = "") 
//public class TestOrderCreate {
//    protected  final Log logger = LogFactory.getLog(this.getClass());
//    String casePath = "/api/tradeelectric/ordercreate";
//    String mtToken = CommonLoginUtil.ms_c_MTLogin_01();
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "一个sku正常下单")
//    public void ms_c_orderCreate_1(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response:" + response);
//        long orderid = response.getJSONArrayByJsonPath("data.order_ids").getLong(0);
//        String payToken = response.getValueByJsonPath("data.pay_token");
//        String tradeNo = response.getValueByJsonPath("data.trade_no");
//        log.info("orderid: " + orderid );
//        log.info("tradeNo: " + tradeNo);
//        log.info("payToken: " + payToken);
//        payTest(tradeNo,payToken);
//        Assert.assertTrue(!payToken.isEmpty());
//    }
//
//    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "多个sku正常下单")
//    public void ms_c_orderCreate_2(JSONObject request, JSONObject expect){
//        Response response = callService(request);
//        log.info("response:" + response);
//        Assert.assertTrue(!response.getValueByJsonPath("data.pay_token").isEmpty());
//        Assert.assertTrue(!response.getValueByJsonPath("data.trade_no").isEmpty());
//    }
//
//    private ResponseMap callService(JSONObject request){
////        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,host,"",casePath,caseId);
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("token",mtToken);
//		JsonPathUtil.setJsonPathVaule(request, "$.params.token",mtToken);
//
//		return request.post(); = DBCaseRequestUtil.post("env.tradePlatform.createOrder.host", request);
//    }
//
//    private void payTest(String tradeNo,String payToken){
//        PayApi obj = new PayApi("stable.pay.test.sankuai.com");
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("tradeno", tradeNo);
//        params.put("pay_token", payToken);
//        // params.put("nb_platform", "..."); //可传可不传，默认值为www(即B端PC收银台)；可选值有www和touch。如果是B端i版收银台请传nb_platform=touch
//        try {
//            boolean ret = obj.doPayForBusiness(params);//支付成功会返回true
//            System.out.println(ret);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
