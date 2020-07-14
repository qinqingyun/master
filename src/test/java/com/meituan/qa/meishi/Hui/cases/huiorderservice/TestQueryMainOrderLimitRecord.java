package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderPageResponse;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderLimitRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderPageRequest;
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

import java.util.Date;

import static com.meituan.nibscp.unity.validation.api.enums.DiffStatusEnum.SUCCEED;

/**
 * 商家后台全量数据查询,
 * 接口文档：https://km.sankuai.com/page/373499991
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "pigeon",des="全量数据查询")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrderLimitRecord")
public class TestQueryMainOrderLimitRecord extends TestBase {
    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "buyuqi",createTime = "2020-07-08",des = "全量数据查询")
    public void ms_c_queryMainOrderLimitRecord_01(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setMaxSize(Integer.MAX_VALUE);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
//        Date date = new Date();
//        date.setTime(1594368000000l);
//        queryMainOrderLimitRequest.setBeginTime(date);
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),"SUCCEED","数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "buyuqi",createTime = "2020-07-08",des = "参数maxSize：传0")
    public void ms_c_queryMainOrderLimitRecord_02(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数mtShopIds，传空list，预期结果返回时间段内所有门店的orderId")
    public void ms_c_queryMainOrderLimitRecord_03(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数mtShopIds，传无买单订单的门店")
    public void ms_c_queryMainOrderLimitRecord_04(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数orderStatusList：传空list")
    public void ms_c_queryMainOrderLimitRecord_05(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数refundStatusList：传空list")
    public void ms_c_queryMainOrderLimitRecord_06(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数schemeIdList：传空list")
    public void ms_c_queryMainOrderLimitRecord_07(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数beginTime：传0，endTime传0")
    public void ms_c_queryMainOrderLimitRecord_08(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数beginTime：时间晚于endTime")
    public void ms_c_queryMainOrderLimitRecord_09(JSONObject request, JSONObject expect){
        QueryMainOrderLimitRequest queryMainOrderLimitRequest  = JSON.parseObject(request.toString(), QueryMainOrderLimitRequest.class);
        queryMainOrderLimitRequest.setBeginTime(huiOrderApi.getEndTimeDate());
        queryMainOrderLimitRequest.setEndTime(huiOrderApi.getBeginTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderLimitRequest));
        QueryOrdersResponse queryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrdersResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrdersResponse swimlanQueryOrdersResponse = huiOrderLoopCheck.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        log.info("结果返回：{}",JSON.toJSONString(swimlanQueryOrdersResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlanQueryOrdersResponse),JSON.toJSONString(queryOrdersResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
}
