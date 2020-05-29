
package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.PayApi;
import com.google.common.collect.Lists;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.data.db.DBUtil;
import com.meituan.toolchain.mario.data.db.client.DBClient;
import com.meituan.toolchain.mario.framework.DBDataProvider;
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
import com.sankuai.meituan.resv.trade.idl.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sankuai.meituan.resv.order.utility.enums.HandleWayEnum.MERCHANT;
import static com.sankuai.meituan.resv.order.utility.enums.OperatorTypeEnum.SYSTEM;

/**
 * Created by buyuqi on 2019/12/8.
 */
@ClassAnnotation(author ="byq", type="thrift", des="预订下单-》接单-》修改订单相关接口")
@Slf4j
public class ResvInfo {
    public ResvInfo(TResvIGoodsService.Iface tResvIGoodsService,TResvTradeService.Iface tResvTradeService,RemoteResvOrderService remoteResvOrderService) {
        this.tResvIGoodsService = tResvIGoodsService;
        this.tResvTradeService = tResvTradeService;
        this.remoteResvOrderService = remoteResvOrderService;
    }
    TResvIGoodsService.Iface tResvIGoodsService;
    TResvTradeService.Iface tResvTradeService;
    RemoteResvOrderService remoteResvOrderService;

    //1。获取预订sku
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
    //2。获取预订订单
    public PlaceOrderResponseModel depositOrder(Integer bookableTime, Integer skuId, Integer skuVersion,Integer platform) throws TException,ResvTradeException {
        PlaceOrderResponseModel response = null;
        try {
            PlaceOrderRequestModel placeOrderRequestModel = new PlaceOrderRequestModel();
            placeOrderRequestModel.setPhoneInterCode("86");
            placeOrderRequestModel.setPlatform(platform);
            placeOrderRequestModel.setPoiId(6207656);
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
        //Assert.assertEquals(response.getStatus(),ResvOrderEnum.待支付);
        resvPay(response);
        return response;
    }
    //3。商家接单
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
    //4.获取订单状态
    public Integer pollingOrderStatus(Integer orderId,Integer platform) throws TException, ResvTradeException {
        Integer userId = 123;
        OrderStatusModel req = tResvTradeService.pollingOrderStatus(orderId,userId,platform);
        return req.getStatus();
    }
    //5.修改数据库预订时间
    public Integer updateBookableTime(Integer bookableTime,Integer orderId) throws TException, ResvOrderException {
        bookableTime = bookableTime - 3600;
        DBClient clientResv = DBUtil.getClient("resv");
        clientResv.update(ConfigMange.getValue("sql_resv_updata_bookableTime"), Lists.newArrayList((Object) bookableTime, orderId));
        return orderId;
    }

// 预订支付
    private boolean resvPay(PlaceOrderResponseModel response) {
        PayApi obj = new PayApi("stable.pay.test.sankuai.com");
        Map<String, String> params = new HashMap<String, String>();
        params.put("tradeno",response.preTradeNo);
        params.put("pay_token",response.payToken);
        //params.put("nb_platform", "..."); //可传可不传，默认值为www(即B端PC收银台)；可选值有www和touch。如果是B端i版收银台请传nb_platform=touch
        boolean ret = false;
        try {
            ret = obj.doPayForBusiness(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
