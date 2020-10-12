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
import com.meituan.qa.meishi.Hui.util.PayMockUtil;
import com.meituan.qa.meishi.Hui.util.SetTraceUtil;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.MaitonHongbaoTResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPApp;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPM;
import static com.meituan.qa.meishi.Hui.entity.OrderStatusEnum.*;

@Slf4j
public class DianPingMTest extends TestBase {
    private String platformPath="/platformPath";
    /**
     * 用例简介:     买单使用原价买单方案，门店只有原价买单方案，M站和小程序下单单走老
     * 数据源:       shopId：65731456
     * 主要流程:     下单 -> 支付 -> 详情 -> 退款
     * 备注:        平台：点评M站 ；买单方案：原价买单；退款方式：直接退款
     **/
    @Test(groups = "P1",description = "点评M站，买单使用原价买单方案->方案选取->下单->支付->商家直退->退款成功")
    public void mOriginTest() throws Exception {
        SetTraceUtil setTraceUtil = new SetTraceUtil();
        String caseId = "mOriginTest";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        setTraceUtil.setTrace(); //mock相关配置
        //1.创建订单
        OrderModel orderModel = loopCheck.mCreateOrder(caseId);
        log.info("创单成功！{}:", JSON.toJSONString(orderModel));
        //2.新老订单映射
        MappingOrderIds mappingOrderIds = new MappingOrderIds();
        mappingOrderIds.setNewOrderId(orderModel.getOrderId());
        mappingOrderIds.setOldOrderId(orderModel.getOrderId());
        //3.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //5.支付mock
        maitonApi.orderPay(orderModel);
        //6.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //7.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //8.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //9.商户订单详情页校验
        //CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
        //11.商户订单中心推送校验
        //12.商家直退
        DirectRefundResponse directRefundResponse = thriftApi.superRefund("qa-autocase", orderModel);
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //16.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //17.退款后商户订单中心校验
        //CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }
    @Test(groups = "P1",description = "点评M站，买单使用折扣买单方案->方案选取->下单->支付->用户申请->商家同意->退款")
    public void mDiscountTest() throws Exception {
        PayMockUtil payMockUtil = new PayMockUtil();
        SetTraceUtil setTraceUtil = new SetTraceUtil();
        String caseId = "mDiscountTest";
        String loadUnifiedCashier = "ms_c__dp_loadUnifiedCashier";
        String platformCaseId = "ms_c_dpdiscount_platform_consum";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //0.登录获取基本userInfo
        maitonApi.replaceUserInfo(DPApp);
        setTraceUtil.setTrace(); //mock相关配置
        //1.加载优惠台
//        CouponProduct couponProduct = loopCheck.loadUnifiedCashier(loadUnifiedCashier);
//        log.info("折扣couponOfferId:{}" + JSON.toJSONString(couponProduct));
        //2.创建订单
        OrderModel orderModel = loopCheck.mCreateOrder(caseId);
        log.info("创单成功！{}:",JSON.toJSONString(orderModel));
        //3.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //4.平台下单校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,下单成功);
        //5.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //6.支付mock
        //maitonApi.orderPay(orderModel);
        payMockUtil.mockPay(orderModel,mappingOrderIds);
        //7.支付后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,支付成功);
        //8.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //9.支付结果页校验
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,orderModel);
        //10.用户订单详情页校验
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,orderModel,DPApp);
        //11.商户订单详情页校验
        //CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,支付成功);
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
        payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //15.退款后平台校验
        CheckOrderUtil.checkNewPlatform(platformPath,platformCaseId,mappingOrderIds,orderModel,退款成功);
        //16.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
        //17.退款后商户订单中心校验
        //CheckOrderUtil.checkMerchantOrderDetail(caseId,orderModel,退款成功);
    }
}
