package com.meituan.qa.meishi.Hui.util;

import com.sankuai.nibqa.trade.payMock.api.PayNotifyMockService;
import com.sankuai.nibqa.trade.payMock.api.RefundNotifyMockService;
import com.sankuai.nibqa.trade.payMock.impl.PayNotifyMockServiceImpl;
import com.sankuai.nibqa.trade.payMock.impl.RefundNotifyMockServiceImpl;
import com.sankuai.nibqa.trade.payMock.params.request.PayNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.response.PayMockBaseResponse;
import org.testng.Assert;

public class PayMockUtil {

    /**
    * mock支付
    */
    public static void mockPay(PayNotifyMockRequest request){
        PayNotifyMockService payNotifyMockService = new PayNotifyMockServiceImpl();
        request.setBizCode("nib.food.paybill");
        PayMockBaseResponse<String> response = payNotifyMockService.mockPayNotify(request);
        Assert.assertEquals(response.getCode(), 0, "支付mock失败，错误信息:" + response.getErrorMsg());
    }

    /**
     * mock退款
     */
    public static void mockRefund(RefundNotifyMockRequest request){
        RefundNotifyMockService refundNotifyMockService = new RefundNotifyMockServiceImpl();
        request.setBizCode("nib.food.paybill");
        PayMockBaseResponse<String> response = refundNotifyMockService.mockRefundNotify(request);
        Assert.assertEquals(response.getCode(), 0, "退款mock失败，错误信息:" + response.getErrorMsg());
    }
}