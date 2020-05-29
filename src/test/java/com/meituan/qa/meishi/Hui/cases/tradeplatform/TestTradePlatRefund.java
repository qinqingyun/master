package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
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
//import com.sankuai.web.trade.client.api.RefundServiceI;
//import com.sankuai.web.trade.client.dto.clientobject.OrderCO;
//import com.sankuai.web.trade.client.dto.clientobject.PayRequestCO;
//import com.sankuai.web.trade.client.request.RefundRequest;
//import com.sankuai.web.trade.client.response.RefundResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/refundServiceI.refund",
//        type = "thrift",des="交易平台退款接口" +
//        "文档：https://km.sankuai.com/page/62161167")
//@Slf4j
//public class TestTradePlatRefund {
//    RefundResponse refundResponse;
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.refund")
//    RefundServiceI refundServiceI;
//
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.query")
//    QueryServiceI queryServiceI;
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    String CASEPATH = "/refundServiceI.refund";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "申请退款")
//    public void ms_c_tradePlatRefund_1(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_1",11014191361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "已存在退款成功订单再次申请退款")
//    public void ms_c_tradePlatRefund_2(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_2",1000001058828L);
//        Assert.assertTrue(3007 == refundResponse.getErrCode());
//        Assert.assertTrue("该订单有未完成或退款成功的退款单".equals(refundResponse.getErrMessage()));
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "退款总金额 != 商家承担退款金额 + 美团承担退款金额")
//    public void ms_c_tradePlatRefund_3(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_3",1000000169828L);
//        Assert.assertTrue(3003 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "order退款总金额> 订单总金额")
//    public void ms_c_tradePlatRefund_4(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_4",1000000169828L);
//        Assert.assertTrue(3003 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "order实付总金额>订单支付金额")
//    public void ms_c_tradePlatRefund_5(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_5",1000000169828L);
//        Assert.assertTrue(3003 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "orderitem退款总金额> 订单总金额")
//    public void ms_c_tradePlatRefund_6(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_6",1000000169828L);
//        Assert.assertTrue(3003 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "orderitem实付总金额>订单支付金额")
//    public void ms_c_tradePlatRefund_7(JSONObject request, JSONObject expect){
//        callService("ms_c_tradePlatRefund_7",1000000169828L);
//        Assert.assertTrue(3003 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
////未执行
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "已存在退款失败订单，再次申请退款")
//    public void ms_c_tradePlatRefund_8(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_8",1000000169828L);
//        Assert.assertTrue(3000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "订单未支付")
//    public void ms_c_tradePlatRefund_9(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_9",1000001145828L);
//        Assert.assertTrue(3004 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单状态不符合申请退款条件".equals(refundResponse.getErrMessage()));
//    }
//
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "已消费退款美团承担")
//    public void ms_c_tradePlatRefund_10(){
//
//        callService("ms_c_tradePlatRefund_10",1000000169828L);
//        Assert.assertTrue(3000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-19",updateTime = "2018-08-19",des = "已消费退款美团商家共同承担")
//    public void ms_c_tradePlatRefund_11(){
//
//        callService("ms_c_tradePlatRefund_11",1000000169828L);
//        Assert.assertTrue(3000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "parnterId小于0")
//    public void ms_c_tradePlatRefund_12(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_12",1000000169828L);
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "RefundOrder为null")
//    public void ms_c_tradePlatRefund_13(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_13");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//    //有问题 重新验证一下
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "fundsInfoCOList为null")
//    public void ms_c_tradePlatRefund_14(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_14");
//        Assert.assertTrue(4000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "bizInfo为null")
//    public void ms_c_tradePlatRefund_15(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_15");
//        Assert.assertTrue(2 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "tradeInfo为null")
//    public void ms_c_tradePlatRefund_16(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_16");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "OutRefundNo为null")
//    public void ms_c_tradePlatRefund_17(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_17");
//        Assert.assertTrue(100 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "orderid为空")
//    public void ms_c_tradePlatRefund_18(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_18");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "userid为空")
//    public void ms_c_tradePlatRefund_19(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_19");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundReason为空")
//    public void ms_c_tradePlatRefund_20(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_20");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundReasonCode为空")
//    public void ms_c_tradePlatRefund_21(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_21");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "payRequestId为空")
//    public void ms_c_tradePlatRefund_22(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_22");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "payTime为空")
//    public void ms_c_tradePlatRefund_23(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_23");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "RefundType为空或非指定值")
//    public void ms_c_tradePlatRefund_24(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_24");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "rollbackResource为非指定值")
//    public void ms_c_tradePlatRefund_25(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_25");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundTotalFee为负数")
//    public void ms_c_tradePlatRefund_26(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_26");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundDeliveryFee为负数")
//    public void ms_c_tradePlatRefund_27(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_27");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundGoodsFee为负数")
//    public void ms_c_tradePlatRefund_28(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_28");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundPayFee为负数")
//    public void ms_c_tradePlatRefund_29(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_29");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundDiscountFee为负数")
//    public void ms_c_tradePlatRefund_30(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_30");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "businessAmountFee为负数")
//    public void ms_c_tradePlatRefund_31(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_31");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "mtAmountFee为负数")
//    public void ms_c_tradePlatRefund_32(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_32");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundRequest为null")
//    public void ms_c_tradePlatRefund_33(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_33");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "refundRequest中tradeNo为null")
//    public void ms_c_tradePlatRefund_34(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_34");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList为null")
//    public void ms_c_tradePlatRefund_35(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_35");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中quantity为null")
//    public void ms_c_tradePlatRefund_36(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_36");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中itemId为null")
//    public void ms_c_tradePlatRefund_37(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_37");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中refundTotalFee为负数")
//    public void ms_c_tradePlatRefund_38(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_38");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中refundDiscountFee为负数")
//    public void ms_c_tradePlatRefund_39(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_39");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中refundPayFee为负数")
//    public void ms_c_tradePlatRefund_40(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_40");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
////写重复了
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中refundPayFee为负数")
//    public void ms_c_tradePlatRefund_41(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_41");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中businessAmountFee为负数")
//    public void ms_c_tradePlatRefund_42(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_42");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中mtAmountFee为负数")
//    public void ms_c_tradePlatRefund_43(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_43");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "itemCOList中productId为空")
//    public void ms_c_tradePlatRefund_44(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_44");
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
////退款失败以及异常等情况使用，不自动化
//	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-28",updateTime = "2018-08-28",des = "")
//    public void ms_c_tradePlatRefund_45(JSONObject request, JSONObject expect){
//
//        callService("ms_c_tradePlatRefund_45",11014191361L);//11014194361
//        Assert.assertTrue(1000 == refundResponse.getErrCode());
////        Assert.assertTrue(!refundResponse.isSuccess());
////        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//
//
//
//    private void callService(String caseId,long orderId){
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
//        RefundRequest refundRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        OrderCO orderCO = queryServiceI.findOrderByOrderId(orderId);
//        long payRequestId = orderCO.getPayRequestId();
//        long coOrderId = orderCO.getOrderId();
//        long orderItemId = orderCO.getOrderItemCOs().get(0).getOrderItemId();
//        PayRequestCO payRequestCO = null;
//        try{
//            payRequestCO = queryServiceI.findPayByPayRequestId(payRequestId);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String payTradeNo = payRequestCO.getPayTradeNo();
//
//        try {
//            refundRequest = OBJECT_MAPPER.readValue(s, RefundRequest.class);
//            refundRequest.getRefundOrder().getRefundRequest().setTradeNo(payTradeNo);
//            refundRequest.getRefundOrder().setPayRequestId(payRequestId);
//            refundRequest.getRefundOrder().setOrderId(coOrderId);
//            refundRequest.getRefundOrder().setOutRefundNo(System.currentTimeMillis() + "-" + coOrderId);
////            refundRequest.getRefundOrder().setOutRefundNo("");
//            refundRequest.getRefundOrder().getItemCOList().get(0).setItemId(orderItemId);
////线上st
////            refundRequest.getFundsInfoCOList().get(0).setJsonFundsExt("{\"money\":\"0.01\",\"orderid\":" + coOrderId +",\"iph_pay_merchant_no\":\"11000016845479\",\"trade_no\":\""+ payTradeNo +"\",\"url\":\"http://mpay.vip.sankuai.com/trade/refund\"}");
////            refundRequest.getFundsInfoCOList().get(1).setJsonFundsExt("{\"balanceBusinessId\":"+ orderItemId +",\"jsonFinanceInfo\":\"{\\\"record_company1\\\":102,\\\"client_id1\\\":\\\"41099621\\\",\\\"Invoice1\\\":\\\"\\\",\\\"client_id2\\\":\\\"MIS\\\",\\\"BGBU1\\\":\\\"12010\\\",\\\"product_type1\\\":\\\"1602\\\"}\",\"balanceType\":2,\"jsonBalanceCheck\":\"{\\\"client_id1\\\":41099621,\\\"client_id2\\\":\\\"MIS\\\"}\"}");
////线下环境
//            refundRequest.getFundsInfoCOList().get(0).setJsonFundsExt("{\"money\":\"0.5\",\"orderid\":" + coOrderId +",\"iph_pay_merchant_no\":\"11000001560545\",\"trade_no\":\""+ payTradeNo +"\",\"url\":\"http://stable.pay.st.sankuai.com/trade/refund\"}");
//            refundRequest.getFundsInfoCOList().get(1).setJsonFundsExt("{\"balanceBusinessId\":"+ orderItemId +",\"jsonFinanceInfo\":\"{\\\"record_company1\\\":102,\\\"client_id1\\\":\\\"40002046\\\",\\\"Invoice1\\\":\\\"\\\",\\\"client_id2\\\":\\\"MIS\\\",\\\"BGBU1\\\":\\\"12010\\\",\\\"product_type1\\\":\\\"1602\\\"}\",\"balanceType\":2,\"jsonBalanceCheck\":\"{\\\"client_id1\\\":40002046,\\\"client_id2\\\":\\\"MIS\\\"}\"}");
//
////全商家承担参数（st和线上）
//   /*         refundRequest.getFundsInfoCOList().get(1).setJsonFundsExt("{\"bizType\":\"10\",\"data\":{\\\"buyPrice\\\":0.98,\\\"serviceFee\\\":0.02,\\\"bizType\\\":\\\"10\\\",\\\"outerCustomerSource\\\":1,\\\"orderId\\\":1000000176828,\\\"salePrice\\\":0.01,\\\"dealId\\\":53174352," +
//                    "\\\"refundTime\\\":1536130390,\\\"bizCost\\\":0.0,\\\"buyTime\\\":1536128762,\\\"outerCustomerId\\\":40926342,\\\"pcRemark\\\":\\\"\\\",\\\"bizLogShowRule\\\":1,\\\"bizUniqNum\\\":\\\"1536130390504-1000000176828\\\",\\\"deliveryFee\\\":0,\\\"dataTime\\\":1536130390,\\\"contractId\\\":\\\"a6f294af-1da5-4fc8-9db5-97cfbd0fe46c\\\",\\\"useTime\\\":1536129106,\\\"bizRefund\\\":0.99,\\\"actType\\\":61,\\\"appRemark\\\":\\\"\\\",\\\"poiId\\\":159847351},\"dataTime\":1536130390,\"actType\":61,\"useCredit\":false}");
//        */} catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundRequest:"  + JSONObject.toJSONString(refundRequest));
//        refundResponse = refundServiceI.refund(refundRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//
//    private void callService(String caseId){
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
//        RefundRequest refundRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        try {
//            refundRequest = OBJECT_MAPPER.readValue(s, RefundRequest.class);
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundRequest:"  + JSONObject.toJSONString(refundRequest));
//        refundResponse = refundServiceI.refund(refundRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//}
