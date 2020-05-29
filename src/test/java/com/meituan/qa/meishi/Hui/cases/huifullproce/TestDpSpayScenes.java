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
 * 备注:        平台：点评侧 ；买单方案：智能支付；退款方式：无需关注，退款逻辑不在买单侧
 **/

@Slf4j
public class TestDpSpayScenes  extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> dpCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath = "/platformPath";
    OrderCheck orderCheck=new OrderCheck();

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    public static String LOADUNIFIEDCASHIER = "ms_c_4Verify_loadUnifiedCashier_02";
    public static  String CASEID="ms_c_4Verify_loadUnifiedCashier_02";

    @Test(groups = "P3")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "普通下单(原价)")

    public void ms_c_DpSpayScenes() throws  Exception {

        dpCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_uniCashierCreateOrder_SpayScenes_01");
        payToken = dpCreateResult.get(0);
        tradeNo = dpCreateResult.get(1);
        orderId = dpCreateResult.get(2);
        serializedId = dpCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //4.买单下单校验
        QueryOrderResponse CreateOrderResponse=checkLoop.getMaitonOrder(1,orderId);
        orderCheck.maitonOrder(1,CreateOrderResponse);

//        //3、支付
//        CashierPay cashierPay = CashierPay.builder().payToken(payToken).token(dpToken).tradeNo(tradeNo).build();
//        boolean payret = cashierPay.orderPay();
//        Assert.assertTrue(payret, tradeNo + "订单支付失败");

//        //4.买单支付校验
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
//                .token(dpToken)
//                .userAgent(dpClient).build();
//        String status = orderState.queryMopayStatus();
//        Assert.assertNotNull(status);
//
//        //订单详情页
//        OrderDetail orderdetail=OrderDetail.builder().token(dpToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId).build();
//        orderdetail.DpOrderDetail();
    }

}
