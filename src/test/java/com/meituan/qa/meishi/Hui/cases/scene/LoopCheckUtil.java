package com.meituan.qa.meishi.Hui.cases.scene;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.PlatformCheckInfo;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.sankuai.nibqa.trade.api.dto.ValidResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class LoopCheckUtil extends TestBase {
    @LoopCheck(desc = "原价买单创建订单,无需加载优惠台", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public OrderModel uniCashierCreateOrder(String caseId, OrderSourceEnum sourceEnum)  {
        OrderModel orderCreateResult = maitonApi.uniCashierCreateOrder(caseId,sourceEnum);
        return orderCreateResult;
    }
    @LoopCheck(desc = "查询新老订单ID映射轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    public MappingOrderIds getMappingOrderIds(String orderId) throws Exception {
        MappingOrderIds mappingOrderIds = thriftApi.getMappingOrderIds(orderId);
        return mappingOrderIds;
    }
    @LoopCheck(desc = "下单后平台校验轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    public ValidResponse getPlatformStatus(int  flag, String  orderId, JSONObject verifyRequest, String buyer)  {
        PlatformCheckInfo platformCheck = PlatformCheckInfo.builder()
                .flag(flag)
                .orderid(Long.valueOf(orderId))
                .verifyRequest(verifyRequest)
                .buyer(buyer)
                .build();
        ValidResponse validResponse = platformCheck.PlatformCheckInfo();
        if(!validResponse.isSuccess()){
            log.error("下单后平台校验轮询失败:{}", validResponse.getMsg());
            return null;
        }
        return validResponse;
    }
    @LoopCheck(desc = "查询买单侧下单轮询", interval = 500, timeout = 500 * 30) // 每间隔500ms请求一次，共10s
    public QueryOrderResponse getMaitonOrder( int flag,String orderId) throws Exception {
        QueryOrderResponse queryOrderResponse = thriftApi.getMaidonOrder(orderId);
        if (flag==1){
            if (queryOrderResponse.getOrderDTO().getOrderStatus().intValue()!=0){
                return null;
            }
        }
        if (flag==2){
            if (queryOrderResponse.getOrderDTO().getOrderStatus().intValue()!=10){
                return null;
            }
        }
        if (flag==3){
            List<Integer> refundset = Lists.newArrayList(30,50);
            if (!refundset.contains(queryOrderResponse.getOrderDTO().getOrderStatus().intValue())){
                return null;
            }
        }
        return queryOrderResponse;
    }
    @LoopCheck(desc = "支付结果页轮询", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public String getPayResultPage(String caseId, OrderSourceEnum sourceEnum,String serializedId)  {
        String queryMopayStatus = maitonApi.queryMopayStatus(caseId, sourceEnum, serializedId);
        return queryMopayStatus;
    }
    @LoopCheck(desc = "订单详情页轮询", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public String getOrderDetail(String caseId,OrderSourceEnum sourceEnum,String orderId)  {
        String orderDetail = maitonApi.MtOrderDetail(caseId, sourceEnum, orderId);
        return orderDetail;
    }
}
