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
//import com.sankuai.web.trade.client.api.RefundServiceI;
//import com.sankuai.web.trade.client.dto.clientobject.OrderCO;
//import com.sankuai.web.trade.client.request.RefundApplyRequest;
//import com.sankuai.web.trade.client.response.RefundResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/refundServiceI.refundApply",
//        type = "thrift",des="交易平台用户申请退款接口" +
//        "文档：https://km.sankuai.com/page/108800762")
//@Slf4j
//public class TestRefundApply {
//    RefundResponse refundResponse;
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.refund")
//    RefundServiceI refundServiceI;
//
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.query")
//    QueryServiceI queryServiceI;
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    String _CASEPATH = "/refundServiceI.refundApply";
//
//    long orderId = 11010282361L;
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-14",updateTime = "2018-11-14",des = "未发货申请退款")
//    public void ms_c_refundApply_1(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_1",11010426361L);//11010287361
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "已发货仅退款-用户已确认收货")
//    public void ms_c_refundApply_2(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_2",11010412361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "已发货仅退款-用户未确认收货")
//    public void ms_c_refundApply_3(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_3",11014150361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "已发货退货退款-用户已确认收货")
//    public void ms_c_refundApply_4(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_4",11009345361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "已发货退货退款-用户未确认收货")
//    public void ms_c_refundApply_5(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_5",11014151361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = " partnerId 为空")
//    public void ms_c_refundApply_6(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_6",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "outRefundNo为空")
//    public void ms_c_refundApply_7(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_7");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "notifyUrl为空")
//    public void ms_c_refundApply_8(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_8",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "bizInfo为空")
//    public void ms_c_refundApply_9(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_9",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "refundOrderInfo为空")
//    public void ms_c_refundApply_10(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_10");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "orderId为空")
//    public void ms_c_refundApply_11(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_11");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "userId 为空")
//    public void ms_c_refundApply_12(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_12",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "refundReason为空")
//    public void ms_c_refundApply_13(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_13",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "refundType为空")
//    public void ms_c_refundApply_14(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_14",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "refundItemInfoList为空")
//    public void ms_c_refundApply_15(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_15");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
////此条有待确认，代码校验的是等于0，目前case写的-1  //退款金额验证失败
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "businessAmountFee为0")
//    public void ms_c_refundApply_16(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_16",11014053361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "mtAmountFee不为0")
//    public void ms_c_refundApply_17(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_17",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-15",updateTime = "2018-11-15",des = "refundType传入非指定类型")
//    public void ms_c_refundApply_18(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_18",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "用户与订单不符")
//    public void ms_c_refundApply_19(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_19",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款用户与订单号不匹配".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " itemId 与订单中不一致")
//    public void ms_c_refundApply_20(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_20");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款明细id与订单明细id不匹配".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "订单未支付")
//    public void ms_c_refundApply_21(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_21",11010281361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单状态不符合申请退款条件".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "未发货退款：订单已支付，但是已发货")
//    public void ms_c_refundApply_22(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_22",11014054361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单状态不符合申请退款条件".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "已发货仅退款：订单已支付，但是未发货")
//    public void ms_c_refundApply_23(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_23",11010266361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单状态不符合申请退款条件".equals(refundResponse.getErrMessage()));
//    }
//
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "已发货退货退款：订单已支付，但是未发货")
//    public void ms_c_refundApply_24(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_24",11010266361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单状态不符合申请退款条件".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = "已申请退款订单，再次申请退款(outrefundno与前一次相同)")
//    public void ms_c_refundApply_25(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_25");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//    //只能等流程通了以后测试
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 存在已退款成功，再次申请退款")
//    public void ms_c_refundApply_26(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_26",11009274361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("订单已全额退款".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 存在退款失败订单（商家确认拒绝状态），再次申请退款(outrefundno与前一次相同)")
//    public void ms_c_refundApply_27(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_27");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 存在退款失败订单（商家拒绝发货状态），再次申请退款(outrefundno与前一次相同)")
//    public void ms_c_refundApply_27_1(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_27_1");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("最终处理失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单已申请退款，再次申请退款(outrefundno与前一次不相同)")
//    public void ms_c_refundApply_28(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_28",11010270361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单已确认通过，再次申请退款(outrefundno与前一次不相同)")
//    public void ms_c_refundApply_29(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_29",11010288361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//    //需走流程
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单为商品寄回状态，再次申请退款(outrefundno与前一次不相同)")
//    public void ms_c_refundApply_30(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_30",11014059361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单为已审核状态，再次申请退款(outrefundno与前一次不相同)")
//    public void ms_c_refundApply_31(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_31",11014076361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//    //需走流程
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单为退款成功，再次申请退款(outrefundno与前一次不相同)")
//    public void ms_c_refundApply_32(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_32",11014061361L);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("重复申请".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 订单退款总金额 != 商家承担退款金额 + 美团承担退款金额")
//    public void ms_c_refundApply_33(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_33",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 订单item退款总金额 != 商家承担退款金额 + 美团承担退款金额")
//    public void ms_c_refundApply_34(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_34",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单商家承担总金额!=各item商家承担总金额之和")
//    public void ms_c_refundApply_35(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_35",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " 退款单美团承担总金额!=各item美团承担总金额之和")
//    public void ms_c_refundApply_36(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_36",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
////totalfee现在是计算出来的不是自己传的
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " order退款总金额> 订单总金额")
//    public void ms_c_refundApply_37(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_37",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
////暂时木有case，payfee字段还没有添加上，上一条case会先中校验所以此条case没有中相应的校验（原因是totalfee是通过payfee计算的）
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-16",updateTime = "2018-11-16",des = " order实付总金额>订单支付金额")
//    public void ms_c_refundApply_38(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_38",orderId);
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("退款金额验证失败".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},threadPoolSize = 3 ,invocationCount = 3,dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-23",updateTime = "2018-11-23",des = "未发货申请退款,并发执行，只能一条成功")
//    public void ms_c_refundApply_39(JSONObject request, JSONObject expect){
//        callService("ms_c_refundApply_39",11009327361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//    }
//
//    public void callService(String caseId,long orderId){
//        callService(caseId,orderId,0,0);
//    }
//
//    private void callService(String caseId,long orderId,long userId,int refundType){
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
//        RefundApplyRequest refundRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        OrderCO orderCO = queryServiceI.findOrderByOrderId(orderId);
////        long payRequestId = orderCO.getPayRequestId();
//        long coOrderId = orderCO.getOrderId();
//        long orderItemId = orderCO.getOrderItemCOs().get(0).getOrderItemId();
////        PayRequestCO payRequestCO = null;
////        try{
////            payRequestCO = queryServiceI.findPayByPayRequestId(payRequestId);
////
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////        String payTradeNo = payRequestCO.getPayTradeNo();
//
//        try {
//            refundRequest = OBJECT_MAPPER.readValue(s, RefundApplyRequest.class);
////            refundRequest.getRefundOrderInfo().setTradeNo(payTradeNo);
////            refundRequest.getRefundOrder().setPayRequestId(payRequestId);
//            if(userId > 0){
//                refundRequest.getRefundOrderInfo().setUserId(userId);
//            }
//            if(refundType > 0){
//                refundRequest.getRefundOrderInfo().setRefundType(refundType);
//            }
//            refundRequest.getRefundOrderInfo().setOrderId(coOrderId);
//            refundRequest.setOutRefundNo(System.currentTimeMillis() + "-" + coOrderId);
//            refundRequest.getRefundOrderInfo().getRefundItemInfoList().get(0).setItemId(orderItemId);
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundRequest:"  + JSONObject.toJSONString(refundRequest));
//        refundResponse = refundServiceI.refundApply(refundRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//
//    private void callService(String caseId){
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
//        RefundApplyRequest refundRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        try {
//            refundRequest = OBJECT_MAPPER.readValue(s, RefundApplyRequest.class);
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundRequest:"  + JSONObject.toJSONString(refundRequest));
//        refundResponse = refundServiceI.refundApply(refundRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//}
