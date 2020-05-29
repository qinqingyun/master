package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.dianping.hui.business.ecom.service.RefundOrderBaseService;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.CashierPay;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestWxDiscountScenes extends TestDPLogin {

    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";

    @PigeonAPI(url = "http://service.dianping.com/hui/huiBusinessService/refundOrderBaseService_1.0.0")
    private RefundOrderBaseService refundOrderBaseService;

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @Test(groups = "P3")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "微信小程序下单")

    public void ms_c_WxDiscountScenes() {
        String orderId="1233";

       /* 1.加载优惠台*/
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        /* 2.小程序下单*/
//        Map<String, String> dpwxaOrderCreateResult_01 = CreateOrderUtil.wxaCreateOrder(dpToken, "ms_c_wxacCreateOrder_018", 0, "+xqrtEinhODeoGXFchLsqXC3QFH9y5cHj+Mp25v5S298EJ6l4A84wwDVJZmu01DZ");
        /* 3.支付*/
        CashierPay cashierPay = CashierPay.builder().payToken(payToken).token(dpToken).tradeNo(tradeNo).build();
//        boolean payret = cashierPay.orderPay();
//        Assert.assertTrue(payret, tradeNo + "订单支付失败");
        /* 4.直接退款*/
       // HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(orderId).operator("qa-autocase").build();
        // 直接发起退款
       // DirectRefundResponse response = huiRefund.superRefund();
       // log.info("获取退款结果:{}", JSON.toJSONString(response));

    }

}
