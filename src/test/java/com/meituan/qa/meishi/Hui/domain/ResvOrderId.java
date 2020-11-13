package com.meituan.qa.meishi.Hui.domain;

import com.meituan.mtrace.TraceParam;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.sankuai.meituan.resv.i.thrift.exception.InternalTException;
import com.sankuai.meituan.resv.order.thrift.exception.ResvOrderException;
import com.sankuai.meituan.resv.trade.idl.exception.ResvTradeException;
import com.sankuai.meituan.resv.trade.idl.model.PlaceOrderResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;


/**
 * Created by buyuqi on 2019/12/8.
 */
@ClassAnnotation(author ="byq", type="thrift", des="预订下单-》接单-》修改订单相关接口")
@Slf4j
public class ResvOrderId {

    private void mtLogin(){
        Tracer.serverRecv(new TraceParam(""));
        Tracer.getServerTracer().getSpan().getForeverContext().put("userIdStr","5002907380");
        Tracer.getServerTracer().getSpan().getForeverContext().put("uuid","75FC45E70DA7FD7024468E0077DE660951ADFAD5FB298651AFB59400E67CA63F");
        Tracer.putContext("platform", String.valueOf(10));
    }
    private void dpLogin(){
        Tracer.serverRecv(new TraceParam(""));
        Tracer.getServerTracer().getSpan().getForeverContext().put("userIdStr","9007199254760212");
        Tracer.getServerTracer().getSpan().getForeverContext().put("uuid","75FC45E70DA7FD7024468E0077DE660951ADFAD5FB298651AFB59400E67CA63F");
        Tracer.putContext("platform", String.valueOf(20));
    }

    public Integer reserveOrder(ResvInfo resvInfo,Integer platfarm,String userToken) throws TException, InternalTException, ResvTradeException, ResvOrderException {
        if(platfarm == 10){
            mtLogin();
        }else {
            dpLogin();
        }
        Integer bookableTime = resvInfo.getBookableTime(platfarm);
        ResvSkuIdAndSkuVersion skuInfo = resvInfo.getSkuInfo(bookableTime);
        PlaceOrderResponseModel placeOrderResponseModel = resvInfo.depositOrder(bookableTime, skuInfo.getSkuId(), skuInfo.getSkuVersion(), platfarm,userToken);
        if(placeOrderResponseModel == null){
            return 0;
        }
        Integer resvOrderId = placeOrderResponseModel.getSucceedOrderId();
        Integer orderStatus = resvInfo.pollingOrderStatus(resvOrderId,platfarm);
        resvOrderId = resvInfo.acceptOrder(resvOrderId, orderStatus);
        resvInfo.updateBookableTime(bookableTime,resvOrderId);
        return resvOrderId;
    }

}
