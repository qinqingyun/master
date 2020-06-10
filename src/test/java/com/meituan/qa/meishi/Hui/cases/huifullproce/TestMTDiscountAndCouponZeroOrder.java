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
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.Task;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.MaitonHongbaoTService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2020-02-04
 * 用例简介:     使用买单满减优惠+平台券0元单
 * 数据源:       poiId：95191712
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：满减买单（10-5）；退款方式：直接退款；促销方式：使用平台券
 **/
@Slf4j
public class TestMTDiscountAndCouponZeroOrder extends TestDPLogin {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_mtshop_03";

    //商家端登录后获取
    public static String cashierOverviewPath = "/hui/ajax/cashieroverview";
    public static String orderoverviewPath = "/hui/ajax/orderoverview";
    public static String cashierqueryPath = "/hui/ajax/cashierquery";
    public static String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    Integer source = 1;//代表了使用买单优惠+优惠券，0元单

    public static String LOADUNIFIEDCASHIER = "ms_c_mtshoploadUnifiedCashier_05";


    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;

    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;

    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;


    /**
     * coupon 金额5元
     * 提单时的cipher 多个使用 %23隔开
     * o6nT68Q0fiFN1Ov04inIQY0TYURST%2FiDjegrqsoweUHOAHeM0Zmz%2BZBUWVOCSwdrydqiNKTPs%2BuxJ0USCEM28U%2BsKm4%2BTKmgJm4esMqnB5w%3D%23ssKyhaTwI%2FXPrviN9Ha7990NpfKddu0%2FHuWVTxZdtjmJUT1BWPTEDCW7bZTgdej8LiE1ZHgyECyTp0rrbP4yXhYKt4914aYBGeugj9iQAo0e%2BSlShx%2BmRRreaSvwXMv3%235gg7O6x3yCWMV%2BinDIZVFjPoKPlhZTn7NZkR%2F6eftsVP1ZH%2BYDsDKN%2Fcbi787AgP8dwhSSGvwOF0aOqxlthMMQ%3D%3D
     * UnifiedCouponIssueRequest：{"userId":123344,"userType":"MT",operationToken:"26332572ACA5F1D2591E34B4B4AF4271","operator":"dengjia06","couponGroupIdList":[],"unifiedCouponGroupIdList":["549009064"]}
     */
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1")
    @MethodAnotation(author = "buyuqi", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_mt_couponZeroOrder(String doubleWriteMode)  throws Exception {
        if( doubleWriteMode.equals("NEW"))
            LionUtil.setUserWriteList(mtUserId+"_1");
        if( doubleWriteMode.equals("OLD"))
            LionUtil.setUserBlackList(mtUserId+"_1");
        //0.平台券查询
        String couponid = "23738010020695727";
        DeskCoupon deskCoupon = checkLoop.getCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk",couponid);
        if(deskCoupon == null){
            // userid 5002907380  发券
            UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
            couponIssueRequest.setUserType("MT");
            couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
            couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(549009064));
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


        //1.加载优惠台
        LoadCashier loadCashier = LoadCashier.builder()
                .caseId(LOADUNIFIEDCASHIER)
                .token(mtToken)
                .userAgent(mtClient).build();
        CouponProduct couponProduct = loadCashier.parseCouponOfferId().orElse(null);
        //创建订单
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(mtToken,mtClient,CASEID,couponProduct,deskCoupon,source);

        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        Long orderId = createResult.getOrderId();
        String tradeNo = createResult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(String.valueOf(orderId));
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();


        //下单后平台校验
        Thread.sleep(2000);
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_hui_mt_CouponZeroOrder");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);


        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //平台支付成功校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_hui_mt_CouponZeroOrder");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单侧支付后校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,maitonQueryOrderResponse);

        //4、支付结果页
        Task.sleep(3);
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .serializedId(neworderid)
                .token(mtToken)
                .userAgent(mtClient).build();
        String status = orderState.queryMopayStatus();
        Assert.assertNotNull(status);

        //订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(mtToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(String.valueOf(orderId)).build();
        orderdetail.MtOrderDetail();

        //直接退款
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        // 直接发起退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse directRefundResponseresponse = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponseresponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponseresponse));
        Assert.assertEquals(jsonObject.getString("success"),"true");

        //退款后平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_hui_mt_CouponZeroOrder");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //买单侧退款后校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

    }
}
