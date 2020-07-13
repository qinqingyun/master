package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueOption;
import com.dianping.unified.coupon.issue.api.request.UnifiedCouponIssueRequest;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.google.common.collect.Lists;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * Created by buyuqi on 2019/12/17.
 */
//申请退款接口
@Slf4j
public class Btest {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;
    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    @Test
    public void Btest(){

//        AppealRefundRequest request = new AppealRefundRequest();
//        request.setApp(1);
//        request.setCreditPlatform(1);
//        request.setLoginType(1);
//        request.setMobile("17610420086");
//        request.setUa("ua-test");
//        request.setOrderId(4800841605845480070l);
//        request.setDesc("test nib apply");
//        request.setOrderTime(System.currentTimeMillis());
//        request.setFingerprint("finger");
//        request.setOperator("9007199254760212");
//        request.setOrderSource(0);
//        request.setPlatform(1);
//        request.setIp("127.0.0.1");
//        request.setReason("付款错误");
//        request.setDesc("付款错误");
//        request.setTarget(RefundFlowTargetEnum.USER.getCode());
//        request.setUuid("uuid");
//        // 原路
//        request.setRouter(1);
//        request.setUserId(9007199254760212l);
//        System.out.println(JSON.toJSONString(request));
//        refundFlowService.appealRefund(request);

//        AppealRefundRequest request = new AppealRefundRequest();
//        request.setApp(1);
//        request.setCreditPlatform(1);
//        request.setLoginType(1);
//        request.setMobile("17610420086");
//        request.setUa("ua-test");
//        request.setOrderId(4800841606201380451l);
//        request.setDesc("test nib apply");
//        request.setOrderTime(System.currentTimeMillis());
//        request.setFingerprint("finger");
//        request.setOperator("5002907380");
//        request.setOrderSource(0);
//        request.setPlatform(1);
//        request.setIp("127.0.0.1");
//        request.setReason("付款错误");
//        request.setDesc("付款错误");
//        request.setTarget(RefundFlowTargetEnum.USER.getCode());
//        request.setUuid("uuid");
//        // 原路
//        request.setRouter(1);
//        request.setUserId(5002907380l);
//        System.out.println(JSON.toJSONString(request));
//        refundFlowService.appealRefund(request);


        Long a = Long.valueOf("559869592");
        long b = a%13;
        System.out.println(b);

    }
    @Test
    public void setShopPromo() throws TException {
        MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
        maitonHongbaoTRequest.setPlatform(Platform.MT);
        maitonHongbaoTRequest.setUserId(29060740);
        maitonHongbaoTRequest.setPoiId(97224769);
        maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
        maitonHongbaoTRequest.setOrderId(123132131);
        maitonHongbaoTRequest.setOrderPrice(1);
        MaitonHongbaoTResponse response = maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
        Optional<MaitonHongbaoTBean> detailOptional = response.data.stream().findFirst();
        String shopPromoid = detailOptional.get().id;
        log.info("发券接口返回=======" + shopPromoid);
    }
    @Test
    public void setCouponPromo(){
        UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
        couponIssueRequest.setUserType("MT");
        couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
        couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(763075395));
        couponIssueRequest.setOperator("qa-system");
        couponIssueRequest.setUserId(29060740);

        UnifiedCouponIssueOption option = new UnifiedCouponIssueOption();
        option.setCreditType(0);
        option.setNotifyType(0);
        UnifiedCouponIssueResponse unifiedCouponIssueResponse = unifiedCouponIssueTrustService.issueTrustCoupon(couponIssueRequest, option);
    }
}

