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
import com.sankuai.nibqa.trade.payMock.params.request.PayNotifyMockRequest;
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
 * Date:        2020-01-02
 * 用例简介:     买单使用原价买单方案，使用商家券
 * 数据源:       poiId：97224769
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款；促销方式：使用商家券
 **/
@Slf4j
public class TestMTShopPromo_New extends TestDPLogin {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_mtshop";

    //商家端登录后获取
    public static String cashierOverviewPath = "/hui/ajax/cashieroverview";
    public static String orderoverviewPath = "/hui/ajax/orderoverview";
    public static String cashierqueryPath = "/hui/ajax/cashierquery";
    public static String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    Integer source = 0;//1代表了使用买单优惠+优惠券，0元单;0代表了非0元单

    public static String LOADUNIFIEDCASHIER = "ms_c_mtshoploadUnifiedCashier_02";


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
    //String  doubleWriteMode = "OLD";
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1",description = "点评app，使用商家优惠券买单：返券->发券->买单使用商家优惠券->下单->支付->极速退款")
    @MethodAnotation(author = "byq", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_mt_shopPromo(String doubleWriteMode)  throws Exception {
        PayNotifyMockRequest payNotifyMockRequest = new PayNotifyMockRequest();
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(mtUserId + "_1");
            payNotifyMockRequest.setScene(Scene.NEW_MAIN);
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
            Tracer.putContext("PAY_MOCK","TRUE");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(mtUserId+"_1");
            payNotifyMockRequest.setScene(Scene.OLD_MAIN);
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }

        String id = "120000901026380";
        DeskCoupon deskCoupon = checkLoop.getShopCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk_01",id);
        if(deskCoupon == null){
            //调用营销接口发商家券
            MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
            maitonHongbaoTRequest.setPlatform(Platform.MT);
            maitonHongbaoTRequest.setUserId(mtUserId);
            maitonHongbaoTRequest.setPoiId(97224769);
            maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
            maitonHongbaoTRequest.setOrderId(123132131);
            maitonHongbaoTRequest.setOrderPrice(1);
            MaitonHongbaoTResponse response= maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
            Optional<MaitonHongbaoTBean> detailOptional=response.data.stream().findFirst();
            id= detailOptional.get().id;
            log.info("发券接口返回======="+ id);
            //下单前查询优惠
            deskCoupon = checkLoop.getShopCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk_01",id);
            Assert.assertTrue(deskCoupon != null,"获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        }

        //1.加载优惠台
        LoadCashier mtloadCashier = LoadCashier.builder()
                .caseId(LOADUNIFIEDCASHIER)
                .token(mtToken)
                .userAgent(mtClient).build();
        CouponProduct couponProduct = mtloadCashier.parseCouponOfferId().orElse(null);
        //创建订单
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(mtToken,mtClient,CASEID,couponProduct,deskCoupon,source);

        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        String orderId = createResult.getOrderId().toString();
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
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);

        //3、支付
        //CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);
        Long amount = createOrderResponse.getOrderDTO().getUserAmount().longValue() * 100;
        payNotifyMockRequest.setTradeNo(tradeNo);
        payNotifyMockRequest.setOrderId(orderId);
        payNotifyMockRequest.setAmount(amount);
        if(doubleWriteMode.equals("OLD")){
            payNotifyMockRequest.setOutNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockPay(payNotifyMockRequest);

        //平台支付成功校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单侧支付后校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,maitonQueryOrderResponse);

        //4、支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,mtToken,mtClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,mtToken,"MT","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

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
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //退款mock
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        //退款后平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_mtshopScenes_platform");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //买单侧退款后校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
//        orderCheck.maitonOrder(3,refundOrderResponse);

    }
}
