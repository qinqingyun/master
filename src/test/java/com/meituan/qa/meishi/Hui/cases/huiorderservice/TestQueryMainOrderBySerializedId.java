package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
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
import static com.meituan.qa.meishi.Hui.cases.base.TestBase.huiOrderLoopCheck;
import static com.meituan.qa.meishi.Hui.cases.base.TestBase.thriftApi;

/**
 * 商家后台根据订单序列号查询订单信息,
 * 接口文档：https://km.sankuai.com/page/372472969
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "pigeon",des="根据订单序列号查询订单信息")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrderBySerializedId")
public class TestQueryMainOrderBySerializedId extends TestBase {
    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "buyuqi",createTime = "2020-07-07",des = "根据订单序列号查询订单信息")
    public void ms_c_queryMainOrderBySerializedId_01(JSONObject request, JSONObject expect){
        String serializedId = request.getString("serializedId");
        log.info("入参：{}",serializedId);
        QueryOrderResponse queryOrderResponse = huiOrderLoopCheck.queryMainOrderBySerializedId(serializedId);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderResponse));
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderResponse swimlaneQueryOrderResponse = huiOrderLoopCheck.queryMainOrderBySerializedId(serializedId);
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderResponse));
        Tracer.setSwimlane("");
//        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderResponse),JSON.toJSONString(queryOrderResponse));
//        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
//        Assert.assertEquals(orderDiff.getDiffStatusEnum(),"SUCCEED","数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "buyuqi",createTime = "2020-07-07",des = "serializedId：不传入")
    public void ms_c_queryMainOrderBySerializedId_02(JSONObject request, JSONObject expect){
        String serializedId = request.getString("serializedId");
        log.info("入参：{}",serializedId);
        QueryOrderResponse queryOrderResponse = huiOrderLoopCheck.queryMainOrderBySerializedId(serializedId);
        log.info("结果返回：{}",JSON.toJSONString(queryOrderResponse));
        Assert.assertTrue(queryOrderResponse == null);
        Tracer.setSwimlane("buyuqi-rjgoi");
        QueryOrderResponse swimlaneQueryOrderResponse = huiOrderLoopCheck.queryMainOrderBySerializedId(serializedId);
        log.info("结果返回：{}",JSON.toJSONString(swimlaneQueryOrderResponse));
        Tracer.setSwimlane("");
        DiffResponse orderDiff = thriftApi.getOrderDiff(JSON.toJSONString(swimlaneQueryOrderResponse),JSON.toJSONString(queryOrderResponse));
        log.info("数据diff结果:{}",JSON.toJSONString(orderDiff));
        Assert.assertEquals(orderDiff.getDiffStatusEnum(),SUCCEED,"数据diff未成功"+JSON.toJSONString(orderDiff.getDiffResultItemDTOList()));
    }
}
