package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
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
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2019
 * 用例简介:     买单使用原价买单方案
 * 数据源:       shopId：65731456
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：点评侧 ；买单方案：原价买单；退款方式：用户申请商家退款
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境app原价流程")
@Slf4j
public class TestdpOriginalScence_New extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
//    private String bisid = CommonLoginUtil.merchantAPPLogin();//商家端登录后获取
    List<String> dpResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();


    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;

    //String doubleWriteMode = "NEW";
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1")
    @MethodAnotation(author = "byq", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "普通下单(原价)")
    public void ms_c_dPoriginalScenes(String doubleWriteMode) throws Exception {
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(dpUserIdByq + "_0");
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
            Tracer.putContext("PAY_MOCK","TRUE");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(dpUserIdByq+"_0");
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }

        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //1、输入金额创建订单（下单）
        dpResult = checkLoop.uniCashierCreateOrder(dpTokenByq,dpClient,"ms_c_dploadUnifiedCashier");
        payToken = dpResult.get(0);
        tradeNo = dpResult.get(1);
        orderId = dpResult.get(2);
        serializedId = dpResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        // 平台下单校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);

        //创建订单成功diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_dPoriginalScenes创建订单成功diff");

        //2、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, dpTokenByq);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpOriginalScenes_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单支付后校验
        QueryOrderResponse OrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,OrderResponse);

        //订单支付成功diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_dPoriginalScenes订单支付成功diff");

        //4、支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,dpTokenByq,dpClient,"ms_c_huiFullProcess_101_queryMopayStatus");
//        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,dpTokenByq,"DP","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        //用户申请，商家退款
//     String renfundOrderStatus = checkLoop.getOrderRefundDetailAccess(orderId,dpUserId);
//        Assert.assertEquals(renfundOrderStatus,"true","订单退款失败");
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(dpUserIdByq).userId(Long.valueOf(dpUserIdByq)).build();
        log.info("执行退款订单id{}",orderId);
        ApplyRefundRequest applyRefundResponse = huiRefund.apply();
        TimeUnit.SECONDS.sleep(2);
        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        TimeUnit.SECONDS.sleep(3);
        agreeRefundResponse.isSuccess();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //退款mock
        Long amount = createOrderResponse.getOrderDTO().getUserAmount().longValue() * 100;
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        //平台退款校验
//        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_dpOriginalScenes_platform_consum");
//        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
//        log.info("新平台退款订单id{}",neworderid);
//        //checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,dpUserIdByq);
//        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,"5021293686");

        //买单侧退款校验  回放流量退款订单id传newid  需要排查
        log.info("老系统退款订单id{}",oldorderid);
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //订单退款成功diff
//        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_dPoriginalScenes订单退款成功diff");
    }

}
