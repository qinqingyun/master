package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.ClassAnnotation;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.MethodAnotation;
//import com.sankuai.web.trade.client.api.OrderCreateServiceI;
//import com.sankuai.web.trade.client.request.OrderCreateRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sankuai.web.trade.client.response.OrderCreateResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/orderCreateServiceI.create",
//        type = "thrift",des="代理商创单" +
//        "文档：https://km.sankuai.com/page/61242255")
//@Slf4j
//public class TestTradeCreateOrder {
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.order")
//    OrderCreateServiceI orderCreateServiceI;
//
//    String CASEPATH = "/orderCreateServiceI.create";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "多个sku创建订单")
//    public void ms_c_tradeCreateOrder_1(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_001");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getTradeNo().isEmpty());
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "创建订单只使用一个sku")
//    public void ms_c_tradeCreateOrder_2(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_002");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getTradeNo().isEmpty());
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "创建订单传入重复的sku信息")
//    public void ms_c_tradeCreateOrder_3(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_003");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty());  //返回错误信息未添加
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "交易总金额和sku总金额不等")
//    public void ms_c_tradeCreateOrder_4(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_004");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("REQUEST_ERROR_PARAM".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("income not equal outcome".equals(orderCreateResponse.getErrMsg()));
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "总金额和支付金额不等")
//    public void ms_c_tradeCreateOrder_5(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_005");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("REQUEST_ERROR_PARAM".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("income not equal trade total".equals(orderCreateResponse.getErrMsg()));
//    }
////还未实现
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "使用已支付成功的outNo再次下单")
//    public void ms_c_tradeCreateOrder_6(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_006");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-11",updateTime = "2018-08-11",des = "outNo有相应预下单记录，再次下单")
//    public void ms_c_tradeCreateOrder_7(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_tradeCreateOrder_007");
//        log.info("orderCreateResponse:" + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getTradeNo().isEmpty());
//    }
//
//     OrderCreateResponse callService(String caseId){
////        RequestsFromDB request = new RequestsFromDB(CASEPATH,caseId,"");
////        RequestsFromDB request = new RequestsFromDB(CASEPATH,caseId,"");
//        OrderCreateRequest orderCreateRequest = null;
////        long time = System.currentTimeMillis();
////        orderCreateRequest.getCommonInfo().setOutNo("ms_c_createOrder_" + time);
//        try {
//            orderCreateRequest = OBJECT_MAPPER.readValue(request.getDataFromDBInFastjson().getString("thrift_param"), OrderCreateRequest.class);
//        } catch (IOException e) {
//            log.error("转化json出错" + e);
//        }
//        log.info("orderCreateRequest:"  + com.alibaba.fastjson.JSONObject.toJSONString(orderCreateRequest));
//         OrderCreateResponse orderCreateResponse = orderCreateServiceI.create(orderCreateRequest);
//        return orderCreateResponse;
//
//    }
//
//
//
//
//}
