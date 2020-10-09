package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqiimport com.sankuai.nibqa.trade.payMock.params.enums.Scene;
 * Date:        2019-12-06
 * 用例简介:     买单使用预订金支付，0元单
 * 数据源:       poiId：95191712
 * 主要流程:     预订订单生成 -> 查询优惠台 -> 下单 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：极速退款
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境，预定金0元单场景")
@Slf4j
public class TestZeroOrder_New extends TestDPLogin {
    private String orderId;
    private String serializedId;
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
    String  doubleWriteMode="NEW";

    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1",description = "美团app，预定金0元单场景，买单使用预订买单方案->预订订单生成->方案选取->下单->支付->用户申请->商家同意->退款")
    @MethodAnotation(author = "byq", createTime = "2019-12-13",  des = "预定金0元单场景")

    public void ms_c_resvZeroScenes_01() throws Exception{
        log.info("ms_c_resvZeroScenes_01走老走新的状态{}",doubleWriteMode);
        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //0、预订金订单下单
        ResvOrderId resvOrder = new ResvOrderId();
        ResvInfo resvInfo = new ResvInfo(tResvIGoodsService,tResvTradeService,remoteResvOrderService);
        Integer resvOrderId = 0;
        for(int i = 0; i < 5; i++){
            resvOrderId = resvOrder.reserveOrder(resvInfo,10);
            if(resvOrderId > 0){
                break;
            }
        }
        String resvMaitonOrderId = resvOrderId.toString();
        log.info("预订订单:{}", resvOrderId);

        if( doubleWriteMode.equals("NEW")) {
            LionUtil.setUserWriteList(mtUserId + "_1");
            Tracer.putContext("MOCK_REFUND_SettleAccount","TRUE");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(mtUserId+"_1");
        }
        //预定金买单下单
        orderCreateResult = checkLoop.uniCashierCreateOrderResv(mtToken,mtClient,"ms_MT_resv_uniCashierCreateOrder_01",resvMaitonOrderId);
        orderId = orderCreateResult.get(0);
        serializedId = orderCreateResult.get(1);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId );

        // 新老订单映射
        MappingOrderIds mappingOrderIds=checkLoop.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        TimeUnit.SECONDS.sleep(3);
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
        String statusMsg = checkLoop.getOrderState(serializedId,mtToken,mtClient,"ms_c_huiFullProcess_101_queryMopayStatus");
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");

        //订单详情页
        String orderDetail = checkLoop.getOrderDetail(orderId,mtToken,"MT","ms_c_huiFullProcess_101_huiMaitonOrderMT");
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");

        // 直接发起退款
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        // 直接发起退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //平台校验
        JSONObject refundOrder = DBDataProvider.getRequest(platformPath, "ms_c_resvZeroScenes_platform_consum");
        JSONObject refundOrderRequest= refundOrder.getJSONObject("params");
        checkLoop.getPlatformStatus(3,neworderid,refundOrderRequest,String.valueOf(mtUserId));

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,oldorderid);
//        orderCheck.maitonOrder(3,refundOrderResponse);
    }
}
