package com.meituan.qa.meishi.Hui.cases.huimerchantservice;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.scheme.service.QueryMsSchemeService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/QueryMsSchemeService/getSchemeInvoiceStatus")
public class TestGetSchemeInvoiceStatus {
    @PigeonAPI(url = "com.dianping.hui.scheme.service.QueryMsSchemeService")
    private QueryMsSchemeService queryMsSchemeService;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单可正常退款")
    public void ms_c__getSchemeInvoiceStatus_01(JSONObject request, JSONObject expect){
        boolean result = queryMsSchemeService.getSchemeInvoiceStatus(request.getLong("couponOfferId"));
        log.info("result：" + result);

    }

}
