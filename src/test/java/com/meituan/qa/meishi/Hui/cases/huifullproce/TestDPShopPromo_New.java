package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.meituan.mtrace.Tracer;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.LoadCashier;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.PayMockUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.nibqa.trade.payMock.params.enums.Scene;
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019-12-06
 * 用例简介:     买单使用商家返券促销
 * 数据源:       shopId：65731456 返券方案：
 * 主要流程:     调营销发券接口 -> 查询优惠台 -> 下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：点评侧；买单方案：原价买单；退款方式：直接退款
 **/

@Slf4j
public class TestDPShopPromo_New extends TestDPLogin {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_dpshop";
    public static String LOADUNIFIEDCASHIER = "ms_c_dpshoploadUnifiedCashier_02";


    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;

    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;

    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;

    OrderCheck orderCheck=new OrderCheck();
    String platformPath="/platformPath";
    Integer source = 0;//1代表了使用买单优惠+优惠券，0元单;0代表了非0元单

    /**
     * coupon 金额5元
     * 提单时的cipher 多个使用 %23隔开
     * o6nT68Q0fiFN1Ov04inIQY0TYURST%2FiDjegrqsoweUHOAHeM0Zmz%2BZBUWVOCSwdrydqiNKTPs%2BuxJ0USCEM28U%2BsKm4%2BTKmgJm4esMqnB5w%3D%23ssKyhaTwI%2FXPrviN9Ha7990NpfKddu0%2FHuWVTxZdtjmJUT1BWPTEDCW7bZTgdej8LiE1ZHgyECyTp0rrbP4yXhYKt4914aYBGeugj9iQAo0e%2BSlShx%2BmRRreaSvwXMv3%235gg7O6x3yCWMV%2BinDIZVFjPoKPlhZTn7NZkR%2F6eftsVP1ZH%2BYDsDKN%2Fcbi787AgP8dwhSSGvwOF0aOqxlthMMQ%3D%3D
     * UnifiedCouponIssueRequest：{"userId":123344,"userType":"MT",operationToken:"26332572ACA5F1D2591E34B4B4AF4271","operator":"dengjia06","couponGroupIdList":[],"unifiedCouponGroupIdList":["549009064"]}
     */
    @Parameters({ "DoubleWriteMode" })
    //String  doubleWriteMode = "OLD";
    @Test(groups = "P1")
    @MethodAnotation(author = "buyuqi", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_dp_shopPromo(String  doubleWriteMode) throws Exception {
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(dpUserId+"_0");
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }
        String id = "120000904211812";
        DeskCoupon deskCoupon = checkLoop.getShopCouponCipher(dpToken,dpClient,"ms_c_dpshopScenes_platform_01",id);
        if(deskCoupon == null){
            //调用营销接口发商家券
            MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
            maitonHongbaoTRequest.setPlatform(Platform.DP);
            maitonHongbaoTRequest.setUserId(Long.valueOf(dpUserId));
            maitonHongbaoTRequest.setPoiId(65731456);
            maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
            maitonHongbaoTRequest.setOrderId(123132131);
            maitonHongbaoTRequest.setOrderPrice(1);
            MaitonHongbaoTResponse response= maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
            Optional<MaitonHongbaoTBean> detailOptional=response.data.stream().findFirst();
            id= detailOptional.get().id;
            log.info("发券用户===="+dpUserId+"发券门店=====65731456"+"发券平台===="+Platform.DP+"商家券=======" + id);
            //下单前查询优惠
            deskCoupon = checkLoop.getShopCouponCipher(dpToken,dpClient,"ms_c_dpshopScenes_platform_01",id);
            Assert.assertTrue(deskCoupon != null,"获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        }
//        HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(dpToken).useCardflag(UseCard.USE_MERCHANT_CARD).client(dpClient).caseid("ms_c_dpshopScenes_platform_01").build();
//        DeskCoupon deskCoupon = promoDesk.shopCouponCipher(id).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));

        //1.加载优惠台
        LoadCashier dploadCashier = LoadCashier.builder()
                .caseId(LOADUNIFIEDCASHIER)
                .token(dpToken)
                .userAgent(dpClient).build();
        CouponProduct couponProduct = dploadCashier.parseCouponOfferId().orElse(null);
        //创建订单
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(dpToken,dpClient,CASEID,couponProduct,deskCoupon,source);
//        //1.加载优惠台
//        LoadCashier dploadCashier = LoadCashier.builder()
//                .caseId(LOADUNIFIEDCASHIER)
//                .token(dpToken)
//                .userAgent(dpClient).build();
//
//        //下单
//        HuiCreateOrder createOrder = HuiCreateOrder.builder()
//                .token(dpToken)
//                .userAgent(dpClient)
//                .caseid(CASEID)
//                .couponProduct(dploadCashier.parseCouponOfferId().orElse(null))
//                .deskcoupon(deskCoupon)
//                .build();
//        HuiCreateOrderResult createResult = createOrder.requestCreate();

        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        String orderId = createResult.getOrderId().toString();
        String tradeNo = createResult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        // 新老订单映射
        MappingOrderIds mappingOrderIds = checkLoop.getMappingOrderIds(String.valueOf(orderId));
        String neworderid = mappingOrderIds.getNewOrderId();
        String oldorderid = mappingOrderIds.getOldOrderId();

        //下单后平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

       //下单后买单侧校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,maitonQueryOrderResponse);

        //支付
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);

        //平台支付成功校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单侧支付后校验
        QueryOrderResponse QueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,QueryOrderResponse);

        //4、支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,dpToken,dpClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,dpToken,"DP","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        //直接退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse directRefundResponseresponse = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponseresponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponseresponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //退款mock
        Long amount = maitonQueryOrderResponse.getOrderDTO().getUserAmount().longValue() * 100;
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        // 交易平台接收退款回调后更改订单状态可能会存在滞后或者读写延迟问题，故增加延迟等待时间
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("平台校验已消费退款前延迟5s时异常："+e.toString());
        }

        //平台校验已消费退款
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,"5000050967");
    }
}
