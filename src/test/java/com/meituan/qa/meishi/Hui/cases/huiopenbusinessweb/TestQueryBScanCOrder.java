package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.order.request.EcomQueryBScanCOrderReq;
import com.dianping.hui.openbusiness.order.response.EcomQueryBScanCOrderResp;
import com.dianping.hui.openbusiness.order.service.EcomBScanCService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/EcomBScanCService.queryBScanCOrder",
        type = "pigeon",des="B扫C，B端支付结果页")
@Slf4j
public class TestQueryBScanCOrder {
    private String _APIPATH = "/EcomBScanCService.queryBScanCOrder";

	@ThriftAPI(appkey = "com.dianping.hui.openbusiness.order.service.EcomBScanCService")
    private EcomBScanCService ecomBScanCService;

    EcomQueryBScanCOrderReq  req = new EcomQueryBScanCOrderReq();
    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-10",updateTime = "2018-10-10",des = "5分钟内查询未支付订单")
    public void ms_c_queryBScanCOrder_01(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("B215D7D302648C9F2F9A904D6EF6200E");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("支付中".equals(response.getDesc()));
        Assert.assertTrue(response.getPoll() == 1);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-10",updateTime = "2018-10-10",des = "查询美团支付成功订单")
    public void ms_c_queryBScanCOrder_02(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("A51F95E11639E3B698EF4F1ED4E56641");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("支付成功".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 1);
        Assert.assertTrue(!response.getSerialNumber().isEmpty() && !"0".equals(response.getSerialNumber()));
        Assert.assertTrue(response.getPayAmount().compareTo(new BigDecimal(0)) > 0);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "查询点评支付成功订单")
    public void ms_c_queryBScanCOrder_03(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("86D603F29E3BBD28BB3D7A7DED7C74F9");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("支付成功".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 1);
        Assert.assertTrue(response.getPayAmount().compareTo(new BigDecimal(0)) > 0);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "查询已退款订单")
    public void ms_c_queryBScanCOrder_04(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("F03E6B7332AF198EB19A7FBD06DE62B3");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("已退款".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 3);
        Assert.assertTrue(response.getPayAmount().compareTo(new BigDecimal(0)) > 0);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "authString未创单")
    public void ms_c_queryBScanCOrder_05(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("D65D06C31AFD48050A09FF976BE98E3D");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("未查到相应订单".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == -1);
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "未支付订单，下单超过5分钟")
    public void ms_c_queryBScanCOrder_06(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("2760954AEEAA16ECA52EF6B734B2ED19");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("支付中".equals(response.getDesc()));
        Assert.assertTrue(response.getPoll() == 0);
    }
    //以下未验证
	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "订单支付失败")
    public void ms_c_queryBScanCOrder_07(JSONObject request, JSONObject expect){
        EcomQueryBScanCOrderResp response = callService("F03E6B7332AF198EB19A7FBD06DE62B3");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("支付失败".equals(response.getDesc()));
        Assert.assertTrue(response.getPoll() == 0);
    }

    private EcomQueryBScanCOrderResp callService(String authString) {
        req.setAuthString(authString);
        return ecomBScanCService.queryBScanCOrder(req);
    }
}
