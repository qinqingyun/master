package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.open.entity.OrderSimpleDTO;
import com.dianping.hui.open.service.HuiOpenQueryOrderService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/HuiOpenQueryOrderService/queryOrderSimpleBySerializedId")
public class TestQueryOrderSimpleBySerializedId {

    @PigeonAPI(url = "http://service.dianping.com/hui/openService/huiOpenQueryOrderService_1.0.0")
    private HuiOpenQueryOrderService huiOpenQueryOrderService;


    @Test(groups = {"P1"}, dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang", createTime = "2018-05-10", updateTime = "2018-05-10", des = "到餐订单可正常退款")
    public void ms_c_queryOrderSimpleBySerializedId_01(JSONObject request, JSONObject expect) {
        OrderSimpleDTO result = huiOpenQueryOrderService.queryOrderSimpleBySerializedId(request.getString("serializedId"));
        log.info("result：" + result);

    }
}