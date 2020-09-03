package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.request.ApplyRefundRequest;
import com.dianping.mopayprocess.refundflow.response.AgreeRefundResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRefund {
    private Long ym_dpUserId = 9000000000013201684L;
    private  Long ym_mtUserId = 5026362359L;

    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;


    @Test(groups = "P1",description = "美团app，买单使用折扣买单方案->方案选取->下单->支付->用户申请->商家同意->退款")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-01-13", updateTime = "2020-01-13", des =
            "普通下单(全单折)")
    public void ms_c_refund_123() throws InterruptedException {
//        Long ym_mtUserId = 5026362359L;
        Long ym_mtUserId = 29060740L;    // 尾号7718的账号
//        String orderId = "4818856010915746277";
//        String orderId = "4818856010919166437";  // 退款成功
       // String orderId = "4818856010985418070"; //推送用户申请退款
        String orderId = "4818856010989081942"; //美团用户申请退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(String.valueOf(ym_mtUserId)).userId(ym_mtUserId).build();
        log.info("执行退款订单id{}", orderId);
        ApplyRefundRequest applyRefundRequest = huiRefund.apply();
        log.info("申请退款结果:", JSON.toJSONString(applyRefundRequest));
        TimeUnit.SECONDS.sleep(2);

//        AgreeRefundResponse agreeRefundResponse = huiRefund.agree();
//        log.info("获取退款结果:{}", JSON.toJSONString(agreeRefundResponse));
//        TimeUnit.SECONDS.sleep(3);
//        agreeRefundResponse.isSuccess();
//        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(agreeRefundResponse));
//        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
    }

    @Test(groups = "P1",description = "美团app，买单使用折扣买单方案->方案选取->下单->支付->商家直接退款")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-01-13", updateTime = "2020-01-13")
    public void ms_c_refund_directPay() throws InterruptedException {
//         String orderId = "4818856010957136357";//"4818856010956980709";     //"4818856010955065829";    //"4818856010936878565";  //8-返券方案
//        String orderId = "4818856010944411109";    //❎"4818856010939833829";     //0元单 原价买单+商家券
//        String orderId = "4818856010944938274";    // 0元单 原价买单+平台券
//        String orderId = "4818856010950760738"; //买单优惠+平台券
//        String orderId = "4818856010950552037";   // 商家券+平台券
//        String orderId = "4818856010951643426";     // 每满减+返券
//        String orderId = "4818856010952793794";     // 惠吃卡
//        String orderId = "4818856010962083522";       // 惠吃卡+平台优惠
//        String orderId = "4818856010964347586";        // 惠吃卡+商家优惠
//        String orderId = "4818856010965822146";   // 惠吃卡+赠品券
//        String orderId = "4818856010967399106";// 0元单-惠吃卡+平台券
//        String orderId = "4818856010972665174";   // 预定金+原价
        String orderId = "4818856010980449986";     // 折扣+ka立减+预定金
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator(String.valueOf(ym_mtUserId)).userId(ym_mtUserId).build();
        log.info("执行退款订单id{}", orderId);
        DirectRefundResponse rep = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(rep));
        TimeUnit.SECONDS.sleep(3);
        rep.isSuccess();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(rep));
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");
    }

}
