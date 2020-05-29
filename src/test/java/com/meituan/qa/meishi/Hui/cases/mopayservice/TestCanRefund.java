package com.meituan.qa.meishi.Hui.cases.mopayservice;

import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.service.RefundCheckService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;


@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/RefundCheckService.canRefund")
public class TestCanRefund {

    @PigeonAPI(url = "http://service.dianping.com/hui/huiBusinessService/refundOrderBaseService_1.0.0")
    private RefundCheckService refundCheckService;

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单可正常退款")
    public void ms_c_canRefund_01(JSONObject request, JSONObject expect){
        boolean result = callService(request);
        log.info("result：" + result);
        Assert.assertTrue(result);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单商家流水不足")
    public void ms_c_canRefund_02(JSONObject request, JSONObject expect){
        boolean result = callService(request);
        Assert.assertTrue(!result);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到综订单可正常退款")
    public void ms_c_canRefund_03(JSONObject request, JSONObject expect){
        boolean result = callService(request);
        Assert.assertTrue(result);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到综订单商家流水不足")
    public void ms_c_canRefund_04(JSONObject request, JSONObject expect){
        boolean result = callService(request);
        Assert.assertTrue(!result);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "orderid错误")
    public void ms_c_canRefund_05(JSONObject request, JSONObject expect){
        boolean result = callService(request);
        Assert.assertTrue(!result);
    }

    private Boolean callService(JSONObject request){
        long orderId = request.getLong("orderId");
        return  refundCheckService.canRefund(orderId);
    }
}
