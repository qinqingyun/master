package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2020-01-02
 * 用例简介:     买单使用智能支付
 * 数据源:       shopId：427462222
 * 主要流程:     下单 -> 支付 -> 详情
 * 备注:        平台：美团侧 ；买单方案：智能支付；退款方式：无需关注，退款逻辑不在买单侧
 **/
@Slf4j
public class TestSpayOriginalScenes  extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath = "/platformPath";
    OrderCheck orderCheck=new OrderCheck();

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;


    @Test(groups = "P3")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "美团智能支付原价流程")

    public void ms_c_SpayOriginalScenes() throws Exception {

        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_uniCashierCreateOrder_SpayScenes_01");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //4.买单下单校验
        QueryOrderResponse CreateOrderResponse=checkLoop.getMaitonOrder(1,orderId);
        orderCheck.maitonOrder(1,CreateOrderResponse);

//        //3、支付
//        CashierPay cashierPay = CashierPay.builder().payToken(payToken).token(mtToken).tradeNo(tradeNo).build();
//        boolean payret = cashierPay.orderPay();
//        Assert.assertTrue(payret, tradeNo + "订单支付失败");
//
//       //4.买单支付校验
//        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,orderId);
//        orderCheck.maitonOrder(2,maitonQueryOrderResponse);
//
//
//        //5.支付结果页
//        Task.sleep(3);
//
//        QueryOrderState orderState = QueryOrderState.builder()
//                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
//                .serializedId(serializedId)
//                .token(mtToken)
//                .userAgent(mtClient).build();
//        String status = orderState.queryMopayStatus();
//        Assert.assertNotNull(status);
//
//        //订单详情页
//        OrderDetail orderdetail=OrderDetail.builder().token(mtToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId).build();
//        orderdetail.MtOrderDetail();
    }
}