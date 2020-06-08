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
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.LoadCashier;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
public class TestDpMerchantPromo  extends TestDPLogin {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_merchantpromo_02";

    String platformPath = "/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    public static String LOADUNIFIEDCASHIER = "ms_c_4Verify_loadUnifiedCashier_05";
    String  doubleWriteMode="NEW";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    Integer source = 0 ;//1代表了使用买单优惠+优惠券，0元单；0代表非0原单

    @Test(groups = "P1")
    @MethodAnotation(author = "buyuqi", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_DpMerchantPromo() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(mtUserId+"_1");
        }
        //0.平台券查询 userid  9007199254760212  Dp151
        String couponid = "15849785805690212000001";
        DeskCoupon deskCoupon = checkLoop.getCouponCipher(dpToken,dpClient,"ms_c_hui_gethuipromodesk_03",couponid);
        if(deskCoupon == null){
            UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
            couponIssueRequest.setUserType("DP");
            couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
            couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(461937051));
            couponIssueRequest.setOperator("qa-system");
            couponIssueRequest.setUserId(Long.valueOf(dpUserId));

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
            //提单页券列表
            deskCoupon = checkLoop.getCouponCipher(dpToken,dpClient,"ms_c_hui_gethuipromodesk_03",couponid);
            Assert.assertTrue(deskCoupon != null,"获取平台券失败，可能原因：调用平台券接口超时或者查券失败");
        }
        //HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(dpToken).useCardflag(UseCard.USE_PLATFORM_CARD).client(dpClient).caseid("ms_c_hui_gethuipromodesk_01").build();
        //DeskCoupon deskCoupon = promoDesk.shopCouponCipher(couponid).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));
        //1、加载优惠台
        LoadCashier loadCashier = LoadCashier.builder().caseId("ms_c__dp_loadUnifiedCashier_02").token(dpToken).userAgent(dpClient).build();
        CouponProduct couponProduct = loadCashier.parseCouponOfferId().orElse(null);
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(dpToken,dpClient,CASEID,couponProduct,deskCoupon,source);

//        HuiCreateOrder createOrder = HuiCreateOrder.builder()
//                .token(dpToken)
//                .userAgent(dpClient)
//                .caseid(CASEID)
//                .couponProduct(loadCashier.parseCouponOfferId().orElse(null))
//                .deskcoupon(deskCoupon)
//                .build();
//        HuiCreateOrderResult createResult = createOrder.requestCreate();
        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        String orderId = createResult.getOrderId().toString();
        String tradeNo = createResult.getTradeNo();

        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(String.valueOf(orderId));
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform_02");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单下单后校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,maitonQueryOrderResponse);

        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform_02");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单侧支付校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,createOrderResponse);

        //4、支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,dpToken,dpClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情
        String orderDetail = checkLoop.getOrderDetail(orderId,dpToken,"DP","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        // 直接发起退款
        HuiRefund huiRefund = HuiRefund.builder()
                .refundFlowService(refundFlowService)
                .orderId(Long.valueOf(orderId))
                .userId(Long.valueOf(dpUserId))
                .operator("qa-autocase").build();

        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));

        //平台侧退款校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_merchantPromo_platform_02");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,"5000050967");

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

    }
}
