package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderMResult;
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

import java.util.concurrent.TimeUnit;

/**
 * Author:      qinqingyun
 * Modified:    buyuqi
 * Date:        2019-12-24
 * 用例简介:     买单使用7折优惠买单方案
 * 数据源:       shopId：24799161
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：M站 ；买单方案：7折优惠买单；退款方式：直接退款
 **/

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境m站全单折流程")
@Slf4j
public class TestMDiscountScenes extends TestDPLogin {

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;

    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;

    String  doubleWriteMode="NEW";

    @Test(groups = "P1")
    @MethodAnotation(author = "qinqingyun", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_m_discount_scenes() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //下单
        String CASEID = "ms_c_ajaxcreateorder_01";
        HuiCreateOrder createOrder = HuiCreateOrder.builder()
                .token(dpToken)
                .userAgent(mClient)
                .caseid(CASEID)
                .couponProduct(null)
                .deskcoupon(null)
                .build();
        HuiCreateOrderMResult mcreateresult = createOrder.requestMCreate();
        String payToken = mcreateresult.getPayToken();
        Long orderId = mcreateresult.getPayOrderID();
        String tradeNo = mcreateresult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        log.info("创单成功！order:{}", JSON.toJSONString(mcreateresult));


        //新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(String.valueOf(orderId));
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //下单后平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);

      //买单下单校验
        QueryOrderResponse createOrderResponse=checkLoop.getMaitonOrder(1,oldorderid);
        orderCheck.maitonOrder(1,createOrderResponse);

        //订单生成diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_hui_m_discount_scenes生成订单diff");

        //2、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, dpToken);
        //平台支付校验

        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //买单下单校验
        QueryOrderResponse OrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,OrderResponse);

        //订单支付成功diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_hui_m_discount_scenes订单支付成功diff");

        //3、支付结果页
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        QueryOrderState orderState = QueryOrderState.builder()
                .caseId("ms_c_huiFullProcess_101_queryMopayStatus")
                .serializedId(String.valueOf(orderId))
                .token(dpToken)
                .userAgent(mClient).build();
        String status = orderState.queryMStatus();
        Assert.assertNotNull(status);

        //4、订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(dpToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId.toString()).build();
        orderdetail.mOrderDetail();

        // 5.直接发起退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));

        //退款平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,dpUserId);

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
        orderCheck.maitonOrder(3,refundOrderResponse);

        //订单退款成功diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_hui_m_discount_scenes订单退款diff");
    }
}
