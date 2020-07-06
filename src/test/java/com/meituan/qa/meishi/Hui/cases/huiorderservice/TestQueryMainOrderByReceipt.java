package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.message.Trace;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateReq;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateResp;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderByReceiptRequest;
import com.dianping.hui.order.shard.service.QueryMainOrder4MTService;
import com.meituan.mtrace.Tracer;
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
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "thrift",des="商家后台单个订单查询")
@Slf4j
@PigeonAPI(methodName = "/QueryMainOrder4MTService/queryMainOrderByReceipt")
public class TestQueryMainOrderByReceipt {
    @PigeonAPI(url = "http://service.dianping.com/huiOrderService/QueryMainOrder4MTService_1.0.0")
    QueryMainOrder4MTService queryMainOrder4MTService;
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200702", des = "")
    public void ms_c_queryMainOrderByReceipt_01(JSONObject request, JSONObject expect) throws Exception {
        QueryMainOrderByReceiptRequest receiptRequest  = JSON.parseObject(request.toString(), QueryMainOrderByReceiptRequest.class);
        QueryOrdersResponse queryOrdersResponse = queryMainOrder4MTService.queryMainOrderByReceipt(receiptRequest);
        //Tracer.putContext("SWIMLANE","3792-izitw");
    }
}
