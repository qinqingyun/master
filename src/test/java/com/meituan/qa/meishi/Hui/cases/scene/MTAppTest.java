package com.meituan.qa.meishi.Hui.cases.scene;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.MTApp;

@Slf4j
public class MTAppTest extends TestBase {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();

    @Test(groups = "P1",description = "美团app，买单使用原价买单方案->方案选取->下单->支付->用户申请->商家同意->退款")
    public void mtOriginTest() throws Exception {
        String creatOrderCaseId = "ms_c_4Verify_mtloadUnifiedCashier_05";
        String platformCaseId = "ms_c_originalScenes_platform";
        List<String> orderCreateResult = new ArrayList();
        //1.创建订单
        orderCreateResult = loopCheck.uniCashierCreateOrder(creatOrderCaseId,MTApp);
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);
        //2.新老订单映射
        MappingOrderIds mappingOrderIds=loopCheck.getMappingOrderIds(orderId);
        //3.平台下单校验
        JSONObject createOrderRequest = DBDataProvider.getRequest(platformPath, platformCaseId);
        JSONObject verifyRequest= createOrderRequest.getJSONObject("params");
        loopCheck.getPlatformStatus(1,mappingOrderIds.getNewOrderId(),verifyRequest,null);
        //买单侧下单校验
        QueryOrderResponse maitonQueryOrderResponse=loopCheck.getMaitonOrder(1,mappingOrderIds.getNewOrderId());
        orderCheck.maitonOrder(1,maitonQueryOrderResponse);
    }

}
