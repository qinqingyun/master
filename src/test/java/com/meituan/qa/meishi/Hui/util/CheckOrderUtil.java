package com.meituan.qa.meishi.Hui.util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.jayway.jsonpath.JsonPath;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.OrderStatusEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.util.MtraceUtil;
import com.sankuai.meituan.resv.deposit.enums.SourceEnum;
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
            QueryOrderResponse queryOrderResponse=loopCheck.getMaitonOrder(orderStatusEnum.getStatusName(),mappingOrderIds.getOldOrderId());
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
    public static void checkNewPlatform(String platformPath, String platformCaseId, MappingOrderIds mappingOrderIds,OrderModel orderModel, OrderStatusEnum orderStatusEnum) throws Exception {
        if(IS_CHECK_NEW_ORDER_SYSTEM) {
            JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, platformCaseId);
            JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
            updatePlatformVerifyRequest(verifyRequest,orderModel);
            loopCheck.getPlatformStatus(orderStatusEnum.getStatusName(),mappingOrderIds.getNewOrderId(),verifyRequest,null);
        }
    }
    public static void updatePlatformVerifyRequest(JSONObject verifyRequest,OrderModel orderModel){
        //动态替换下单时使用的买单方案  productInfoDTOList
        JSONArray productInfoDTOList = verifyRequest.getJSONArray("productInfoDTOList");
        JSONObject productjsonObject = productInfoDTOList.getJSONObject(0);
        productjsonObject.put("productId",orderModel.getSchemeId());
        //动态替换下单时的商家/平台承担金额 payInfoDTOList
        JSONArray payInfoDTOList = verifyRequest.getJSONArray("payInfoDTOList");
        JSONObject payjsonObject = payInfoDTOList.getJSONObject(0);
        productjsonObject.put("amount",orderModel.getPayAmount());
        JSONArray promoInfoDTOList = payjsonObject.getJSONArray("promoInfoDTOList");
        JSONObject promoInfoJsonObject = promoInfoDTOList.getJSONObject(0);
        promoInfoJsonObject.put("amount",orderModel.getPromoAmount());
        promoInfoJsonObject.put("fromMerchant",orderModel.getMerchantAmount());
        promoInfoJsonObject.put("fromPlatform",orderModel.getPlatformAmount());
        //动态替换退款时的商家/平台承担金额 refundInfoDTOList
        JSONArray refundInfoDTOList = verifyRequest.getJSONArray("refundInfoDTOList");
        JSONObject refundJsonObject = refundInfoDTOList.getJSONObject(0);
        productjsonObject.put("amount",orderModel.getPayAmount());
        productjsonObject.put("fromMerchant",orderModel.getPayAmount());
        JSONArray refundPromoInfoDTOList = refundJsonObject.getJSONArray("promoInfoDTOList");
        JSONObject refundPromoInfoJsonObject = refundPromoInfoDTOList.getJSONObject(0);
        refundPromoInfoJsonObject.put("amount",orderModel.getPromoAmount());
        refundPromoInfoJsonObject.put("fromMerchant",orderModel.getMerchantAmount());
        refundPromoInfoJsonObject.put("fromPlatform",orderModel.getPlatformAmount());
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
    public static void checkPayOrderResultPage(String caseId, OrderModel orderModel) throws Exception {
        String statusMsg = loopCheck.getPayResultPage(caseId, orderModel.getSerializedId());
        Assert.assertEquals(statusMsg,"支付成功","支付结果页状态：支付失败或支付中");
    }
    public static void checkOrderDetail(String caseId, OrderModel orderModel,OrderSourceEnum sourceEnum) throws Exception {
        String orderDetail = loopCheck.getOrderDetail(caseId, orderModel.getOrderId(), sourceEnum);
        Assert.assertEquals(orderDetail,"支付成功","订单详情页状态未支付成功");
    }





}
