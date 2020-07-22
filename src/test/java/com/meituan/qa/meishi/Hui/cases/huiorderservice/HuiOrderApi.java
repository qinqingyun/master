package com.meituan.qa.meishi.Hui.cases.huiorderservice;

import com.dianping.hui.order.response.QueryOrderPageResponse;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.response.QueryOrdersResponse;
import com.dianping.hui.order.shard.request.QueryMainOrderBaseRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderByReceiptRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderLimitRequest;
import com.dianping.hui.order.shard.request.QueryMainOrderPageRequest;
import com.dianping.hui.order.shard.response.QueryOrderStatisticsResponse;
import com.dianping.hui.order.shard.service.QueryMainOrder4MTService;
import com.meituan.toolchain.mario.annotation.PigeonAPI;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class HuiOrderApi {
    @PigeonAPI(url = "http://service.dianping.com/huiOrderService/QueryMainOrder4MTService_1.0.0")
    private QueryMainOrder4MTService queryMainOrder4MTService;
    /**
     * 商家后台单个订单查询,
     * 接口文档：https://km.sankuai.com/page/372512934
     */
    public QueryOrdersResponse queryMainOrderByReceipt(QueryMainOrderByReceiptRequest receiptRequest){
        QueryOrdersResponse queryOrdersResponse = queryMainOrder4MTService.queryMainOrderByReceipt(receiptRequest);
        return queryOrdersResponse;
    }
    /**
     * 商家后台根据订单序列号查询订单信息,
     * 接口文档：https://km.sankuai.com/page/372472969
     * @return
     */
    public QueryOrderResponse queryMainOrderBySerializedId(String serializedId){
        QueryOrderResponse queryOrderResponse = queryMainOrder4MTService.queryMainOrderBySerializedId(serializedId);
        return queryOrderResponse;
    }
    /**
     * 分页查询,
     * 接口文档：https://km.sankuai.com/page/372550757
     * @return
     */
    public QueryOrderPageResponse queryMainOrder(QueryMainOrderPageRequest queryMainOrderPageRequest){
        QueryOrderPageResponse queryOrderPageResponse = queryMainOrder4MTService.queryMainOrder(queryMainOrderPageRequest);
        return queryOrderPageResponse;
    }
    /**
     * 商家后台全量数据查询,
     * 接口文档：https://km.sankuai.com/page/373499991
     * @return
     */
    public QueryOrdersResponse queryMainOrderLimitRecord(QueryMainOrderLimitRequest queryMainOrderLimitRequest){
        QueryOrdersResponse queryOrdersResponse = queryMainOrder4MTService.queryMainOrderLimitRecord(queryMainOrderLimitRequest);
        return queryOrdersResponse;
    }
    /**
     * 商家后台数据聚合查询,
     * 接口文档：https://km.sankuai.com/page/373532404
     * @return
     */
    public QueryOrderStatisticsResponse queryMainOrderStatistics(QueryMainOrderBaseRequest queryMainOrderBaseRequest){
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = queryMainOrder4MTService.queryMainOrderStatistics(queryMainOrderBaseRequest);
        return queryOrderStatisticsResponse;
    }
    /**
     * 商家后台数据聚合查询,
     * 接口文档：https://km.sankuai.com/page/373532404
     * @return
     */
    public QueryOrderStatisticsResponse QueryMainOrderBaseRequest(QueryMainOrderBaseRequest queryMainOrderBaseRequest){
        QueryOrderStatisticsResponse queryOrderStatisticsResponse = queryMainOrder4MTService.queryMainOrderSummary(queryMainOrderBaseRequest);
        return queryOrderStatisticsResponse;
    }
    /**获取当前时间所在的0点时段
     * @return
     */
    public Date getBeginTimeDate(){
        long current = System.currentTimeMillis();
        long zeroTime = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset() - 86400000;
        Date date = new Date();
        date.setTime(zeroTime);
        return date;
    }
    public Date getEndTimeDate(){
        long current = System.currentTimeMillis();
        long endTime = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset() + 604800000;
        Date date = new Date();
        date.setTime(endTime);
        return date;
    }
}
