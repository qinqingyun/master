package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * Created by buyuqi on 2019/12/17.
 */
//申请退款接口
@Slf4j
public class Btest {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
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
}

