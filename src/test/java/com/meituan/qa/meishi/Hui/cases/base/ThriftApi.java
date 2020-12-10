package com.meituan.qa.meishi.Hui.cases.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.PayApi;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.common.enums.RefundFlowPlatformEnum;
import com.dianping.hui.common.enums.RefundFlowTargetEnum;
import com.dianping.hui.common.enums.RefundFlowTypeEnum;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.hui.order.shard.service.QueryMainOrder4MTService;
import com.dianping.mopayprocess.refundflow.request.AgreeRefundRequest;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.request.DirectRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.ApplyRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueOption;
import com.dianping.unified.coupon.issue.api.request.UnifiedCouponIssueRequest;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.meituan.mtrace.Tracer;
import com.meituan.nibscp.unity.validation.api.request.JsonDiffRequest;
import com.meituan.nibscp.unity.validation.api.response.DiffResponse;
import com.meituan.nibscp.unity.validation.api.service.DiffService;
import com.meituan.nibtp.trade.client.buy.enums.BizSpaceId;
import com.meituan.nibtp.trade.client.buy.enums.OrderResultCodeEnum;
import com.meituan.nibtp.trade.client.buy.response.QueryOrderMappingRes;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.domain.ResvSkuIdAndSkuVersion;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.qa.meishi.Hui.util.CheckOrderType;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.data.db.DBUtil;
import com.meituan.toolchain.mario.data.db.client.DBClient;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.util.MtraceUtil;
import com.sankuai.meituan.resv.i.thrift.exception.InternalTException;
import com.sankuai.meituan.resv.i.thrift.model.BookableTimeQueryModel;
import com.sankuai.meituan.resv.i.thrift.model.TableFullInfoDTO;
import com.sankuai.meituan.resv.i.thrift.model.TableInfoQueryDTO;
import com.sankuai.meituan.resv.i.thrift.model.TimeModel;
import com.sankuai.meituan.resv.i.thrift.service.TResvIGoodsService;
import com.sankuai.meituan.resv.order.thrift.dto.AcceptOrderDTO;
import com.sankuai.meituan.resv.order.thrift.dto.OperateContextDTO;
import com.sankuai.meituan.resv.order.thrift.exception.ResvOrderException;
import com.sankuai.meituan.resv.order.thrift.service.RemoteResvOrderService;
import com.sankuai.meituan.resv.trade.idl.TResvTradeService;
import com.sankuai.meituan.resv.trade.idl.exception.ResvTradeException;
import com.sankuai.meituan.resv.trade.idl.model.GoodsTypeEnum;
import com.sankuai.meituan.resv.trade.idl.model.OrderStatusModel;
import com.sankuai.meituan.resv.trade.idl.model.PlaceOrderRequestModel;
import com.sankuai.meituan.resv.trade.idl.model.PlaceOrderResponseModel;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.stringtemplate.v4.ST;
import org.testng.Assert;

import java.util.*;

import static com.meituan.nibscp.unity.common.api.enums.MigrationBizTypeEnum.FOOD_BUY;
import static com.sankuai.meituan.resv.order.utility.enums.HandleWayEnum.MERCHANT;
import static com.sankuai.meituan.resv.order.utility.enums.OperatorTypeEnum.SYSTEM;
import static java.lang.Boolean.TRUE;


