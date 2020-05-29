package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
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
//import com.sankuai.web.trade.client.api.QueryServiceI;
//import com.sankuai.web.trade.client.query.QueryRefundRequest;
//import com.sankuai.web.trade.client.response.RefundQueryResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/queryServiceI.findRefundByUserIdAndOrderId",
//        type = "thrift",des="交易平台退款单查询接口" +
//        "文档：https://km.sankuai.com/page/63786624")
//@Slf4j
//public class TestFindRefundByUserIdAndOrderId {
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.query")
//    QueryServiceI queryServiceI;
//
//    String  casePath = "/queryServiceI.findRefundByUserIdAndOrderId";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-20",updateTime = "2018-08-20",des = "查询退款订单")
//    public void ms_c_findRefundByUserIdAndOrderId_1(JSONObject request, JSONObject expect){
//        RefundQueryResponse refundQueryResponse = callService("ms_c_findRefundByUserIdAndOrderId_1");
//        System.out.println(JSONObject.toJSONString(refundQueryResponse));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-20",updateTime = "2018-08-20",des = "订单不存在")
//    public void ms_c_findRefundByUserIdAndOrderId_2(JSONObject request, JSONObject expect){
//        RefundQueryResponse refundQueryResponse = callService("ms_c_findRefundByUserIdAndOrderId_2");
//        System.out.println(JSONObject.toJSONString(refundQueryResponse));
//        Assert.assertTrue(refundQueryResponse.getData().isEmpty());
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-20",updateTime = "2018-08-20",des = "userid错误为-1")
//    public void ms_c_findRefundByUserIdAndOrderId_3(JSONObject request, JSONObject expect){
//        RefundQueryResponse refundQueryResponse = callService("ms_c_findRefundByUserIdAndOrderId_3");
//        Assert.assertTrue(3006 == refundQueryResponse.getErrCode());
//        Assert.assertTrue("查询数据异常".equals(refundQueryResponse.getData().isEmpty()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-20",updateTime = "2018-08-20",des = "订单没有退款")
//    public void ms_c_findRefundByUserIdAndOrderId_4(JSONObject request, JSONObject expect){
//        RefundQueryResponse refundQueryResponse = callService("ms_c_findRefundByUserIdAndOrderId_4");
//        System.out.println(JSONObject.toJSONString(refundQueryResponse));
//        Assert.assertTrue(refundQueryResponse.getData().isEmpty());
//    }
//
//
//    private RefundQueryResponse callService(String caseId){
////        RequestsFromDB requests = new RequestsFromDB(casePath,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(casePath,caseId,"");
//        QueryRefundRequest queryRefundRequest = null;
//        try {
//            queryRefundRequest = OBJECT_MAPPER.readValue(requests.getDataFromDBInFastjson().getString("thrift_param"), QueryRefundRequest.class);
//            log.info(JSONObject.toJSONString(queryRefundRequest));
//        } catch (IOException e) {
//            log.info("解析json出错");
//        }
//        RefundQueryResponse refundQueryResponse = queryServiceI.findRefundByUserIdAndOrderId(queryRefundRequest);
//        return refundQueryResponse;
//    }
//}
