package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderPageResponse;
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

@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "pigeon",des="商家后台分页查询信息")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrder")
public class TestQueryMainOrder extends TestBase {
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "分页查询")
    public void ms_c_queryMainOrder_01(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        //queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        Date date = new Date();
        date.setTime(1594296000000l);
        queryMainOrderPageRequest.setEndTime(date);
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数mtShopIds，传空list，预期结果返回时间段内所有门店的orderId")
    public void ms_c_queryMainOrder_02(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数mtShopIds，传无买单服务的门店，预期结果返回null")
    public void ms_c_queryMainOrder_03(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel().getRecords().size()==0,"poi没有开通买单服务，查询结果返回不为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数schemeIdList，传空list，预期结果返回时间段内所有合同的orderId")
    public void ms_c_queryMainOrder_04(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数beginTime：传0,endTime：传0")
    public void ms_c_queryMainOrder_05(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel().getRecords().size() == 0,"时间戳有问题，返回结果不为null");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数beginTime：时间晚于endTime")
    public void ms_c_queryMainOrder_06(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getEndTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getBeginTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel().getRecords().size()==0,"开始时间晚于结束时间，查询结果返回不为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数pageSize：传0")
    public void ms_c_queryMainOrder_07(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel().getRecords().size()==0,"参数pageSize：传0，查询结果records返回不为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200703", des = "参数pageSize：传极大值，查询结果正常返回")
    public void ms_c_queryMainOrder_08(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数pageNo：传0，返回结果为null")
    public void ms_c_queryMainOrder_09(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel() == null,"参数pageNo：传0，返回结果为null");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数pageNo：传大于最大分页值。数据最大2页，传3，records为空")
    public void ms_c_queryMainOrder_10(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Assert.assertTrue(queryOrderPageResponse.getPageModel().getRecords().size()==0,"参数pageNo：传大于最大分页值，查询结果records返回不为空");
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数orderStatusList：传空list")
    public void ms_c_queryMainOrder_11(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200708", des = "参数refundStatusList：传空lis")
    public void ms_c_queryMainOrder_12(JSONObject request, JSONObject expect){
        QueryMainOrderPageRequest queryMainOrderPageRequest = JSON.parseObject(request.toString(), QueryMainOrderPageRequest.class);
        queryMainOrderPageRequest.setBeginTime(huiOrderApi.getBeginTimeDate());
        queryMainOrderPageRequest.setEndTime(huiOrderApi.getEndTimeDate());
        log.info("入参：{}",JSON.toJSONString(queryMainOrderPageRequest));
        QueryOrderPageResponse queryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderPageResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderPageResponse swimlaneQueryOrderPageResponse = huiOrderLoopCheck.queryMainOrder(queryMainOrderPageRequest);
        Tracer.setSwimlane("");
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderPageResponse));
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderPageResponse),JSON.toJSONString(queryOrderPageResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
}
