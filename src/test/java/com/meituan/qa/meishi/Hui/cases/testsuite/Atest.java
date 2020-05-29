package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.mopayprocess.refundflow.request.AgreeRefundRequest;
import com.dianping.mopayprocess.refundflow.request.DirectRefundRequest;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * Created by buyuqi on 2019/12/17.
 */
//同意退款接口
@Slf4j
public class Atest {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @Test
    public void Atest(){
//        CommanDiff.setMap("atest","1");
//        log.info("{}",CommanDiff.getMap());

        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp("127.0.0.1");
        request.setDesc("同意退款desc");
        request.setOperator("5002907380");
        request.setOrderId(4800841607001238870l);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        refundFlowService.agreeRefund(request);
    }
    @Test
    //直接退款
    //dpuserid:"9007199254760212"
    //mtuserId:5002907380
    public void superRefund(){
        // 直接发起退款
        DirectRefundRequest request = new DirectRefundRequest();
        request.setSuperRefund(0);
        request.setReason("直接退款");
        request.setIp("10.21.21.2");
        request.setDesc("直接退款");
        request.setOperator("859811843");
        request.setOrderId(200325928692073l);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        DirectRefundResponse response = refundFlowService.refund(request);
    }
}
