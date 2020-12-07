package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.domain.CommanDiff;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境app原价流程")
@Slf4j
public class TestOriginalScenes_NEW extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    //private String bisid = CommonLoginUtil.merchantAPPLogin();//商家端登录后获取
    List<String> orderCreateResult = new ArrayList();
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
    //String  doubleWriteMode = "OLD";
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1",description = "美团app，买单使用原价买单方案->预订订单生成->方案选取->下单->支付->用户申请->商家同意->退款")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "普通下单(原价)")

    public void ms_c_originalScenes_01(String  doubleWriteMode) throws Exception{
        PayNotifyMockRequest payNotifyMockRequest = new PayNotifyMockRequest();
        RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();
        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(mtUserId + "_1");
            payNotifyMockRequest.setScene(Scene.NEW_MAIN);
            refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
            Tracer.putContext("PAY_MOCK","TRUE");
            Tracer.putContext("MOCK_REFUND_SettleAccount","TRUE");
            Tracer.putContext("SettleMock", "true");
        }
        if( doubleWriteMode.equals("OLD"))
        {
            LionUtil.setUserBlackList(mtUserId+"_1");
            payNotifyMockRequest.setScene(Scene.OLD_MAIN);
            refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
            Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
        }
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //输入金额创建订单
        orderCreateResult = checkLoop.uniCashierCreateOrder(mtToken,mtClient,"ms_c_4Verify_mtloadUnifiedCashier_05");
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

        // 平台下单校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,maitonQueryOrderResponse);

        //订单生成diff
//        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01生成订单diff");

        //2、支付
        Long amount = maitonQueryOrderResponse.getOrderDTO().getCurrentAmount().longValue() * 100;
        payNotifyMockRequest.setTradeNo(tradeNo);
        payNotifyMockRequest.setOrderId(neworderid);
        payNotifyMockRequest.setAmount(amount);
        if(doubleWriteMode.equals("OLD")){
            payNotifyMockRequest.setOutNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockPay(payNotifyMockRequest);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //支付后买单侧校验
        QueryOrderResponse QueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,QueryOrderResponse);

        //支付成功订单diff
//        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01支付成功订单diff");

        //支付结果页
        String statusMsg = checkLoop.getOrderState(serializedId,mtToken,mtClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,mtToken,"MT","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }

        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //退款mock
        refundNotifyMockRequest.setAmount(amount);
        refundNotifyMockRequest.setOrderId(neworderid);
        refundNotifyMockRequest.setTradeNo(tradeNo);
        if(doubleWriteMode.equals("OLD")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+orderId);
        }
        PayMockUtil.mockRefund(refundNotifyMockRequest);

        //平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

       //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //退款成功订单diff
//        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01退款成功订单diff");
    }

//    @AfterSuite
//    public void afterSuite(){
//        int diffSumCount = CommanDiff.diffSumCount.getAndIncrement();
//        log.info("总比对数量diffSumCount = "+ diffSumCount);
//        int diffFailCount = CommanDiff.diffFailCount.getAndIncrement();
//        log.info("总失败数量diffFailCount ="+ diffFailCount);
//        //一致率计算
//        double diffPercent = (diffSumCount - diffFailCount) * 1.00 / diffSumCount;
//        Assert.assertTrue(diffFailCount <= 2,"新老数据diff比对失败");
//    }
}


