package com.meituan.qa.meishi.Hui.cases.base;

import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.mopayprocess.refundflow.request.DirectRefundRequest;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.google.common.base.Strings;
import com.meituan.nibtp.trade.client.buy.enums.BizSpaceId;
import com.meituan.nibtp.trade.client.buy.enums.OrderResultCodeEnum;
import com.meituan.nibtp.trade.client.buy.response.QueryOrderMappingRes;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CheckOrderType;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class ThriftApi {
    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0",desc = "退款rpc服务")
    RefundFlowService refundFlowService;

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
    public DirectRefundResponse superRefund(String ip, String orderId) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        // 直接发起退款
        DirectRefundRequest request = new DirectRefundRequest();
        request.setSuperRefund(0);
        request.setReason("直接退款");
        request.setIp(optionalIP(ip));
        request.setDesc("直接退款");
        request.setOperator("qa-autocase");
        request.setOrderId(Long.valueOf(orderId));
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        DirectRefundResponse response = refundFlowService.refund(request);
        return response;
    }
    private String optionalIP(String ip) {
        if (Strings.isNullOrEmpty(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }
}
