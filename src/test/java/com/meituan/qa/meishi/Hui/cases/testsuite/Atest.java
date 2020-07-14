package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.request.AgreeRefundRequest;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.request.DirectRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.meituan.qa.meishi.Hui.util.TestDPLogin.checkLoop;

/**
 * Created by buyuqi on 2019/12/17.
 */
//同意退款接口
@Slf4j
public class Atest extends TestDPLogin {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    OrderCheck orderCheck=new OrderCheck();

    @Test
    public void Atest(){
//        CommanDiff.setMap("atest","1");
//        log.info("{}",CommanDiff.getMap());

        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp("127.0.0.1");
        request.setDesc("同意退款desc");
        request.setOperator("5002907380");
        request.setOrderId(4809848809930036566l);
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
        request.setOperator("29060740");
        request.setOrderId(4818856010183808342l);
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        DirectRefundResponse response = refundFlowService.refund(request);
    }

    @Test
    public void superRefund11()  {
        // 直接发起退款
        String orderId = "4809848809887129282";
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(dpUserIdByq).userId(Long.valueOf(dpUserIdByq)).build();
        log.info("执行退款订单id{}",orderId);
        ApplyRefundRequest applyRefundRequest = huiRefund.apply();
        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        agreeRefundResponse.isSuccess();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
    }

}
