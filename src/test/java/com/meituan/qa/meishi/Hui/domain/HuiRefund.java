package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.mopayprocess.refundflow.request.*;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.response.RejectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.google.common.base.Strings;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class HuiRefund {

    private RefundFlowService refundFlowService;

    public Long orderId;

    public Long userId;

    public String operator;

    public String ip;

    public DirectRefundResponse superRefund() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        // 直接发起退款
        DirectRefundRequest request = new DirectRefundRequest();
        request.setSuperRefund(0);
        request.setReason("直接退款");
        request.setIp(optionalIP(ip));
        request.setDesc("直接退款");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        DirectRefundResponse response = refundFlowService.refund(request);
        return response;
    }

    public void direct() {
        DirectRefundRequest request = new DirectRefundRequest();
        request.setReason("直接退款");
        request.setIp(optionalIP(ip));
        request.setDesc("直接退款");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        DirectRefundResponse response = refundFlowService.refund(request);
    }

    public void reject() {
        RejectRefundRequest request = new RejectRefundRequest();
        request.setReason("拒绝退款");
        request.setIp(optionalIP(ip));
        request.setDesc("拒绝退款desc");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.MERCHANT.getCode());
        request.setPlatform(RefundFlowPlatformEnum.ECOM.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        refundFlowService.rejectRefund(request);
    }

    public void rejectCs() {
        RejectRefundRequest request = new RejectRefundRequest();
        request.setReason("拒绝退款");
        request.setIp(optionalIP(ip));
        request.setDesc("拒绝退款desc");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        refundFlowService.rejectRefund(request);
    }

    public AgreeRefundResponse agree() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp(optionalIP(ip));
        request.setDesc("同意退款desc");
        request.setOperator(operator);
        request.setOrderId(orderId);
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
    public RejectRefundResponse rejectRefund() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        RejectRefundRequest request = new RejectRefundRequest();
        request.setReason("拒绝退款");
        request.setIp(optionalIP(ip));
        request.setDesc("拒绝款desc");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.MERCHANT.getCode());
        request.setPlatform(RefundFlowPlatformEnum.ECOM.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        RejectRefundResponse rejectRefundResponse = refundFlowService.rejectRefund(request);
        if (rejectRefundResponse.getErrCode() == 0) {
            rejectRefundResponse.setIsSuccess(true);
        }
        return rejectRefundResponse;
    }


    public AgreeRefundResponse agreeCs() {
        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp(optionalIP(ip));
        request.setDesc("同意退款desc");
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        AgreeRefundResponse agreeRefundResponse = refundFlowService.agreeRefund(request);
        if (agreeRefundResponse.getErrCode() == 0) {
            agreeRefundResponse.setIsSuccess(true);
        }
        return agreeRefundResponse;
    }

    public void cancel() {
        CancelRefundRequest request = new CancelRefundRequest();
        request.setIp(optionalIP(ip));
        request.setOperator(operator);
        request.setOrderId(orderId);
        request.setUserId(userId);
        request.setTarget(RefundFlowTargetEnum.USER.getCode());
        System.out.println(JSON.toJSONString(request));
        refundFlowService.cancelRefund(request);
    }

    public AppealRefundRequest appeal() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        AppealRefundRequest request = new AppealRefundRequest();
        request.setApp(1);
        request.setCreditPlatform(1);
        request.setLoginType(1);
        request.setMobile("17610420086");
        request.setUa("ua-test");
        request.setOrderId(orderId);
        request.setDesc("test nib apply");
        request.setOrderTime(System.currentTimeMillis());
        request.setFingerprint("finger");
        request.setOperator(operator);
        request.setOrderSource(0);
        request.setPlatform(1);
        request.setIp(optionalIP(ip));
        request.setReason("付款错误");
        request.setDesc("付款错误");
        request.setTarget(RefundFlowTargetEnum.USER.getCode());
        request.setUuid("uuid");
        // 原路
        request.setRouter(1);
        request.setUserId(userId);
        System.out.println(JSON.toJSONString(request));
        refundFlowService.appealRefund(request);
        return request;
    }

    public ApplyRefundRequest apply() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        ApplyRefundRequest request = new ApplyRefundRequest();
        request.setApp(1);
        request.setCreditPlatform(1);
        request.setLoginType(1);
        request.setMobile("17610420086");
        request.setUa("ua-test");
        request.setOrderId(orderId);
        request.setDesc("test nib apply");
        request.setOrderTime(System.currentTimeMillis());
        request.setFingerprint("finger");
        request.setUuid("uuid");
        request.setOperator(operator);
        request.setOrderSource(0);
        request.setPlatform(1);
        request.setIp(optionalIP(ip));
        request.setReason("付款错误");
        request.setDesc("付款错误");
        request.setTarget(RefundFlowTargetEnum.USER.getCode());
        request.setType(RefundFlowTypeEnum.APPLY.getCode());
        // 原路
        request.setRouter(1);
        request.setUserId(userId);
        log.info("申请退款参数：{}",JSON.toJSONString(request));
        refundFlowService.applyRefund(request);
        return request;
    }


    private String optionalIP(String ip) {
        if (Strings.isNullOrEmpty(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }
}
