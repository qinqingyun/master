package com.meituan.qa.meishi.Hui.cases.scene;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.util.CheckOrderUtil;
import com.meituan.qa.meishi.Hui.util.PayMockUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.MTApp;
import static com.meituan.qa.meishi.Hui.entity.OrderStatusEnum.*;

@Slf4j
public class MTAppTest extends TestBase {
    private String platformPath="/platformPath";
    private PayMockUtil payMockUtil= new PayMockUtil();

    @Test(groups = "P1",description = "美团app，买单使用原价买单方案->方案选取->下单->支付->商家直退->退款成功")
    public void mtOriginTest() throws Exception {
        String creatOrderCaseId = "ms_c_4Verify_mtloadUnifiedCashier_05";
        String platformCaseId = "ms_c_originalScenes_platform";
        String payResultCaseId = "ms_c_huiFullProcess_101_queryMopayStatus";
        String orderDetailCaseId = "ms_c_huiFullProcess_101_huiMaitonOrderMT";
        //1.创建订单
        OrderModel orderModel = loopCheck.uniCashierCreateOrder(creatOrderCaseId, MTApp);
        log.info("创单成功！{}:",JSON.toJSONString(orderModel));
        //2.新老订单映射
        MappingOrderIds mappingOrderIds = CheckOrderUtil.checkOrderMapping(orderModel);
        //3.平台下单校验
        CheckOrderUtil.checkNewPlarform(platformPath,platformCaseId,mappingOrderIds,下单成功);
        //4.买单侧下单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,下单成功);
        //5.支付mock
        maitonApi.orderPay(orderModel,MTApp);
        //payMockUtil.mockPay(orderModel,mappingOrderIds);
        //6.支付后平台校验
        CheckOrderUtil.checkNewPlarform(platformPath,platformCaseId,mappingOrderIds,支付成功);
        //7.支付后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,支付成功);
        //8.支付结果页
        CheckOrderUtil.checkPayOrderResultPage(payResultCaseId,MTApp,orderModel);
        //9.用户订单详情页
        CheckOrderUtil.checkOrderDetail(orderDetailCaseId,MTApp,orderModel);
        //10.商户订单详情页
        //11.商户订单中心推送
        //12.商家直退
        DirectRefundResponse directRefundResponse = thriftApi.superRefund("qa-autocase", orderModel);
        log.info("获取退款结果:{}", JSON.toJSONString(directRefundResponse));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(directRefundResponse));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
        //14.退款mock
        //payMockUtil.mockRefund(orderModel,mappingOrderIds);
        //15.退款后平台校验
        CheckOrderUtil.checkNewPlarform(platformPath,platformCaseId,mappingOrderIds,退款成功);
        //16.退款后买单校验
        CheckOrderUtil.checkOldOrderSystem(mappingOrderIds,退款成功);
    }

}
