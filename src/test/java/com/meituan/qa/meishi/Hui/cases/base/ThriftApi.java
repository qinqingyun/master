package com.meituan.qa.meishi.Hui.cases.base;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.shard.service.QueryMainOrder4MTService;
import com.dianping.mopayprocess.refundflow.request.AgreeRefundRequest;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.request.DirectRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.ApplyRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueOption;
import com.dianping.unified.coupon.issue.api.request.UnifiedCouponIssueRequest;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.meituan.mtrace.Tracer;
import com.meituan.nibscp.unity.validation.api.request.JsonDiffRequest;
import com.meituan.nibscp.unity.validation.api.response.DiffResponse;
import com.meituan.nibscp.unity.validation.api.service.DiffService;
import com.meituan.nibtp.trade.client.buy.enums.BizSpaceId;
import com.meituan.nibtp.trade.client.buy.enums.OrderResultCodeEnum;
import com.meituan.nibtp.trade.client.buy.response.QueryOrderMappingRes;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.qa.meishi.Hui.util.CheckOrderType;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.stringtemplate.v4.ST;

import java.util.Optional;
import static com.meituan.nibscp.unity.common.api.enums.MigrationBizTypeEnum.FOOD_BUY;


/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class ThriftApi {
    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0",desc = "退款rpc服务")
    RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;
    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    @ThriftAPI(desc = "unity平台diff工具", appkey = "com.sankuai.nibscp.unity.validation", interfaceName = "com.meituan.nibscp.unity.validation.api.service.DiffService")
    DiffService diffService;
    @PigeonAPI(url = "http://service.dianping.com/huiOrderService/QueryMainOrder4MTService_1.0.0")
    QueryMainOrder4MTService queryMainOrder4MTService;
    /**
     * unity平台买单数据diff
     *
     */
    public DiffResponse getOrderDiff(String newData,String oldData) {
        JsonDiffRequest jsonDiffRequest = new JsonDiffRequest();
        jsonDiffRequest.setMigrationBizTypeEnum(FOOD_BUY);
        jsonDiffRequest.setIdentity("food_buy.normal.supply.default-8");
        jsonDiffRequest.setTraceId(Tracer.id());
        jsonDiffRequest.setNewJsonData(newData);
        jsonDiffRequest.setOldJsonData(oldData);
        DiffResponse response = diffService.diff(jsonDiffRequest);
        return response;
    }

    /**
     * 新老订单映射
     *
     */
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
    /**
     * 直接退款
     *
     */
    public DirectRefundResponse superRefund(String ip, OrderModel orderModel) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        // 直接发起退款
        DirectRefundRequest request = new DirectRefundRequest();
        request.setSuperRefund(0);
        request.setReason("直接退款");
        request.setIp("127.0.0.1");
        request.setDesc("直接退款");
        request.setOperator("qa-autocase");
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        DirectRefundResponse response = refundFlowService.refund(request);
        return response;
    }
//    private String optionalIP(String ip) {
//        if (Strings.isNullOrEmpty(ip)) {
//            return "127.0.0.1";
//        }
//        return ip;
//    }
    /**
     * 用户申请退款
     *
     */
    public ApplyRefundResponse applyRefund(String ip, OrderModel orderModel, UserModel userModel) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        ApplyRefundRequest request = new ApplyRefundRequest();
        request.setApp(1);
        request.setCreditPlatform(1);
        request.setLoginType(1);
        request.setMobile("17610420086");
        request.setUa("ua-test");
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setDesc("test nib apply");
        request.setOrderTime(System.currentTimeMillis());
        request.setFingerprint("finger");
        request.setUuid("uuid");
        request.setOperator("127.0.0.1");
        request.setOrderSource(0);
        request.setPlatform(1);
        request.setIp("127.0.0.1");
        request.setReason("付款错误");
        request.setDesc("付款错误");
        request.setTarget(RefundFlowTargetEnum.USER.getCode());
        request.setType(RefundFlowTypeEnum.APPLY.getCode());
        // 原路
        request.setRouter(1);
        request.setUserId(Long.valueOf(userModel.getUserId()));
        System.out.println(JSON.toJSONString(request));
        ApplyRefundResponse applyRefundResponse = refundFlowService.applyRefund(request);
        return applyRefundResponse;
    }
    /**
     * 商家同意退款
     *
     */
    public AgreeRefundResponse agreeRefund(String ip, OrderModel orderModel) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp("127.0.0.1");
        request.setDesc("同意退款desc");
        request.setOperator("127.0.0.1");
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setTarget(RefundFlowTargetEnum.MERCHANT.getCode());
        request.setPlatform(RefundFlowPlatformEnum.ECOM.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        AgreeRefundResponse agreeRefundResponse = refundFlowService.agreeRefund(request);
        if (agreeRefundResponse.getErrCode() == 0) {
            agreeRefundResponse.setIsSuccess(true);
        }
        return agreeRefundResponse;
    }
        /**
         * 商家券发券接口
         *
         */
    public MaitonHongbaoTResponse setShopPromo(Long userId,Integer poiId) throws TException {
        MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
        maitonHongbaoTRequest.setPlatform(Platform.MT);
        maitonHongbaoTRequest.setUserId(userId);
        maitonHongbaoTRequest.setPoiId(poiId);
        maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
        maitonHongbaoTRequest.setOrderId(123132131);
        maitonHongbaoTRequest.setOrderPrice(1);
        MaitonHongbaoTResponse response = maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
        return response;
    }
    /**
     * 平台券发券接口
     *
     */
    public UnifiedCouponIssueResponse setCouponPromo(Long userId,Integer couponId) {
        UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
        couponIssueRequest.setUserType("MT");
        couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
        couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(couponId));
        couponIssueRequest.setOperator("qa-system");
        couponIssueRequest.setUserId(userId);

        UnifiedCouponIssueOption option = new UnifiedCouponIssueOption();
        option.setCreditType(0);
        option.setNotifyType(0);
        UnifiedCouponIssueResponse unifiedCouponIssueResponse = unifiedCouponIssueTrustService.issueTrustCoupon(couponIssueRequest, option);
        return unifiedCouponIssueResponse;
    }
    /**
    *
     * 老系统订单查询
     */
    public QueryOrderResponse getMaidonOrder(String orderId) {
        QueryOrderResponse queryOrderResponse = queryMainOrder4MTService.queryMainOrderByOrderId(Long.valueOf(orderId));
        return queryOrderResponse;
    }
}
