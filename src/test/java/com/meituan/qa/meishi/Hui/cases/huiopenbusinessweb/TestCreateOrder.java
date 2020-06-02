package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateReq;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateResp;
import com.dianping.hui.openbusiness.order.service.EcomOrderCreateService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * 大客下单api接口，文档：https://km.sankuai.com/page/335188001
 * @throws Exception
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "thrift",des="大客下单api接口")
@Slf4j
@PigeonAPI(methodName = "/EcomOrderCreateService/createOrder")
public class TestCreateOrder {
    @PigeonAPI(url = "com.dianping.hui.openbusiness.order.service.EcomOrderCreateService")
    EcomOrderCreateService ecomOrderCreateService;

    @Test(groups = {"P1"})
    @MethodAnotation(author = "byq", createTime = "20200529", des = "美团侧正常下单")
    public void ms_c_createOrder_01() {
        EcomOrderCreateReq ecomOrderCreateReq = new EcomOrderCreateReq();
        ecomOrderCreateReq.setShopId(65731456);
        ecomOrderCreateReq.setOriginAmount(BigDecimal.valueOf(1));
        ecomOrderCreateReq.setUserAmount(BigDecimal.valueOf(1));
        ecomOrderCreateReq.setShopType(1);
        ecomOrderCreateReq.setUserId(859799548);
        ecomOrderCreateReq.setUserType(1);
        ecomOrderCreateReq.setUserAgent("123");
        ecomOrderCreateReq.setCityId(1);
        ecomOrderCreateReq.setFingerPrint("123");
        ecomOrderCreateReq.setIp("127.0.0.1");
        ecomOrderCreateReq.setMobile("15528302855");
        EcomOrderCreateResp order = ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info(JSON.toJSONString(order));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200529", des = "点评侧正常下单")
    public void ms_c_createOrder_02(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp order = ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(order));
    }
}