package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderMResult;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019-12-24
 * 用例简介:     买单使用原价买单方案
 * 数据源:       shopId：65731456
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：M站 ；买单方案：原价买单；退款方式：直接退款。M站只有原价买单，只走老不走新
 **/

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境m站原价流程")
@Slf4j
public class TestMoriginalScenes extends TestDPLogin {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;

    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;

    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    String  doubleWriteMode="NEW";

    @Test(groups = "P1")
    @MethodAnotation(author = "qinqingyun", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_m_original_scenes() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        /*下单*/
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        String CASEID = "ms_c_ajaxcreateorder_";
        HuiCreateOrder createOrder = HuiCreateOrder.builder()
                .token(dpToken)
                .userAgent(mClient)
                .caseid(CASEID)
                .couponProduct(null)
                .deskcoupon(null)
                .build();
        HuiCreateOrderMResult mcreateresult = createOrder.requestMCreate();
        String payToken = mcreateresult.getPayToken();
        String tradeNo = mcreateresult.getTradeNo();
        Long orderId = mcreateresult.getPayOrderID();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        log.info("创单成功！orderId = " + orderId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);


        // 新老订单映射
//        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(String.valueOf(orderId));
//        String neworderid= mappingOrderIds.getNewOrderId();
//        String oldorderid= mappingOrderIds.getOldOrderId();


        // 平台创单校验
//        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpOriginalScenes_platform_consum");
//        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
//        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,String.valueOf(orderId));
        orderCheck.maitonOrder(1,createOrderResponse);

        //订单生成diff
        //differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_hui_m_original_scenes生成订单diff");

        //支付
//        CashierPay cashierPay = CashierPay.builder().payToken(payToken).token(dpToken).tradeNo(tradeNo).build();
//        boolean payret = cashierPay.orderPay();
//        Assert.assertTrue(payret, tradeNo + "订单支付失败");
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);
        //平台支付校验
       /* JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        PlatformCheckInfo platformCheckOrder = PlatformCheckInfo.builder()
                .flag(4)
                .orderid(Long.valueOf(orderId))
                .verifyRequest(payVerifyRequest)
                .build();
        platformCheckOrder.PlatformCheckInfo();*/

        //买单BP侧支付校验
        QueryOrderResponse maitonQueryOrderResponse=checkLoop.getMaitonOrder(2,String.valueOf(orderId));
        orderCheck.maitonOrder(2,maitonQueryOrderResponse);

        //3、支付结果页
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .token(dpToken)
                .serializedId(String.valueOf(orderId))
                .userAgent(mClient).build();
        String status = orderState.queryMStatus();
        Assert.assertNotNull(status);

        //4、订单详情页

        OrderDetail orderdetail= OrderDetail.builder()
                .token(dpToken)
                        .caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT")
                .orderId(String.valueOf(orderId))
                .userAgent(mClient)
                .build();
        orderdetail.mOrderDetail();

        //直接退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();

        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));

        //平台退款校验*/


//        //买单BP侧订单状态校验
//        MaitonQueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
//        orderCheck.maitonOrder(3,refundOrderResponse);



    }


}
