package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.service.MaitonOrderQueryService;
import com.dianping.hui.order.shard.service.QueryMainOrder4MTService;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.nibtp.trade.client.buy.enums.BizSpaceId;
import com.meituan.nibtp.trade.client.buy.enums.OrderResultCodeEnum;
import com.meituan.nibtp.trade.client.buy.response.QueryOrderMappingRes;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.UseCard;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CheckOrderType;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.nibqa.trade.api.dto.ValidResponse;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
public class TestCheckLoop {

    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "com.dianping.hui.order.service.MaitonOrderQueryService")
     private MaitonOrderQueryService maitonOrderQueryService;

    @PigeonAPI(url = "http://service.dianping.com/huiOrderService/QueryMainOrder4MTService_1.0.0")
    QueryMainOrder4MTService queryMainOrder4MTService;

    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;

    @LoopCheck(desc = "加载优惠台", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    //app优惠台加载
    public Integer getCouponOfferId(String  mtToken, String mtClient,String caseId)  {
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, caseId);
        if(couponOfferId == 0){
            return null;
        }
        return couponOfferId;
    }

    @LoopCheck(desc = "折扣买单创建订单", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 买单下单,需要加载优惠台
    public List uniCashierCreateOrder(String  token, String userAgent,String caseId,int couponOfferId)  {
        List orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(token, userAgent, caseId, couponOfferId);
        return orderCreateResult;
    }
    @LoopCheck(desc = "原价买单创建订单", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 买单下单，无需加载优惠台
    public List uniCashierCreateOrder(String  token, String userAgent,String caseId)  {
        List orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(token, userAgent, caseId);
        return orderCreateResult;
    }
    @LoopCheck(desc = "商家券创建订单", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 买单下单，使用商家券，加载优惠台
    public HuiCreateOrderResult uniCashierCreateOrder(String  token, String userAgent, String caseId, CouponProduct couponProduct, DeskCoupon deskCoupon)  {
        HuiCreateOrder createOrder = HuiCreateOrder.builder()
                .token(token)
                .userAgent(userAgent)
                .caseid(caseId)
                .couponProduct(couponProduct)
                .deskcoupon(deskCoupon)
                .build();
        HuiCreateOrderResult createResult = createOrder.requestCreate();
        return createResult;
    }
    @LoopCheck(desc = "买单下单，使用预定金", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 买单下单，使用预定金
    public List uniCashierCreateOrderResv(String token, String userAgent, String caseId, String bizOrderId)  {
        List orderCreateResult = CreateOrderUtil.uniCashierCreateOrderResv(token, userAgent, caseId,bizOrderId);
        return orderCreateResult;
    }

    @LoopCheck(desc = "下单后平台校验轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    // 在双写时根据下单的订单号获取新老系统订单号，新系统订单号目前是47开头，19位；老系统是19开头
    public ValidResponse getPlatformStatus(int  flag, String  orderId, JSONObject verifyRequest, String buyer)  {
        PlatformCheckInfo platformCheck = PlatformCheckInfo.builder()
                .flag(flag)
                .orderid(Long.valueOf(orderId))
                .verifyRequest(verifyRequest)
                .buyer(buyer)
                .build();
        ValidResponse validResponse = platformCheck.PlatformCheckInfo();
        if(!validResponse.isSuccess()){
            return null;
        }
        return validResponse;
    }

    @LoopCheck(desc = "查询新老订单ID映射轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    // 在双写时根据下单的订单号获取新老系统订单号，新系统订单号目前是47开头，19位；老系统订单号19开头，19位
    public MappingOrderIds getMappingOrderIds(String orderId) throws Exception {
        MappingOrderIds mappingOrderIds = new MappingOrderIds();
        QueryOrderMappingRes orderMappingRes;
        String oldOrderId;
        String newOrderId;
        // 判断订单号大小，来调不同的接口查询订单映射信息
        // 如果不是新系统订单号，调loadByBizOrderId
        if (!CheckOrderType.isNewOrder(orderId)) {
            orderMappingRes = orderMappingService.loadByBizOrderId(orderId, BizSpaceId.FOOD.getCode());
            if (!orderMappingRes.getHeader().isSuccess()) {
                return null;
            }
            oldOrderId = orderMappingRes.getOrderMappingDTO().getBizOrderId();
            newOrderId = orderMappingRes.getOrderMappingDTO().getOrderId().toString();
        } else {
            // 如果是新系统订单号，调loadByOrderId
            //TODO:拿到数据给orderid赋值，其余反馈null
            orderMappingRes = orderMappingService.loadByOrderId(Long.parseLong(orderId));
            if (orderMappingRes.getHeader().getResultCode() == OrderResultCodeEnum.ORDER_MAPPING_NOT_FOUND.getCode()) {
                // 如果查不到映射，新老系统均使用orderId。须先判断ORDER_MAPPING_NOT_FOUND。
                 //return null;
                oldOrderId = orderId;
                newOrderId = orderId;
            } else if (!orderMappingRes.getHeader().isSuccess()) {
                return null;
            } else {
                oldOrderId = orderMappingRes.getOrderMappingDTO().getBizOrderId();
                newOrderId = orderMappingRes.getOrderMappingDTO().getOrderId().toString();
            }
        }

        mappingOrderIds.setOldOrderId(oldOrderId);
        mappingOrderIds.setNewOrderId(newOrderId);

        log.info("查询订单映射: oldOrderId: " + mappingOrderIds.getOldOrderId() + "，newOrderId: " + mappingOrderIds.getNewOrderId());
        return mappingOrderIds;
    }

    @LoopCheck(desc = "查询买单侧下单轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    public QueryOrderResponse getMaitonOrder( int flag,String orderId) throws Exception {
        QueryOrderResponse maitonQueryOrderResponse = queryMainOrder4MTService.queryMainOrderByOrderId(Long.valueOf(orderId));
        if (flag==1){
            if (maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue()!=0){
                return null;
            }
        }
        if (flag==2){
            if (maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue()!=10){
                return null;
            }
        }
        if (flag==3){
            List<Integer> refundset = Lists.newArrayList(30,50);
            if (!refundset.contains(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue())){
                return null;
            }
        }
        return maitonQueryOrderResponse;
    }

    @LoopCheck(desc = "支付结果页状态轮询", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 支付结果页
    public String getOrderState(String serializedId, String token, String userAgent,String caseId)  {
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId(caseId)
                .serializedId(serializedId)
                .token(token)
                .userAgent(userAgent).build();
        String statusMsg = orderState.queryMopayStatus();
        if (!statusMsg.equals("支付成功") ){
            return null;
        }
        return statusMsg;
    }
    @LoopCheck(desc = "订单详情页", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 支付后订单详情页
    public String getOrderDetail(String orderId, String token,String platform,String caseId)  {
        OrderDetail orderdetail= OrderDetail.builder().token(token).caseId(caseId).orderId(orderId).build();
        String orderDetailinfoContent = null;
        if(platform == "MT"){
            orderDetailinfoContent = orderdetail.MtOrderDetail();
        }else {
            orderDetailinfoContent = orderdetail.DpOrderDetail();
        }
        if(!orderDetailinfoContent.equals("支付成功")){
            return null;
        }
        return orderDetailinfoContent;
    }
    @LoopCheck(desc = "订单退款结果", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 订单退款结果,退款方式：直接退款
    public String getOrderRefundDetail(String orderId, String mtToken)  {
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        if(response.getErrCode() != 0){
            return null;
        }
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        String orderRefundStatus = jsonObject.getString("success");
        if(!orderRefundStatus.equals("true")){
            return null;
        }
        return orderRefundStatus;
    }
    @LoopCheck(desc = "订单退款结果", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    // 订单退款结果,退款方式：用户请求退款，商家同意
    public String getOrderRefundDetailAccess(String orderId, String userId) throws InterruptedException {
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(userId).userId(Long.valueOf(userId)).build();
        ApplyRefundRequest applyRefundResponse = huiRefund.apply();
        TimeUnit.SECONDS.sleep(2);
        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        TimeUnit.SECONDS.sleep(2);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        String orderRefundStatus = jsonObject.getString("success");
        if(!orderRefundStatus.equals("true")){
            return null;
        }
        return orderRefundStatus;
    }
    @LoopCheck(desc = "查询商家券的加密串", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 查询商家券的加密串
    public DeskCoupon getShopCouponCipher(String token, String userAgent, String caseId, String hongbaoid) {
        HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(token).useCardflag(UseCard.USE_MERCHANT_CARD).client(userAgent).caseid(caseId).build();
        DeskCoupon deskCoupon = null;
        try {
            deskCoupon = promoDesk.shopCouponCipher(hongbaoid).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));
        }catch (RuntimeException e){
            e.getMessage();
            return null;
        }
        return deskCoupon;
    }
    @LoopCheck(desc = "查询平台券的加密串", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    // 查询平台券的加密串
    public DeskCoupon getCouponCipher(String token, String userAgent, String caseId, String hongbaoid) {
        HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(token).useCardflag(UseCard.USE_PLATFORM_CARD).client(userAgent).caseid(caseId).build();

        DeskCoupon deskCoupon = null;
        try {
            deskCoupon = promoDesk.shopCouponCipher(hongbaoid).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));
        }catch (RuntimeException e){
            e.getMessage();
            return null;
        }
        return deskCoupon;
    }
}
