package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderByReceiptRequest;
import com.meituan.mtrace.Tracer;
import com.meituan.nibscp.unity.validation.api.response.DiffResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 商家后台单个订单查询,
 * 接口文档：https://km.sankuai.com/page/372512934
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "pigeon",des="商家后台单个订单查询")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrderByReceipt")
public class TestQueryMainOrderByReceipt extends TestBase {
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "查询某一订单信息")
    public void ms_c_queryMainOrderByReceipt_01(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(queryOrdersResponse), JSON.toJSONString(swimlineQueryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),"SUCCEED","数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "mtShopId不传入")
    public void ms_c_queryMainOrderByReceipt_02(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(queryOrdersResponse), JSON.toJSONString(swimlineQueryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),"SUCCEED","数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "shopid与800订单不匹配")
    public void ms_c_queryMainOrderByReceipt_03(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Assert.assertEquals(queryOrdersResponse.getOrderDTOs().size(),0,"shopid与800订单不匹配,无法查询订单信息");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        Assert.assertEquals(swimlineQueryOrdersResponse.getOrderDTOs().size(),0,"泳道配置，shopid与800订单不匹配,无法查询订单信息");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "参数serialNumber为非必填参数，不传入")
    public void ms_c_queryMainOrderByReceipt_04(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Assert.assertEquals(queryOrdersResponse.getOrderDTOs().size(),0,"参数serialNumber不传,查询订单信息为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        Assert.assertEquals(swimlineQueryOrdersResponse.getOrderDTOs().size(),0,"泳道配置，参数serialNumber不传,查询订单信息为空");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "参数serialNumber传0")
    public void ms_c_queryMainOrderByReceipt_05(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Assert.assertEquals(queryOrdersResponse.getOrderDTOs().size(),0,"参数serialNumber传0,查询订单信息为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        Assert.assertEquals(swimlineQueryOrdersResponse.getOrderDTOs().size(),0,"泳道配置，参数serialNumber传0,查询订单信息为空");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "参数serialNumber传48长订单")
    public void ms_c_queryMainOrderByReceipt_06(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Assert.assertEquals(queryOrdersResponse.getOrderDTOs().size(),0,"参数serialNumber传48长订单,查询订单信息为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        Assert.assertEquals(swimlineQueryOrdersResponse.getOrderDTOs().size(),0,"泳道配置，参数serialNumber传48长订单,查询订单信息为空");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "beginTime，endTime：非必传不传入值")
    public void ms_c_queryMainOrderByReceipt_07(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "beginTime：晚于endTime")
    public void ms_c_queryMainOrderByReceipt_08(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        log.info("入参：{}",JSON.toJSONString(receiptRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Assert.assertEquals(queryOrdersResponse.getOrderDTOs().size(),0,"beginTime：晚于endTime,查询订单信息为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlineQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderByReceipt(receiptRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlineQueryOrdersResponse));
        Tracer.setSwimlane("");
        Assert.assertEquals(swimlineQueryOrdersResponse.getOrderDTOs().size(),0,"泳道配置，beginTime：晚于endTime,查询订单信息为空");
    }

}
