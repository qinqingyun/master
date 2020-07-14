package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderPageResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderBaseRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderPageRequest;
import com.dianping.hui.order.shard.response.QueryOrderStatisticsResponse;
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

import static com.meituan.nibscp.unity.validation.api.enums.DiffStatusEnum.SUCCEED;

@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "pigeon",des="商家后台聚合信息查询")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrderStatistics")
public class TestQueryMainOrderStatistics extends TestBase {
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "聚合查询")
    public void ms_c_queryMainOrderStatistics_01(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数mtShopIds，传空list，预期结果返回时间段内所有门店的orderId")
    public void ms_c_queryMainOrderStatistics_02(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数mtShopIds，传无买单服务的门店，预期结果返回null")
    public void ms_c_queryMainOrderStatistics_03(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数schemeIdList，传空list，预期结果返回时间段内所有合同的orderId")
    public void ms_c_queryMainOrderStatistics_04(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数beginTime：传0,endTime：传0")
    public void ms_c_queryMainOrderStatistics_05(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数beginTime：时间晚于endTime")
    public void ms_c_queryMainOrderStatistics_06(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getEndTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getBeginTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数orderStatusList：传空list")
    public void ms_c_queryMainOrderStatistics_07(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数refundStatusList：传空list")
    public void ms_c_queryMainOrderStatistics_08(JSONObject request, JSONObject expect){
        QueryMainOrderBaseRequest queryMainOrderBaseRequest = JSON.parseObject(request.toString(), QueryMainOrderBaseRequest.class);
        queryMainOrderBaseRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderBaseRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderBaseRequest));
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderStatisticsResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderStatisticsResponse swimlaneQueryOrderStatisticsResponse = huiOrderLoopCheck.queryMainOrderStatistics(queryMainOrderBaseRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderStatisticsResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderStatisticsResponse),JSON.toJSONString(queryOrderStatisticsResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
}
