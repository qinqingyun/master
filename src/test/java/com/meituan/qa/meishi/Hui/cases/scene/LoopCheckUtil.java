package com.meituan.qa.meishi.Hui.cases.scene;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.PlatformCheckInfo;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import static com.meituan.qa.meishi.Hui.entity.OrderStatusEnum.*;

import com.meituan.toolchain.mario.model.ResponseMap;
import com.sankuai.meituan.resv.i.thrift.exception.InternalTException;
import com.sankuai.meituan.resv.order.thrift.exception.ResvOrderException;
import com.sankuai.meituan.resv.trade.idl.exception.ResvTradeException;
import com.sankuai.nibqa.trade.api.dto.ValidResponse;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.MaitonHongbaoTResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.stringtemplate.v4.ST;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
public class LoopCheckUtil extends TestBase {
    @LoopCheck(desc = "原价买单创建订单,无需加载优惠台", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public OrderModel uniCashierCreateOrder(String caseId) throws UnsupportedEncodingException {
        OrderModel orderCreateResult = maitonApi.uniCashierCreateOrder(caseId);
        return orderCreateResult;
    }
    @LoopCheck(desc = "折扣买单创建订单,需加载优惠台", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public OrderModel uniCashierCreateOrder(String caseId, CouponProduct couponProduct) throws UnsupportedEncodingException {
        OrderModel orderCreateResult = maitonApi.uniCashierCreateOrder(caseId,couponProduct);
        return orderCreateResult;
    }
    @LoopCheck(desc = "使用优惠券买单创建订单", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public OrderModel uniCashierCreateOrder(String caseId, CouponProduct couponProduct, DeskCoupon deskcoupon) throws UnsupportedEncodingException {
        OrderModel orderCreateResult = maitonApi.uniCashierCreateOrder(caseId,couponProduct,deskcoupon);
        return orderCreateResult;
    }
    @LoopCheck(desc = "使用预定金买单创建订单", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public OrderModel uniCashierCreateOrder(String caseId, String resvOrderId) throws UnsupportedEncodingException {
        OrderModel orderCreateResult = maitonApi.uniCashierCreateOrder(caseId,resvOrderId);
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
    @LoopCheck(desc = "查询买单侧下单轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
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
    @LoopCheck(desc = "支付结果页轮询", interval = 500, timeout = 1000 * 10) // 每间隔1000ms请求一次，共10s
    public String getPayResultPage(String caseId, String serializedId)  {
        String queryMopayStatus = maitonApi.queryMopayStatus(caseId, serializedId);
        return queryMopayStatus;
    }
    @LoopCheck(desc = "订单详情页轮询", interval = 500, timeout = 1000 * 10) // 每间隔1000ms请求一次，共10s
    public String getOrderDetail(String caseId, String orderId, OrderSourceEnum sourceEnum)  {
        String orderDetail = null;
        switch (sourceEnum){
            case DPApp:
                orderDetail = maitonApi.DpOrderDetail(caseId,orderId);
                break;
            case MTApp:
                orderDetail = maitonApi.MtOrderDetail(caseId,orderId);
                break;
        }
        return orderDetail;
    }
    @LoopCheck(desc = "加载优惠台轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public CouponProduct loadUnifiedCashier(String caseId)  {
        CouponProduct couponProduct = maitonApi.loadUnifiedCashier(caseId).orElse(null);;
        return couponProduct;
    }
    @LoopCheck(desc = "查询优惠券的加密串", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    // 查询商家券的加密串
    public DeskCoupon getShopCouponCipher(String hongbaoid, String caseId ) {
        DeskCoupon deskCoupon = maitonApi.getShopCouponCipher(hongbaoid,caseId );
        return deskCoupon;
    }
    @LoopCheck(desc = "查询优惠券的加密串", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    // 查询平台券的加密串
    public DeskCoupon getPlatformCouponCipher(String hongbaoid, String caseId ) {
        DeskCoupon deskCoupon = maitonApi.getPlatformCouponCipher(hongbaoid,caseId );
        return deskCoupon;
    }
    @LoopCheck(desc = "发送商家券", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public MaitonHongbaoTResponse setShopPromo(UserModel userModel,Integer poiId,OrderSourceEnum orderSourceEnum) throws TException {
        MaitonHongbaoTResponse maitonHongbaoTResponse= thriftApi.setShopPromo(Long.valueOf(userModel.getUserId()),poiId,orderSourceEnum);
        if(maitonHongbaoTResponse.getData().size()==0){
            return null;
        }
        return maitonHongbaoTResponse;
    }
    @LoopCheck(desc = "发送平台券", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public UnifiedCouponIssueResponse setCouponPromo(UserModel userModel,Integer CouponGroupId, OrderSourceEnum orderSourceEnum) throws TException {
        UnifiedCouponIssueResponse unifiedCouponIssueResponse = thriftApi.setCouponPromo(Long.valueOf(userModel.getUserId()), CouponGroupId,orderSourceEnum);
        return unifiedCouponIssueResponse;
    }
    @LoopCheck(desc = "获取预订订单轮询", interval = 500, timeout = 500 * 20) // 每间隔500ms请求一次，共10s
    public Integer getResvOrderId(Integer platform) throws ResvTradeException, ResvOrderException, TException, InternalTException {
        Integer reserveOrderId = GetResvOrderIdForMaiton.reserveOrderId(platform);
        if (reserveOrderId == 0){
            return null;
        }else {
            return reserveOrderId;
        }
    }
    @LoopCheck(desc = "获取商家订单详情轮训", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap getMerchentOrderDetail(String caseId, String serializedId,String mtShopId){
        ResponseMap merchentOrderDetail = maitonApi.getMerchentOrderDetail(caseId, serializedId,mtShopId);
        if(merchentOrderDetail.getStatusCode() != 200){
            return null;
        }
        return merchentOrderDetail;
    }
}

