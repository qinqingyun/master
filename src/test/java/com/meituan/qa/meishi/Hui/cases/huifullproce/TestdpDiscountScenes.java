package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.RejectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.domain.OrderDetail;
import com.meituan.qa.meishi.Hui.domain.QueryOrderState;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019-12-24
 * 用例简介:     买单使用7折优惠买单方案
 * 数据源:       shopId：24799161
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：点评侧 ；买单方案：7折优惠买单；退款方式：直接退款
 **/

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境app全单折流程")
@Slf4j
public class TestdpDiscountScenes extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();
    List<String> dPorderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    String  doubleWriteMode="NEW";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @Test(groups = "P1")
    @MethodAnotation(author = "qqy", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "点评普通下单(全单折)")
    public void ms_c_dPdiscountScenes_01() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
        }

        //1、加载优惠台
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(dpToken, dpClient, "ms_c__dp_loadUnifiedCashier");
        log.info("折扣couponOfferId:" + couponOfferId);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_dp_uniCashierCreateOrde", couponOfferId);
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //下单后平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //下单后买单侧校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);

        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

       //支付后买单侧校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,maitonQueryOrderResponse);

        //4、支付结果页
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
        String status = orderState.queryMopayStatus();
        Assert.assertNotNull(status);

        //订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(dpToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId).build();
        orderdetail.DpOrderDetail();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }

//        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
//        DirectRefundResponse response = huiRefund.superRefund();
//        log.info("获取退款结果:{}", JSON.toJSONString(response));
//        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
//        //Assert.assertEquals(jsonObject.getString("success"),"true");
//        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(dpUserId).userId(Long.valueOf(dpUserId)).build();
        ApplyRefundRequest applyRefundResponse = huiRefund.apply();
        TimeUnit.SECONDS.sleep(2);
        RejectRefundResponse rejectRefundResponse = huiRefund.rejectRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(rejectRefundResponse));
        TimeUnit.SECONDS.sleep(2);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(rejectRefundResponse));
        //Assert.assertEquals(jsonObject.getString("success"),"true");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","拒绝退款失败");
    }
}
