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
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.meituan.resv.i.thrift.service.TResvIGoodsService;
import com.sankuai.meituan.resv.order.thrift.service.RemoteResvOrderService;
import com.sankuai.meituan.resv.trade.idl.TResvTradeService;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2019-12-06
 * 用例简介:     买单使用预订金支付，0元单
 * 数据源:       poiId：6207656
 * 主要流程:     预订订单生成 -> 查询优惠台 -> 下单 -> 详情 -> 退款
 * 备注:        平台：点评侧 ；买单方案：原价买单；退款方式：极速退款
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境，预定金0元单场景")
@Slf4j
public class TestZeroOrderDP extends TestDPLogin {
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
    @ThriftAPI(appkey = "com.sankuai.resv.c.i",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    TResvIGoodsService.Iface tResvIGoodsService;
    @ThriftAPI(appkey = "com.sankuai.resv.c.trade",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    TResvTradeService.Iface tResvTradeService;
    @ThriftAPI(appkey = "com.sankuai.resv.c.order",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    RemoteResvOrderService remoteResvOrderService;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;


    @Test(groups = "P1")
    @MethodAnotation(author = "byq", createTime = "2020-02-04",  des = "预定金0元单场景，点评侧")

    public void ms_c_resvZeroScenes_02() throws Exception{
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //0、预订金订单下单
        ResvOrderId resvOrder = new ResvOrderId();
        ResvInfo resvInfo = new ResvInfo(tResvIGoodsService,tResvTradeService,remoteResvOrderService);
        String resvOrderId = resvOrder.reserveOrder(resvInfo,20).toString();
        log.info("预订订单:{}", resvOrderId);

        //1、加载优惠台
//        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_02");
//        log.info("折扣couponOfferId:" + couponOfferId);
        //预定金买单下单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrderResv(dpToken, dpClient, "ms_DP_resv_uniCashierCreateOrder_01",resvOrderId);
        orderId = orderCreateResult.get(0);
        serializedId = orderCreateResult.get(1);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId );

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        //支付后平台校验
        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_resvZeroScenes_platform_consum");
        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);

        //支付后买单侧校验
        QueryOrderResponse QueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
        orderCheck.maitonOrder(2,QueryOrderResponse);

        //支付成功订单diff
        differentRecord.diffRecordList(oldorderid,neworderid,"ms_c_resvZeroScenes_01支付成功订单diff");

        //支付结果页
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
        TimeUnit.SECONDS.sleep(3);
        String status = orderState.queryMopayStatus();
        Assert.assertNotNull(status);

        //订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(dpToken).caseId("ms_c_huiFullProcess_101_huiMaitonOrderMT").orderId(orderId).build();
        orderdetail.DpOrderDetail();


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
        //Assert.assertEquals(jsonObject.getString("success"),"true");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_resvZeroScenes_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(3,neworderid,refundOrderRequest,String.valueOf(dpToken));

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
//        orderCheck.maitonOrder(3,refundOrderResponse);
    }

}
