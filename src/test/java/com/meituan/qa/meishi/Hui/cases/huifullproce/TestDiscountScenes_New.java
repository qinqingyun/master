package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.domain.DifferentRecord;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.PayMockUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import com.sankuai.nibqa.trade.payMock.params.enums.Scene;
import com.sankuai.nibqa.trade.payMock.params.request.PayNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.peer.LightweightPeer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2020-01-13
 * 用例简介:     买单使用7折优惠买单方案
 * 数据源:       poiId：41782956
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：7折优惠买单；退款方式：直接退款；双写模式：支持以新+以老
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境app全单折流程")
@Slf4j
public class TestDiscountScenes_New extends TestDPLogin  {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    String caseId = "ms_c_4Verify_uniCashierCreateOrder_03";
    OrderCheck orderCheck=new OrderCheck();

    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    String  doubleWriteMode = "OLD";
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1",description = "美团app，买单使用折扣买单方案->方案选取->下单->支付->用户申请->商家同意->退款")
    @MethodAnotation(author = "byq", createTime = "2020-01-13", updateTime = "2020-01-13", des =
            "普通下单(全单折)")
    public void ms_c_discountScenes_01() throws Exception {

        PayNotifyMockRequest payNotifyMockRequest = new PayNotifyMockRequest();
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(mtUserId + "_1");
            payNotifyMockRequest.setScene(Scene.NEW_MAIN);
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
            Tracer.putContext("PAY_MOCK","TRUE");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(mtUserId+"_1");
            payNotifyMockRequest.setScene(Scene.OLD_MAIN);
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }

        //数据diff
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //加载优惠台
        int couponOfferId = checkLoop.getCouponOfferId(mtToken,mtClient,"ms_c_4Verify_loadUnifiedCashier_02");
        log.info("折扣couponOfferId:" + couponOfferId);

        //创建订单
        orderCreateResult = checkLoop.uniCashierCreateOrder(mtToken,mtClient,caseId,couponOfferId);
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
              + " payToken = " + payToken);

        //新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //下单后平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_discount_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //下单后买单侧校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);

        //订单生成diff
        try {
            differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_discountScenes_01生成订单diff");
        }catch (Exception e){
            log.info("调用diff工具异常{}",e.getMessage());
        }

        //3、支付
        Long amount = createOrderResponse.getOrderDTO().getCurrentAmount().longValue() * 100;
        payNotifyMockRequest.setTradeNo(tradeNo);
        payNotifyMockRequest.setOrderId(neworderid);
        payNotifyMockRequest.setAmount(amount);
        if(doubleWriteMode.equals("OLD")){
            payNotifyMockRequest.setOutNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockPay(payNotifyMockRequest);
        //CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_discount_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //支付后买单侧校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,maitonQueryOrderResponse);
        //增加金额校验

        //支付成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_discountScenes_01支付成功订单diff");

       //支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,mtToken,mtClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //5、订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,mtToken,"MT","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        //新老订单延时5s，可以加轮询，
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        //直接退款
        //TODO:退款请求api层接口。用户／商家／客服的退款请求接口
        //TODO：商家同意退款、商家拒绝退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(String.valueOf(mtUserId)).userId(mtUserId).build();
        log.info("执行退款订单id{}",orderId);
        ApplyRefundRequest applyRefundRequest = huiRefund.apply();
        log.info("申请退款结果:{}",JSON.toJSONString(applyRefundRequest));
        TimeUnit.SECONDS.sleep(2);
        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        TimeUnit.SECONDS.sleep(3);
        agreeRefundResponse.isSuccess();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //退款mock
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        //平台校验已消费退款
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_discount_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //退款后买单侧校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //退款成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_discountScenes_01退款成功订单diff");
    }
}
