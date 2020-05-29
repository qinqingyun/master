package com.meituan.qa.meishi.Hui.cases.mopayservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.request.OrderInvoiceInfoRequest;
import com.dianping.mopayprocess.response.OrderInvoiceInfoResponse;
import com.dianping.mopayprocess.service.OrderInvoiceService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/OrderInvoiceService.getInvoiceInfo",
        type = "pigeon",des="获取订单详情页极速开票信息")
@Slf4j
@ThriftAPI(methodName = "/OrderInvoiceService.getInvoiceInfo")
public class TestGetInvoiceInfo {
    private String _APIPATH = "/OrderInvoiceService.getInvoiceInfo";

	@ThriftAPI(appkey = "com.dianping.mopayprocess.service.OrderInvoiceService")
    private OrderInvoiceService orderInvoiceService;

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "订单24小时内未开发票")
    public void ms_c_getInvoiceInfo_01(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        System.out.println(response.getOrderInvoiceDto().getJumpUrl());
        Assert.assertTrue(response.getOrderInvoiceDto().getStatus() == 0);
        Assert.assertTrue(response.getOrderInvoiceDto().getJumpUrl() != null);
        Assert.assertTrue(response.getOrderInvoiceDto().getInvoiceDesc().equals("请在买单后24小时内提交申请"));

    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "订单24小时内已开发票")
    public void ms_c_getInvoiceInfo_02(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        Assert.assertTrue(response.getOrderInvoiceDto().getStatus() == 1);
        Assert.assertTrue(response.getOrderInvoiceDto().getJumpUrl() == null);
        Assert.assertTrue(!response.getOrderInvoiceDto().getInvoiceDesc().equals("请联系商家领取纸质发票"));

    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "订单超过24小时未开发票")
    public void ms_c_getInvoiceInfo_03(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        log.info(response.getOrderInvoiceDto().getStatus() + "|"+response.getOrderInvoiceDto().getInvoiceDesc());
        Assert.assertTrue(response.getOrderInvoiceDto().getStatus() == 2);
        Assert.assertTrue(response.getOrderInvoiceDto().getJumpUrl() == null);
        Assert.assertTrue(response.getOrderInvoiceDto().getInvoiceDesc().equals("请与商家协商处理"));

    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "订单不支持开发票")
    public void ms_c_getInvoiceInfo_04(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        Assert.assertTrue(response.getOrderInvoiceDto() == null);
        Assert.assertTrue(response.getCode() == 1);
        Assert.assertTrue(response.getMsg().equals("查询发票信息成功"));

    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "orderId错误")
    public void ms_c_getInvoiceInfo_05(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getOrderInvoiceDto() == null);
        Assert.assertTrue(response.getCode() == 2);
        Assert.assertTrue(response.getMsg().equals("查询优惠ID错误"));
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "orderId缺失")
    public void ms_c_getInvoiceInfo_06(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        Assert.assertTrue(response.getOrderInvoiceDto() == null);
        Assert.assertTrue(response.getCode() == 4);
        Assert.assertTrue(response.getMsg().equals("参数错误"));
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "shopid缺失")
    public void ms_c_getInvoiceInfo_07(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        Assert.assertTrue(response.getOrderInvoiceDto() == null);
        Assert.assertTrue(response.getCode() == 4);
        Assert.assertTrue(response.getMsg().equals("参数错误"));
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "payAmount字段缺不用校验（与rd沟通确认后）")
    public void ms_c_getInvoiceInfo_08(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getOrderInvoiceDto().getStatus() == 0);
        Assert.assertTrue(response.getOrderInvoiceDto().getJumpUrl() != null);
        Assert.assertTrue(response.getOrderInvoiceDto().getInvoiceDesc().equals("请在买单后24小时内提交申请"));
    }

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-03",updateTime = "2018-04-03",des = "payTime字段缺失")
    public void ms_c_getInvoiceInfo_09(JSONObject request, JSONObject expect){
        OrderInvoiceInfoResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getOrderInvoiceDto() == null);
        Assert.assertTrue(response.getCode() == 4);
        Assert.assertTrue(response.getMsg().equals("参数错误"));
    }


    private OrderInvoiceInfoResponse callService(JSONObject request){

		OrderInvoiceInfoRequest orderInvoiceInfoRequest = JSON.parseObject(request.getJSONObject("orderInvoiceInfoRequest").toString(),OrderInvoiceInfoRequest.class);
        return orderInvoiceService.getInvoiceInfo(orderInvoiceInfoRequest);
    }
}
