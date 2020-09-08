package com.meituan.qa.meishi.Hui.cases.scene;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.ApplyRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueDetail;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.util.CheckOrderUtil;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.MaitonHongbaoTResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPApp;
import static com.meituan.qa.meishi.Hui.entity.OrderStatusEnum.*;
import static com.meituan.qa.meishi.Hui.entity.OrderStatusEnum.退款成功;
@Slf4j
public class DianPingAppTest extends TestBase {
    private String platformPath="/platformPath";
    /**
     * 用例简介:     买单使用原价买单方案
     * 数据源:       shopId：65731456
     * 主要流程:     下单 -> 支付 -> 详情 -> 退款
     * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款
     **/
    @Test(groups = "P1",description = "点评app，买单使用原价买单方案->方案选取->下单->支付->商家直退->退款成功")
    public void dpOriginTest() throws Exception {
        String caseId = "dpOriginTest";
        String platformCaseId = "ms_c_dpOriginalScenes_platform_consum";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        //1.创建订单
        OrderModel orderModel = loopCheck.uniCashierCreateOrder(caseId);
        log.info("创单成功！{}:", JSON.toJSONString(orderModel));
        //2.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //3.平台下单校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,下单成功);
        //4.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //5.支付mock
        maitonApi.orderPay(orderModel);
        //payMockUtil.mockPay(orderModel,mappingOrderIds);
        //6.支付后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,支付成功);
        //7.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //8.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //9.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //10.商户订单详情页校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
        //11.商户订单中心推送校验
        //12.商家直退
        DirectRefundResponse directRefundResponse = thriftApi.superRefund("qa-autocase", orderModel);
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //14.退款mock
        //payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //15.退款后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,退款成功);
        //16.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //17.退款后商户订单中心校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }
    @Test(groups = "P1",description = "点评app，买单使用折扣买单方案->方案选取->下单->支付->用户申请->商家同意->退款")
    public void dpDiscountTest() throws Exception {
        String caseId = "dpDiscountTest";
        String loadUnifiedCashier = "ms_c__dp_loadUnifiedCashier";
        String platformCaseId = "ms_c_dpdiscount_platform_consum";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        //1.加载优惠台
        CouponProduct couponProduct = loopCheck.loadUnifiedCashier(loadUnifiedCashier);
        log.info("折扣couponOfferId:{}" + JSON.toJSONString(couponProduct));
        //2.创建订单
        OrderModel orderModel = loopCheck.uniCashierCreateOrder(caseId, couponProduct);
        log.info("创单成功！{}:",JSON.toJSONString(orderModel));
        //3.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //4.平台下单校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,下单成功);
        //5.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //6.支付mock
        maitonApi.orderPay(orderModel);
        //payMockUtil.mockPay(orderModel,mappingOrderIds);
        //7.支付后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,支付成功);
        //8.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //9.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //10.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //11.商户订单详情页校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
        //12.商户订单中心推送校验
        //13.用户申请退款校验
        ApplyRefundResponse applyRefundResponse = thriftApi.applyRefund(orderModel, maitonApi.getUserModel().get());
        log.info("申请退款结果:{}",JSON.toJSONString(applyRefundResponse));
        TimeUnit.SECONDS.sleep(1);
        AgreeRefundResponse agreeRefundResponse = thriftApi.agreeRefund(orderModel, maitonApi.getUserModel().get());
        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
        TimeUnit.SECONDS.sleep(1);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //14.退款mock
        //payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //15.退款后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,退款成功);
        //16.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //17.退款后商户订单中心校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }
    /**
     * 用例简介:     买单使用原价买单方案，使用商家券
     * 数据源:       poiId：97224769
     * 主要流程:     下单 -> 支付 -> 详情 -> 退款
     * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款；促销方式：使用商家券
     **/
    @Test(groups = "P1",description = "点评app，使用商家优惠券买单：返券->发券->买单使用商家优惠券->下单->支付->极速退款")
    public void dpShopPromoTest() throws Exception {
        String caseId = "dpShopPromoTest";
        String getHuiPromodeskCaseId= "ms_c_dpshopScenes_platform_01";
        String loadUnifiedCashier = "ms_c_dpshoploadUnifiedCashier_02";
        String platformCaseId = "ms_c_mtshopScenes_platform";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        //1.查询用户账号下是否有可用商家券
        String shopCouponid = "120000901026380";
        DeskCoupon deskCoupon = loopCheck.getShopCouponCipher(shopCouponid,getHuiPromodeskCaseId);
        //2.若没有商家券，调用发券接口发券
        if(deskCoupon == null){
            Assert.assertTrue(false);
            MaitonHongbaoTResponse maitonHongbaoTResponse = loopCheck.setShopPromo(maitonApi.getUserModel().get(), 65731456,DPApp);
            shopCouponid = maitonHongbaoTResponse.data.stream().findFirst().get().id;
            //下单前查询优惠
            deskCoupon = loopCheck.getShopCouponCipher(shopCouponid,getHuiPromodeskCaseId);
            Assert.assertTrue(deskCoupon != null,"获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        }
        //3.加载优惠台，原价买单+商家券，可以无需加载优惠台
//        CouponProduct couponProduct = loopCheck.loadUnifiedCashier(loadUnifiedCashier);
//        log.info("折扣couponOfferId:{}" + JSON.toJSONString(couponProduct));
        //4.创建订单
        OrderModel orderModel = loopCheck.uniCashierCreateOrder(caseId,null,deskCoupon,0);
        log.info("创单成功！{}:",JSON.toJSONString(orderModel));
        //5.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //6.平台下单校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,下单成功);
        //7.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //8.支付mock
        maitonApi.orderPay(orderModel);
        //payMockUtil.mockPay(orderModel,mappingOrderIds);
        //9.支付后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,支付成功);
        //10.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //11.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //12.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //13.商户订单详情页校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
        //14.商户订单中心推送校验
        //15.商家直退
        DirectRefundResponse directRefundResponse = thriftApi.superRefund("qa-autocase", orderModel);
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //16.退款mock
        //payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //17.退款后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,退款成功);
        //18.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //19.退款后商户订单中心校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }
    /**
     * 用例简介:     买单使用原价买单方案，使用平台券6元
     * 数据源:       poiId：97224769
     * 主要流程:     下单 -> 支付 -> 详情 -> 退款
     * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款；促销方式：使用红包平台券
     **/
    @Test(groups = "P1",description = "点评app，使用平台优惠券买单：返券->发券->买单使用商家优惠券->下单->支付->极速退款")
    public void dpCouponPromoTest() throws Exception {
        String caseId = "dpCouponPromoTest";
        String getHuiPromodeskCaseId= "ms_c_hui_gethuipromodesk_03";
        String loadUnifiedCashier = "ms_c_4Verify_loadUnifiedCashier_05";
        String platformCaseId = "dpCouponPromoTest_platform";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        //1.查询用户账号下是否有可用平台券
        String couponId = "23738010020695727";
        DeskCoupon deskCoupon = loopCheck.getPlatformCouponCipher(couponId,getHuiPromodeskCaseId);
        //2.若没有平台券，调用发券接口发券
        if(deskCoupon == null){
            UnifiedCouponIssueResponse unifiedCouponIssueResponse = loopCheck.setCouponPromo(maitonApi.getUserModel().get(),519477930,DPApp);
            BigDecimal couponAmount = BigDecimal.ZERO;
            if (unifiedCouponIssueResponse.getResultCode() == 0) {
                Optional<UnifiedCouponIssueDetail> detailOptional = unifiedCouponIssueResponse.getResult().getResult().stream().findFirst();
                if (detailOptional.isPresent()) {
                    UnifiedCouponIssueDetail issueDetail = detailOptional.get();
                    couponAmount = issueDetail.getDiscountAmount();
                    couponId = issueDetail.getUnifiedCouponId();
                }
            }
            //下单前查询优惠
            deskCoupon = loopCheck.getPlatformCouponCipher(couponId,getHuiPromodeskCaseId);
            Assert.assertTrue(deskCoupon != null,"获取平台券失败，可能原因：调用平台券接口超时或者查券失败");
        }
        //3.加载优惠台，原价买单+平台券，可以无需加载优惠台
        //CouponProduct couponProduct = loopCheck.loadUnifiedCashier(loadUnifiedCashier);
        //log.info("折扣couponOfferId:{}" + JSON.toJSONString(couponProduct));
        //4.创建订单
        OrderModel orderModel = loopCheck.uniCashierCreateOrder(caseId,null,deskCoupon,0);
        log.info("创单成功！{}:",JSON.toJSONString(orderModel));
        //5.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //6.平台下单校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,下单成功);
        //7.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //8.支付mock
        maitonApi.orderPay(orderModel);
        //payMockUtil.mockPay(orderModel,mappingOrderIds);
        //9.支付后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,支付成功);
        //10.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //11.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //12.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //13.商户订单详情页校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
        //14.商户订单中心推送校验
        //15.商家直退
        DirectRefundResponse directRefundResponse = thriftApi.superRefund("qa-autocase", orderModel);
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //16.退款mock
        //payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //17.退款后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,退款成功);
        //18.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //19.退款后商户订单中心校验
        CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }

}
