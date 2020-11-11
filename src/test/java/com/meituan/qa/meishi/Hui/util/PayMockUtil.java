package com.meituan.qa.meishi.Hui.util;

import com.alibaba.fastjson.JSON;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.sankuai.nibqa.trade.payMock.api.PayNotifyMockService;
import com.sankuai.nibqa.trade.payMock.api.RefundNotifyMockService;
import com.sankuai.nibqa.trade.payMock.impl.PayNotifyMockServiceImpl;
import com.sankuai.nibqa.trade.payMock.impl.RefundNotifyMockServiceImpl;
import com.sankuai.nibqa.trade.payMock.params.enums.Scene;
import com.sankuai.nibqa.trade.payMock.params.request.PayNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.request.RefundNotifyMockRequest;
import com.sankuai.nibqa.trade.payMock.params.response.PayMockBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.List;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.MainSystem;
@Slf4j
public class PayMockUtil {
    PayNotifyMockRequest payNotifyMockRequest = new PayNotifyMockRequest();
    RefundNotifyMockRequest refundNotifyMockRequest = new RefundNotifyMockRequest();

    /**
    * mock支付
    */
    public static void mockPay(PayNotifyMockRequest request){
        PayNotifyMockService payNotifyMockService = new PayNotifyMockServiceImpl();
        request.setBizCode("nib.food.paybill");
        PayMockBaseResponse<String> response = payNotifyMockService.mockPayNotify(request);
        Assert.assertEquals(response.getCode(), 0, "支付mock失败，错误信息:" + response.getErrorMsg());
    }

    /**
     * mock退款
     */
    public static void mockRefund(RefundNotifyMockRequest request){
        RefundNotifyMockService refundNotifyMockService = new RefundNotifyMockServiceImpl();
        request.setBizCode("nib.food.paybill");
        PayMockBaseResponse<String> response = refundNotifyMockService.mockRefundNotify(request);
        Assert.assertEquals(response.getCode(), 0, "退款mock失败，错误信息:" + response.getErrorMsg());
    }
    public void setMockScene(){
        switch (MainSystem){
            case "OLD_ONLY":
                payNotifyMockRequest.setScene(Scene.OLD_ONLY);
                refundNotifyMockRequest.setScene(Scene.OLD_ONLY);
                break;
            case "OLD_MAIN":
                payNotifyMockRequest.setScene(Scene.OLD_MAIN);
                refundNotifyMockRequest.setScene(Scene.OLD_MAIN);
                break;
            case "NEW_MAIN":
                payNotifyMockRequest.setScene(Scene.NEW_MAIN);
                refundNotifyMockRequest.setScene(Scene.NEW_MAIN);
                break;

            case "NEW_ONLY":
                payNotifyMockRequest.setScene(Scene.NEW_ONLY);
                refundNotifyMockRequest.setScene(Scene.NEW_ONLY);
                break;
        }
    }
    /**
     * mock支付
     */
    public void mockPay(OrderModel orderModel, MappingOrderIds mappingOrderIds) throws Exception {
        setMockScene();
        PayNotifyMockService payNotifyMockService = new PayNotifyMockServiceImpl();
        payNotifyMockRequest.setBizCode("nib.food.paybill");
        payNotifyMockRequest.setTradeNo(orderModel.getTradeNo());
        payNotifyMockRequest.setOrderId(mappingOrderIds.getNewOrderId());
        payNotifyMockRequest.setAmount(Long.valueOf(orderModel.getPayAmount()));
        if(MainSystem.equals("OLD_MAIN")){
            payNotifyMockRequest.setOutNo("DPHUI-"+mappingOrderIds.getOldOrderId());
        }
        log.info("支付mock入参：{}", JSON.toJSONString(payNotifyMockRequest));
        PayMockBaseResponse<String> response = payNotifyMockService.mockPayNotify(payNotifyMockRequest);
        log.info("支付mock结果返回：{}", JSON.toJSONString(response));
        Assert.assertEquals(response.getCode(), 0, "支付mock失败，错误信息:" + response.getErrorMsg());
    }
    public void mockRefund(OrderModel orderModel, MappingOrderIds mappingOrderIds){
        RefundNotifyMockService refundNotifyMockService = new RefundNotifyMockServiceImpl();
        refundNotifyMockRequest.setBizCode("nib.food.paybill");
        refundNotifyMockRequest.setAmount(Long.valueOf(orderModel.getPayAmount()));
        refundNotifyMockRequest.setOrderId(mappingOrderIds.getNewOrderId());
        refundNotifyMockRequest.setTradeNo(orderModel.getTradeNo());
        if(MainSystem.equals("OLD_MAIN")){
            refundNotifyMockRequest.setRefundNo("DPHUI-"+mappingOrderIds.getOldOrderId());
        }
        PayMockBaseResponse<String> response = refundNotifyMockService.mockRefundNotify(refundNotifyMockRequest);
        Assert.assertEquals(response.getCode(), 0, "退款mock失败，错误信息:" + response.getErrorMsg());
    }

}