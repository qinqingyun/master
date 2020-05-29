package com.meituan.qa.meishi.Hui.cases.huifullproce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.domain.OrderDetail;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2020-01-10
 * 用例简介:     买单使用原价买单方案
 * 数据源:       shopId：24799161
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：点评小程序 ；买单方案：全单折买单；退款方式：直接退款
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境小程序点评下单流程")
@Slf4j
public class TestdpWeixinDiscountScence extends TestDPLogin {
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
    @MethodAnotation(author = "byq", createTime = "2020-01-10", des = "普通下单(原价)")
    public void ms_c_dPWeixinoriginalScenes() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        //1、加载优惠台
        int couponOfferId = CreateOrderUtil.wxaloadUnicashier(dpToken, dpWxClient, "ms_c__dp_wxaloadcashier");
        log.info("折扣couponOfferId:" + couponOfferId);
        //1、输入金额创建订单（下单支付）
        Map<String, String> wxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(dpToken,"ms_c_wxDiscountCreateOrder_01", couponOfferId, null,null);
        orderId = wxaOrderCreateResult.get("orderId");
        log.info("创单支付成功！orderId = " + orderId );
        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();
        //支付后平台校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform_consum");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(1,neworderid,verifyRequest,null);
        //支付后买单校验
        QueryOrderResponse OrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,OrderResponse);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        //订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(dpToken).caseId("ms_c_wxaDetail_001").orderId(orderId).build();
        orderdetail.dpWxOrderDetail();

        //直接退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        //Assert.assertEquals(jsonObject.getString("success"),"true");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //退款后平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_dpdiscount_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(4,neworderid,refundOrderRequest,dpUserId);
        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,orderId);
        orderCheck.maitonOrder(3,refundOrderResponse);
    }

}
