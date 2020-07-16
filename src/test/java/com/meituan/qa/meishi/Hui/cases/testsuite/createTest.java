package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.util.CheckOrderUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.meituan.resv.trade.thrift.TResvBillService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.MTApp;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class createTest extends TestBase{
    @Test()
    public void testCreate() throws Exception {
        List<String> orderCreateResult = new ArrayList();
        OrderCheck orderCheck=new OrderCheck();
        String caseId = "ms_c_4Verify_mtloadUnifiedCashier_05";
        String platformPath="/platformPath";

        orderCreateResult = maitonApi.uniCashierCreateOrder(caseId,MTApp);
        String payToken = orderCreateResult.get(0);
        String tradeNo = orderCreateResult.get(1);
        String orderId = orderCreateResult.get(2);
        String serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        // 新老订单映射
        MappingOrderIds mappingOrderIds=loopCheck.getMappingOrderIds(orderId);
        String neworderid= mappingOrderIds.getNewOrderId();
        String oldorderid= mappingOrderIds.getOldOrderId();

        // 平台下单校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        loopCheck.getPlatformStatus(1,neworderid,verifyRequest,null);

        //买单侧下单校验
        QueryOrderResponse maitonQueryOrderResponse=loopCheck.getMaitonOrder(1,oldorderid);
        CheckOrderUtil.checkOldOrderSystem(1,maitonQueryOrderResponse);

        //2、支付
//        maitonApi.orderPay(payToken, tradeNo, MTApp);
//
//        //支付后平台校验
//        JSONObject payOrderRequest = DBDataProvider.getRequest(platformPath, "ms_c_originalScenes_platform");
//        JSONObject payVerifyRequest= payOrderRequest.getJSONObject("params");
//        checkLoop.getPlatformStatus(2,neworderid,payVerifyRequest,null);
//
//        //支付后买单侧校验
//        QueryOrderResponse QueryOrderResponse=checkLoop.getMaitonOrder(2,oldorderid);
//        CheckOrderUtil.checkOldOrderSystem(1,maitonQueryOrderResponse);
    }
}