/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class ThriftApi {
    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0",desc = "退款rpc服务")
    RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;
    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    @ThriftAPI(desc = "unity平台diff工具", appkey = "com.sankuai.nibscp.unity.validation", interfaceName = "com.meituan.nibscp.unity.validation.api.service.DiffService")
    DiffService diffService;
    @PigeonAPI(url = "http://service.dianping.com/huiOrderService/QueryMainOrder4MTService_1.0.0")
    QueryMainOrder4MTService queryMainOrder4MTService;
    @ThriftAPI(appkey = "com.sankuai.resv.c.i",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    TResvIGoodsService.Iface tResvIGoodsService;
    @ThriftAPI(appkey = "com.sankuai.resv.c.trade",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    TResvTradeService.Iface tResvTradeService;
    @ThriftAPI(appkey = "com.sankuai.resv.c.order",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    RemoteResvOrderService remoteResvOrderService;
    /**
     * unity平台买单数据diff
     *
     */
    public DiffResponse getOrderDiff(String newData,String oldData) {
        JsonDiffRequest jsonDiffRequest = new JsonDiffRequest();
        jsonDiffRequest.setMigrationBizTypeEnum(FOOD_BUY);
        jsonDiffRequest.setIdentity("food_buy.normal.supply.default-8");
        jsonDiffRequest.setTraceId(Tracer.id());
        jsonDiffRequest.setNewJsonData(newData);
        jsonDiffRequest.setOldJsonData(oldData);
        DiffResponse response = diffService.diff(jsonDiffRequest);
        return response;
    }

    /**
     * 新老订单映射
     *
     */
    public MappingOrderIds getMappingOrderIds(String orderId) throws Exception {
        MappingOrderIds mappingOrderIds = new MappingOrderIds();
        QueryOrderMappingRes orderMappingRes;
        String oldOrderId;
        String newOrderId;
        // 判断订单号大小，来调不同的接口查询订单映射信息
        // 如果不是新系统订单号，调loadByBizOrderId
        if (!CheckOrderType.isNewOrder(orderId)) {
            orderMappingRes = orderMappingService.loadByBizOrderId(orderId, BizSpaceId.FOOD.getCode());
            if (!orderMappingRes.getHeader().isSuccess()) {
                return null;
            }
            oldOrderId = orderMappingRes.getOrderMappingDTO().getBizOrderId();
            newOrderId = orderMappingRes.getOrderMappingDTO().getOrderId().toString();
        } else {
            // 如果是新系统订单号，调loadByOrderId
            //TODO:拿到数据给orderid赋值，其余反馈null
            orderMappingRes = orderMappingService.loadByOrderId(Long.parseLong(orderId));
            if (orderMappingRes.getHeader().getResultCode() == OrderResultCodeEnum.ORDER_MAPPING_NOT_FOUND.getCode()) {
                // 如果查不到映射，新老系统均使用orderId。须先判断ORDER_MAPPING_NOT_FOUND。
                //return null;
                oldOrderId = orderId;
                newOrderId = orderId;
            } else if (!orderMappingRes.getHeader().isSuccess()) {
                return null;
            } else {
                oldOrderId = orderMappingRes.getOrderMappingDTO().getBizOrderId();
                newOrderId = orderMappingRes.getOrderMappingDTO().getOrderId().toString();
            }
        }
        mappingOrderIds.setOldOrderId(oldOrderId);
        mappingOrderIds.setNewOrderId(newOrderId);
        log.info("查询订单映射: oldOrderId: " + mappingOrderIds.getOldOrderId() + "，newOrderId: " + mappingOrderIds.getNewOrderId());
        return mappingOrderIds;
    }
    /**
     * 直接退款
     *
     */
    public DirectRefundResponse superRefund(String ip, OrderModel orderModel) {
        // 生成新Trace
        MtraceUtil.generatTrace("商家操作直退");
        // 直接发起退款
        DirectRefundRequest request = new DirectRefundRequest();
        request.setSuperRefund(0);
        request.setReason("直接退款");
        request.setIp("127.0.0.1");
        request.setDesc("直接退款");
        request.setOperator("qa-autocase");
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setTarget(RefundFlowTargetEnum.CUSTOM_SERVICE.getCode());
        //request.setSuperRefund(1); 超级退款
        request.setType(RefundFlowTypeEnum.AGREE.getCode());
        request.setRefundSource(OperationSourceCode.CUSTOMERSERVICE);
        request.setPlatform(RefundFlowPlatformEnum.MIS.getCode());
        log.info("商家直退入参{}",JSON.toJSONString(request));
        DirectRefundResponse response = refundFlowService.refund(request);
        return response;
    }
    /**
     * 用户申请退款
     *
     */
    public ApplyRefundResponse applyRefund(OrderModel orderModel, UserModel userModel) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        ApplyRefundRequest request = new ApplyRefundRequest();
        request.setApp(1);
        request.setCreditPlatform(1);
        request.setLoginType(1);
        request.setMobile("17610420086");
        request.setUa("ua-test");
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setDesc("test nib apply");
        request.setOrderTime(System.currentTimeMillis());
        request.setFingerprint("finger");
        request.setUuid("uuid");
        request.setOperator(userModel.getUserId());
        request.setOrderSource(0);
        request.setPlatform(1);
        request.setIp("127.0.0.1");
        request.setReason("付款错误");
        request.setDesc("付款错误");
        request.setTarget(RefundFlowTargetEnum.USER.getCode());
        request.setType(RefundFlowTypeEnum.APPLY.getCode());
        // 原路
        request.setRouter(1);
        request.setUserId(Long.valueOf(userModel.getUserId()));
        log.info("申请退款参数：{}",JSON.toJSONString(request));
        ApplyRefundResponse applyRefundResponse = refundFlowService.applyRefund(request);
        return applyRefundResponse;
    }
    /**
     * 商家同意退款
     *
     */
    public AgreeRefundResponse agreeRefund(OrderModel orderModel,UserModel userModel) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        AgreeRefundRequest request = new AgreeRefundRequest();
        request.setReason("同意退款");
        request.setIp("127.0.0.1");
        request.setDesc("同意退款desc");
        request.setOperator(userModel.getUserId());
        request.setOrderId(Long.valueOf(orderModel.getOrderId()));
        request.setTarget(RefundFlowTargetEnum.MERCHANT.getCode());
        request.setPlatform(RefundFlowPlatformEnum.ECOM.getCode());
        String ret = JSON.toJSONString(request);
        System.out.println(ret);
        AgreeRefundResponse agreeRefundResponse = refundFlowService.agreeRefund(request);
        if (agreeRefundResponse.getErrCode() == 0) {
            agreeRefundResponse.setIsSuccess(true);
        }
        return agreeRefundResponse;
    }
        /**
         * 商家券发券接口
         *
         */
    public MaitonHongbaoTResponse setShopPromo(Long userId, Integer poiId, OrderSourceEnum orderSourceEnum) throws TException {
        MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
        switch (orderSourceEnum){
            case MTApp:
            case MTWx:
                maitonHongbaoTRequest.setPlatform(Platform.MT);
                break;
            case DPApp:
            case DPM:
            case DPWx:
                maitonHongbaoTRequest.setPlatform(Platform.DP);
                break;
        }
        maitonHongbaoTRequest.setUserId(userId);
        maitonHongbaoTRequest.setPoiId(poiId);
        maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
        maitonHongbaoTRequest.setOrderId(123132131);
        maitonHongbaoTRequest.setOrderPrice(1);
        MaitonHongbaoTResponse response = maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
        return response;
    }
    /**
     * 平台券发券接口
     *
     */
    public UnifiedCouponIssueResponse setCouponPromo(Long userId,Integer CouponGroupId, OrderSourceEnum orderSourceEnum) {
        UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
        switch (orderSourceEnum){
            case MTApp:
            case MTWx:
                couponIssueRequest.setUserType("MT");
                break;
            case DPApp:
            case DPM:
            case DPWx:
                couponIssueRequest.setUserType("DP");
                break;
        }
        couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
        couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(CouponGroupId));
        couponIssueRequest.setOperator("qa-system");
        couponIssueRequest.setUserId(userId);

        UnifiedCouponIssueOption option = new UnifiedCouponIssueOption();
        option.setCreditType(0);
        option.setNotifyType(0);
        UnifiedCouponIssueResponse unifiedCouponIssueResponse = unifiedCouponIssueTrustService.issueTrustCoupon(couponIssueRequest, option);
        log.info("平台券创建结果：{}",JSONObject.toJSONString(unifiedCouponIssueResponse));
        return unifiedCouponIssueResponse;
    }
    /**
     * 老系统订单查询
     */
    public QueryOrderResponse getMaidonOrder(String orderId) {
        QueryOrderResponse queryOrderResponse = queryMainOrder4MTService.queryMainOrderByOrderId(Long.valueOf(orderId));
        return queryOrderResponse;
    }
    /**
     * 1.获取预订sku
     */
    public Integer getBookableTime(Integer platform) throws TException, InternalTException {
        String _APIPATH = "/tResvIGoodsService.getBookableTime";
        String caseIdList = "ms_c_resvGetBookableTime";
        JSONObject obj = DBDataProvider.getRequest(_APIPATH,caseIdList);
        log.info("请求参数{}",obj);
        if (platform == 20){
            obj.put("platform",platform);
        }
        BookableTimeQueryModel bookableTimeQueryModel = JSON.parseObject(obj.toString(), BookableTimeQueryModel.class);
        TimeModel resp = tResvIGoodsService.getBookableTime(bookableTimeQueryModel);
        Integer bookableTime = resp.getTimes().get(0).getMinutes().get(0).getMinutes();
        log.info("可预订时间{}", bookableTime);
        return bookableTime;
    }
    public ResvSkuIdAndSkuVersion getSkuInfo(Integer bookableTime) throws TException, InternalTException {
        String _APIPATH_LIST = "/tResvIGoodsService.getTableInfoQuery";
        String caseIdList = "ms_c_resvGetSkuInfo_01";
        JSONObject obj = DBDataProvider.getRequest(_APIPATH_LIST,caseIdList);
        TableInfoQueryDTO tableInfoQueryDTO = JSON.parseObject(obj.toString(), TableInfoQueryDTO.class);
        tableInfoQueryDTO.setBookingTime(bookableTime);
        List<TableFullInfoDTO> resp = tResvIGoodsService.getTableInfoQuery(tableInfoQueryDTO);
        ResvSkuIdAndSkuVersion resvSkuIdAndSkuVersion = new ResvSkuIdAndSkuVersion();
        resvSkuIdAndSkuVersion.setSkuId(resp.get(0).getSkuId());
        resvSkuIdAndSkuVersion.setSkuVersion(resp.get(0).getSkuVersion());
        log.info("可预订sku{}", resvSkuIdAndSkuVersion.getSkuId() + "," + resvSkuIdAndSkuVersion.getSkuVersion());
        return resvSkuIdAndSkuVersion;
    }
    /**
     * 2。获取预订订单
     */
    public PlaceOrderResponseModel depositOrder(Integer bookableTime, Integer skuId, Integer skuVersion, Integer platform) throws TException, ResvTradeException {
        PlaceOrderResponseModel response = null;
        try {
            PlaceOrderRequestModel placeOrderRequestModel = new PlaceOrderRequestModel();
            placeOrderRequestModel.setPhoneInterCode("86");
            placeOrderRequestModel.setPlatform(platform);
            placeOrderRequestModel.setPoiId(95191712);
            placeOrderRequestModel.setBookingTime(bookableTime);
            placeOrderRequestModel.setPosition(0);
            placeOrderRequestModel.setName("test");
            placeOrderRequestModel.setGender(10);
            placeOrderRequestModel.setPhone("18210527718");
            placeOrderRequestModel.setUserIp("172.18.98.104");
            placeOrderRequestModel.setNumber(5);
            placeOrderRequestModel.setGoodsTypes(Arrays.asList(GoodsTypeEnum.NORMAL));
            placeOrderRequestModel.setCityId(0);
            placeOrderRequestModel.setSkuId(skuId);
            placeOrderRequestModel.setSkuVersion(skuVersion);
            placeOrderRequestModel.setDepositMoney("10");
            response = tResvTradeService.placeOrder(placeOrderRequestModel);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
        return response;
    }
    /**
     * 3。商家接单
     */
    public Integer acceptOrder(Integer orderId,Integer orderStatus) throws TException, ResvOrderException {
        AcceptOrderDTO acceptOrderDTO = new AcceptOrderDTO();
        OperateContextDTO operateContextDTO  = new OperateContextDTO();
        acceptOrderDTO.setHandleWayEnum(MERCHANT);
        acceptOrderDTO.setOrderId(orderId);
        operateContextDTO.setOperatorTypeEnum(SYSTEM);
        if(orderStatus != 10){
            return 0;
        }
        remoteResvOrderService.acceptOrder(acceptOrderDTO,operateContextDTO);
        return orderId;
    }
    /**
     * 4。获取订单状态
     */
    public Integer pollingOrderStatus(Integer orderId,Integer platform) throws TException, ResvTradeException {
        Integer userId = 123;
        OrderStatusModel req = tResvTradeService.pollingOrderStatus(orderId,userId,platform);
        return req.getStatus();
    }
    /**
     * 5。修改数据库预订时间
     */
    public Integer updateBookableTime(Integer bookableTime,Integer orderId) {
        bookableTime = bookableTime - 3600;
        DBClient clientResv = DBUtil.getClient("resv");
        clientResv.update(ConfigMange.getValue("sql_resv_updata_bookableTime"), Lists.newArrayList((Object) bookableTime, orderId));
        return orderId;
    }
}
