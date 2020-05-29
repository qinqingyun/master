package com.meituan.qa.meishi.Hui.cases.huifullproce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019
 * 用例简介:     买单使用原价买单方案
 * 数据源:       shopId：65731456
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：点评侧 ；买单方案：原价买单；退款方式：用户申请商家退款
 **/

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境app原价流程")
@Slf4j
public class TestdpOriginalScence extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> dpResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    String  doubleWriteMode="NEW";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;


    @Test(groups = "P1")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "普通下单(原价)")
    public void ms_c_dPoriginalScenes() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //1、输入金额创建订单（下单）
        dpResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_dplunicashiercreateorder");
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
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);

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
        OrderDetail orderdetail= OrderDetail.builder().token(dpToken).caseId("ms_c_huiFullProcess_huiMaitonOrderDP").orderId(orderId).build();
        orderdetail.DpOrderDetail();

        //用户申请，商家退款
        TimeUnit.SECONDS.sleep(2);
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(dpUserId).userId(Long.valueOf(dpUserId)).build();
        ApplyRefundRequest applyRefundResponse = huiRefund.apply();
        TimeUnit.SECONDS.sleep(2);
        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        TimeUnit.SECONDS.sleep(2);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        //Assert.assertEquals(jsonObject.getString("success"),"true");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //平台退款校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_dpOriginalScenes_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,"5021293686");

        //买单侧退款校验   回放流量退款订单id传newid  需要排查
//        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
//        orderCheck.maitonOrder(3,refundOrderResponse);

        //订单退款成功diff
//        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_dPoriginalScenes订单退款成功diff");


    }

}
