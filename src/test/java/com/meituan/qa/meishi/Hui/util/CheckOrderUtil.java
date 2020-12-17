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
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.MtraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        JSONArray payInfoDTOList = verifyRequest.getJSONArray("payInfoDTOList");
//        JSONObject payjsonObject = payInfoDTOList.getJSONObject(0);
//        productjsonObject.put("amount",orderModel.getPayAmount());
//        JSONArray promoInfoDTOList = payjsonObject.getJSONArray("promoInfoDTOList");
//        if(promoInfoDTOList!=null){
//            JSONObject promoInfoJsonObject = promoInfoDTOList.getJSONObject(0);
//            promoInfoJsonObject.put("amount",orderModel.getPromoAmount());
//            if(orderModel.getMerchantAmount() != "0"){
//                promoInfoJsonObject.put("fromMerchant",orderModel.getMerchantAmount());
//            }else {
//                promoInfoJsonObject.put("fromMerchant",orderModel.getDiscountAmount());
//            }
//            promoInfoJsonObject.put("fromPlatform",orderModel.getPlatformAmount());
//        }

        //动态替换退款时的商家/平台承担金额 refundInfoDTOList
//        JSONArray refundInfoDTOList = verifyRequest.getJSONArray("refundInfoDTOList");
//        JSONObject refundJsonObject = refundInfoDTOList.getJSONObject(0);
//        productjsonObject.put("amount",orderModel.getPayAmount());
//        productjsonObject.put("fromMerchant",orderModel.getPayAmount());
//        JSONArray refundPromoInfoDTOList = refundJsonObject.getJSONArray("promoInfoDTOList");
//        if(refundPromoInfoDTOList!=null){
//            JSONObject refundPromoInfoJsonObject = refundPromoInfoDTOList.getJSONObject(0);
//            refundPromoInfoJsonObject.put("amount", orderModel.getPromoAmount());
//            if(orderModel.getMerchantAmount() != "0"){
//                refundPromoInfoJsonObject.put("fromMerchant",orderModel.getMerchantAmount());
//            }else {
//                refundPromoInfoJsonObject.put("fromMerchant",orderModel.getDiscountAmount());
//            }
//            refundPromoInfoJsonObject.put("fromPlatform", orderModel.getPlatformAmount());
//        }
    }
    public static MappingOrderIds checkOrderMapping(OrderModel orderModel) throws Exception {
        // 生成新Trace
        MtraceUtil.generatTrace("新老订单映射");
        MappingOrderIds mappingOrderIds=new MappingOrderIds();
        if(MainSystem.equals("OLD_MAIN") || MainSystem.equals("NEW_MAIN")){
            mappingOrderIds = loopCheck.getMappingOrderIds(orderModel.getOrderId());
        }else {
            mappingOrderIds.setNewOrderId(orderModel.getOrderId());
            mappingOrderIds.setOldOrderId(orderModel.getOrderId());
        }
        if (mappingOrderIds == null){
            Assert.assertNotNull(mappingOrderIds,"idmapping结果为null，调idmapping结果异常或订单未双写");
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
    public static void checkMerchantOrderDetail(String caseId, OrderModel orderModel,OrderStatusEnum orderStatusEnum){
        JSONObject merchentOrderDetail = loopCheck.getMerchentOrderDetail("ms_c_orderDetail_001", orderModel.getSerializedId(),orderModel.getMtShopId());
        log.info("商户订单详情页信息{}",merchentOrderDetail.toString());
        JSONObject expect = maitonApi.getExpect(caseId);
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "dealAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.dealAmount").toString(),"商家订单dealAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "originAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.originAmount").toString(),"商家订单originAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "discountAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.discountAmount").toString(),"商家订单discountAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "depositAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.depositAmount").toString(),"商家订单depositAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "opbVipAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.opbVipAmount").toString(),"商家订单opbVipAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "maitonAmount").toString(),JsonPath.read(expect, "merchantDetailCheckInfo.maitonAmount").toString(),"商家订单maitonAmount金额与预期不符");
        switch (orderStatusEnum){
            case 支付成功:
                Assert.assertEquals(JsonPath.read(merchentOrderDetail, "orderStatusStr"),"已支付","商家订单状态与预期不符");
                break;
            case 退款成功:
                Assert.assertEquals(JsonPath.read(merchentOrderDetail, "orderStatusStr"),"已退款","商家订单状态与预期不符");
        }
    }
    public static JSONObject getHuiOrderDetailVo(String merchantOrderDetail){
        JSONObject jsonObject = null;
        Pattern pattern = Pattern.compile("huiOrderDetailVo:.*}");
        Matcher matcher = pattern.matcher(merchantOrderDetail);
        if(matcher.find()){
            String group = matcher.group();
            group = group.substring(group.indexOf("{"));
            jsonObject = JSONObject.parseObject(group);
            if(jsonObject == null){
                Assert.assertTrue(false,"查询商家订单详情，未查询到信息");
            }
        }
        return jsonObject;
    }
}
