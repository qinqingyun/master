package com.meituan.qa.meishi.Hui.util;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.OrderStatusEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.util.MtraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import java.util.List;
/**
 * Created by buyuqi on 05/06/2020.
 */
@Slf4j
public class CheckOrderUtil extends TestBase {
    public static void checkOldOrderSystem(MappingOrderIds mappingOrderIds,OrderStatusEnum orderStatusEnum) throws Exception {
        if(IS_CHECK_OLD_ORDER_SYSTEM) {
            QueryOrderResponse queryOrderResponse=loopCheck.getMaitonOrder(orderStatusEnum.getStatusName(),mappingOrderIds.getNewOrderId());
            checkOldOrderSystem(orderStatusEnum.getStatusName(),queryOrderResponse);
        }
    }
    //调用老的交易平台接口校验订单状态是否是支付成功
    public static void checkOldOrderSystem(int flag,QueryOrderResponse maitonQueryOrderResponse){
        if (IS_CHECK_OLD_ORDER_SYSTEM) {
            log.info("*************开始校验老交易系统*************");
            MtraceUtil.generatTrace("老交易系统校验");
                Assert.assertTrue(maitonQueryOrderResponse != null,"maitonQueryOrderResponse为空");
                if (flag==1){
                    log.info("下单状态校验");
                    log.info("老系统订单下单状态校验maitonQueryOrderResponse:{}",maitonQueryOrderResponse);
                    Assert.assertEquals(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue(),0,"下单状态校验失败");
                }

                if (flag==2){
                    log.info("老系统订单支付状态校验maitonQueryOrderResponse:{}",maitonQueryOrderResponse);
                    Assert.assertEquals(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue(),10,"支付状态校验失败");
                }
                if(flag==3){
                    log.info("老系统订单退款状态校验maitonQueryOrderResponse:{}",maitonQueryOrderResponse);
                    List<Integer> requestSet = Lists.newArrayList(30,50);
                    Assert.assertTrue(requestSet.contains(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue()),"退款状态校验失败");

                }
            log.info("*******************老交易系统校验通过****************");
        }
    }
    public static void checkNewPlarform(String platformPath, String platformCaseId, MappingOrderIds mappingOrderIds, OrderStatusEnum orderStatusEnum) throws Exception {
        if(IS_CHECK_NEW_ORDER_SYSTEM) {
            JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, platformCaseId);
            JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
            loopCheck.getPlatformStatus(orderStatusEnum.getStatusName(),mappingOrderIds.getNewOrderId(),verifyRequest,null);
        }
    }
    public static MappingOrderIds checkOrderMapping(OrderModel orderModel) throws Exception {
        MappingOrderIds mappingOrderIds=new MappingOrderIds();
        if(MainSystem.equals("OLD_MAIN") || MainSystem.equals("NEW_MAIN")){
            mappingOrderIds = loopCheck.getMappingOrderIds(orderModel.getOrderId());
        }else {
            mappingOrderIds.setNewOrderId(orderModel.getOrderId());
            mappingOrderIds.setOldOrderId(orderModel.getOrderId());
        }
        return mappingOrderIds;
    }
    public static void checkPayOrderResultPage(String caseId, OrderSourceEnum orderSourceEnum, OrderModel orderModel) throws Exception {
        String statusMsg = loopCheck.getPayResultPage(caseId, orderSourceEnum, orderModel.getSerializedId());
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");
    }
    public static void checkOrderDetail(String caseId, OrderSourceEnum orderSourceEnum, OrderModel orderModel) throws Exception {
        String orderDetail = loopCheck.getOrderDetail(caseId, orderSourceEnum, orderModel.getOrderId());
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");
    }





}
