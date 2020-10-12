package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueDetail;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueOption;
import com.dianping.unified.coupon.issue.api.request.UnifiedCouponIssueRequest;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.google.common.collect.Lists;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.domain.DifferentRecord;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.LoadCashier;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.PayMockUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import com.sankuai.nibqa.trade.payMock.params.enums.Scene;
import com.sankuai.nibqa.trade.payMock.params.request.PayNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Author:      wuanran
 * Modified:    buyuqi
 * Date:        2020-01-02
 * 用例简介:     买单使用原价买单方案，使用平台券6元
 * 数据源:       poiId：97224769
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款；促销方式：使用红包平台券
 **/
@ClassAnnotation(author = "wuanran", depart = "C", des = "平台券")
@Slf4j
public class TestMerchantPromo_New extends TestDPLogin {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_shoppromo";

    //private String bisid = CommonLoginUtil.merchantAPPLogin();
    Integer source = 0;//1代表了使用买单优惠+优惠券，0元单;0代表了非0元单

    //商家端登录后获取
    public static String cashierOverviewPath = "/hui/ajax/cashieroverview";
    public static String orderoverviewPath = "/hui/ajax/orderoverview";
    public static String cashierqueryPath = "/hui/ajax/cashierquery";
    public static String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath = "/platformPath";
    OrderCheck orderCheck=new OrderCheck();


    public static String LOADUNIFIEDCASHIER = "ms_c_4Verify_loadUnifiedCashier_04";


    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;


    /**
     * coupon 金额6元
     * 提单时的cipher 多个使用 %23隔开
     * o6nT68Q0fiFN1Ov04inIQY0TYURST%2FiDjegrqsoweUHOAHeM0Zmz%2BZBUWVOCSwdrydqiNKTPs%2BuxJ0USCEM28U%2BsKm4%2BTKmgJm4esMqnB5w%3D%23ssKyhaTwI%2FXPrviN9Ha7990NpfKddu0%2FHuWVTxZdtjmJUT1BWPTEDCW7bZTgdej8LiE1ZHgyECyTp0rrbP4yXhYKt4914aYBGeugj9iQAo0e%2BSlShx%2BmRRreaSvwXMv3%235gg7O6x3yCWMV%2BinDIZVFjPoKPlhZTn7NZkR%2F6eftsVP1ZH%2BYDsDKN%2Fcbi787AgP8dwhSSGvwOF0aOqxlthMMQ%3D%3D
     * UnifiedCouponIssueRequest：{"userId":123344,"userType":"MT",operationToken:"26332572ACA5F1D2591E34B4B4AF4271","operator":"dengjia06","couponGroupIdList":[],"unifiedCouponGroupIdList":["549009064"]}
     */
    //String  doubleWriteMode = "NEW";
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1")
    @MethodAnotation(author = "wuanran", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_merchantPromo(String  doubleWriteMode) throws Exception{
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(mtUserId + "_1");
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
            Tracer.putContext("PAY_MOCK","TRUE");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(mtUserId+"_1");
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);

        //0.平台券查询
        String couponid = "23738010020695727";
        DeskCoupon deskCoupon = checkLoop.getCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk",couponid);
        if(deskCoupon == null){
            // userid 5002907380  发券
            UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
            couponIssueRequest.setUserType("MT");
            couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
            couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(979962070));
            couponIssueRequest.setOperator("qa-system");
            couponIssueRequest.setUserId(mtUserId);

            UnifiedCouponIssueOption option = new UnifiedCouponIssueOption();
            option.setCreditType(0);
            option.setNotifyType(0);
            UnifiedCouponIssueResponse unifiedCouponIssueResponse = unifiedCouponIssueTrustService.issueTrustCoupon(couponIssueRequest, option);

            BigDecimal couponAmount = BigDecimal.ZERO;
            if (unifiedCouponIssueResponse.getResultCode() == 0) {
                Optional<UnifiedCouponIssueDetail> detailOptional = unifiedCouponIssueResponse.getResult().getResult().stream().findFirst();
                if (detailOptional.isPresent()) {
                    UnifiedCouponIssueDetail issueDetail = detailOptional.get();
                    couponAmount = issueDetail.getDiscountAmount();
                    couponid = issueDetail.getUnifiedCouponId();
                }
            }
            deskCoupon = checkLoop.getCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk",couponid);
            Assert.assertTrue(deskCoupon != null,"获取平台券失败，可能原因：调用平台券接口超时或者查券失败");
        }
        //1、加载优惠台
        LoadCashier loadCashier = LoadCashier.builder()
                .caseId(LOADUNIFIEDCASHIER)
                .token(mtToken)
                .userAgent(mtClient).build();
        CouponProduct couponProduct = loadCashier.parseCouponOfferId().orElse(null);
        //创建订单
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(mtToken,mtClient,CASEID,couponProduct,deskCoupon,source);
        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        String orderId = createResult.getOrderId().toString();
        String tradeNo = createResult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");
        log.info("创单成功！order:{}", JSON.toJSONString(createResult));

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(String.valueOf(orderId));
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单下单后校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,maitonQueryOrderResponse);

        //创建订单成功diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_merchantPromo创建订单成功diff");

        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单侧支付校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,createOrderResponse);

        //支付成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_merchantPromo支付成功订单diff");

        //4、支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,mtToken,mtClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,mtToken,"MT","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }

         // 直接发起退款
        HuiRefund huiRefund = HuiRefund.builder()
                .refundFlowService(refundFlowService)
                .orderId(Long.valueOf(orderId))
                .userId(mtUserId)
                .operator("qa-autocase").build();

        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));

        //退款mock
        Long amount = createOrderResponse.getOrderDTO().getUserAmount().longValue() * 100;
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        // 交易平台接收退款回调后更改订单状态可能会存在滞后或者读写延迟问题，故增加延迟等待时间
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("平台校验已消费退款前延迟2s时异常"+e.toString());
        }

        //平台侧退款校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //退款成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_merchantPromo退款成功订单diff");

    }
}
