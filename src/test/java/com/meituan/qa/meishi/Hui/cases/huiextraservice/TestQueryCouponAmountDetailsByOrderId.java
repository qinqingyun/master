package com.meituan.qa.meishi.Hui.cases.huiextraservice;


import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.extra.service.slave.SlaveHuiCouponService;
import com.dianping.hui.order.dto.OrderCouponAmountDetailDTO;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/SlaveHuiCouponService/queryCouponAmountDetailsByOrderId")
public class TestQueryCouponAmountDetailsByOrderId {
    @PigeonAPI(url = "com.dianping.hui.extra.service.slave.SlaveHuiCouponService")
    private SlaveHuiCouponService slaveHuiCouponService;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单可正常退款")
    public void ms_c_queryCouponAmountDetailsByOrderId_01(JSONObject request, JSONObject expect){
        List<OrderCouponAmountDetailDTO> result = slaveHuiCouponService.queryCouponAmountDetailsByOrderId(request.getLong("orderId"));
        log.info("result：" + result);

    }


}
