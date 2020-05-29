package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019
 * 用例简介:     买单使用原价买单方案
 * 数据源:       poiId：97224769
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款
 **/

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境app原价流程")
@Slf4j
public class TestOriginalScenes  extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String bisid = CommonLoginUtil.merchantAPPLogin();//商家端登录后获取
    List<String> orderCreateResult = new ArrayList();
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

    public void ms_c_originalScenes_01() throws Exception{
        if( doubleWriteMode.equals("NEW"))
            LionUtil.setUserWriteList(mtUserId+"_1");

       DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_mtloadUnifiedCashier_05");
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
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01生成订单diff");

        //2、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //支付后买单侧校验
        QueryOrderResponse QueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,QueryOrderResponse);

        //支付成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01支付成功订单diff");

        //支付结果页
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .serializedId(serializedId)
                .token(mtToken)
                .userAgent(mtClient).build();
        String status = orderState.queryMopayStatus();
        Assert.assertNotNull(status);

        //订单详情页
        OrderDetail orderdetail=OrderDetail.builder().token(mtToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId).build();
        orderdetail.MtOrderDetail();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        // 直接发起退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        //Assert.assertEquals(jsonObject.getString("success"),"true","发起退款失败");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //平台校验

        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //买单侧退款校验
        TimeUnit.SECONDS.sleep(5);
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //退款成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_originalScenes_01退款成功订单diff");
    }
    @AfterSuite
    public void afterSuite(){
        int diffSumCount = CommanDiff.diffSumCount.getAndIncrement();
        log.info("总比对数量diffSumCount = "+ diffSumCount);
        int diffFailCount = CommanDiff.diffFailCount.getAndIncrement();
        log.info("总失败数量diffFailCount ="+ diffFailCount);
        //一致率计算
        double diffPercent = (diffSumCount - diffFailCount) * 1.00 / diffSumCount;
        Assert.assertTrue(diffFailCount <= 2);
    }
}
