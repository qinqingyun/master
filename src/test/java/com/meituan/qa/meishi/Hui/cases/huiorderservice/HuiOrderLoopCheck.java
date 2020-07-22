package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.dianping.hui.order.response.QueryOrderPageResponse;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderBaseRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderByReceiptRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderLimitRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderPageRequest;
import com.dianping.hui.order.shard.response.QueryOrderStatisticsResponse;
import com.meituan.toolchain.mario.annotation.LoopCheck;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.huiOrderApi;

public class HuiOrderLoopCheck {
    @LoopCheck(desc = "商家后台单个订单查询轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共10s
    public QueryOrdersResponse queryMainOrderByReceipt(QueryMainOrderByReceiptRequest receiptRequest){
        QueryOrdersResponse queryOrdersResponse = huiOrderApi.queryMainOrderByReceipt(receiptRequest);
        if(queryOrdersResponse.equals(null)){
            return null;
        }
        return queryOrdersResponse;
    }
    @LoopCheck(desc = "商家后台根据订单序列号查询订单信息轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共5s
    public QueryOrderResponse queryMainOrderBySerializedId(String serializedId){
        QueryOrderResponse queryOrderResponse = huiOrderApi.queryMainOrderBySerializedId(serializedId);
        if(queryOrderResponse.equals(null)){
            return null;
        }
        return queryOrderResponse;
    }
    @LoopCheck(desc = "商家后台分页查询信息轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共5s
    public QueryOrderPageResponse queryMainOrder(QueryMainOrderPageRequest queryMainOrderPageRequest){
        QueryOrderPageResponse queryOrderPageResponse = huiOrderApi.queryMainOrder(queryMainOrderPageRequest);
        if(queryOrderPageResponse.equals(null)){
            return null;
        }
        return queryOrderPageResponse;
    }
    @LoopCheck(desc = "商家后台全量数据查询查询轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共10s
    public QueryOrdersResponse queryMainOrderLimitRecord(QueryMainOrderLimitRequest queryMainOrderLimitRequest){
        QueryOrdersResponse queryOrdersResponse = huiOrderApi.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        if(queryOrdersResponse.equals(null)){
            return null;
        }
        return queryOrdersResponse;
    }
    @LoopCheck(desc = "商家后台聚合查询轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共10s
    public QueryOrderStatisticsResponse queryMainOrderStatistics(QueryMainOrderBaseRequest queryMainOrderBaseRequest){
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderApi.queryMainOrderStatistics(queryMainOrderBaseRequest);
        if(queryOrderStatisticsResponse.equals(null)){
            return null;
        }
        return queryOrderStatisticsResponse;
    }
    @LoopCheck(desc = "商家后台聚合查询轮询", interval = 500, timeout = 500 * 10) // 每间隔500ms请求一次，共10s
    public QueryOrderStatisticsResponse queryMainOrderSummary(QueryMainOrderBaseRequest queryMainOrderBaseRequest){
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = huiOrderApi.QueryMainOrderBaseRequest(queryMainOrderBaseRequest);
        if(queryOrderStatisticsResponse.equals(null)){
            return null;
        }
        return queryOrderStatisticsResponse;
    }
}
