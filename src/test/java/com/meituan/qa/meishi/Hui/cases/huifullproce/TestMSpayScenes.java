package com.meituan.qa.meishi.Hui.cases.huifullproce;/*
package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderMResult;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestMSpayScenes extends TestDPLogin {

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    String platformPath = "/platformPath";

    @Test(groups = "P3")
    @MethodAnotation(author = "qinqingyun", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_mspay_scenes() {
        */
/* 1.下单*//*

        String CASEID = "ms_c_ajaxcreateorder_02";
        HuiCreateOrder createOrder = HuiCreateOrder.builder()
                .token(dpToken)
                .userAgent(dpClient)
                .caseid(CASEID)
                .couponProduct(null)
                .deskcoupon(null)
                .build();
        HuiCreateOrderMResult mcreateresult = createOrder.requestMCreate();
        String payToken = mcreateresult.getPayToken();
        String serializedId = mcreateresult.getSerializedId();
        Long orderId = mcreateresult.getOrderId();
        String tradeNo = mcreateresult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        log.info("创单成功！order:{}", JSON.toJSONString(mcreateresult));
        // 1.1平台创单校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject verifyRequest = createOrderRequest.getJSONObject("params");
        PlatformCheckInfo platformCheck = PlatformCheckInfo.builder()
                .flag(1)
                .orderid(Long.valueOf(orderId))
                .verifyRequest(verifyRequest)
                .build();
        platformCheck.PlatformCheckInfo();

        //2、支付
        CashierPay cashierPay = CashierPay.builder().payToken(payToken).token(dpToken).tradeNo(tradeNo).build();
        boolean payret = cashierPay.orderPay();

        //2.2 买单BP侧支付校验



        //3、支付结果页
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .serializedId(serializedId)
                .token(dpToken)
                .userAgent(dpClient).build();
        String status = orderState.queryMStatus();
        Assert.assertNotNull(status);

        //4、订单详情页
        QueryOrderState orderDetail = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .serializedId(serializedId)
                .token(dpToken)
                .userAgent(dpClient).build();
        String er = orderState.orderDetail();
        Assert.assertNotNull(status);

        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        //5. 直接发起退款
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));


    }
}
*/
