package com.meituan.qa.meishi.Hui.cases.huifullproce;/*
package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.business.ecom.request.AgreeRefundReq;
import com.dianping.hui.business.ecom.response.AgreeRefundResp;
import com.dianping.hui.business.ecom.service.RefundOrderBaseService;
import com.dianping.hui.business.mis.service.MisRefundOrderBaseService;
import com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb.util.CallServiceUtil;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.OrderRefundUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import com.alibaba.fastjson.*;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import com.alibaba.fastjson.*;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.qa.meishi.util.MethodAnotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ClassAnnotation(author = "liukang", depart = "C", des = "test环境app买单流程")
@Slf4j
public class TestFullProcess4Verify extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String bisid = CommonLoginUtil.merchantAPPLogin();//商家端登录后获取
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

  @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "普通下单(满减)")
    public void ms_c_fullProcess4Verify_01() {

        //1、加载优惠台
        int couponOfferId= CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_01",couponOfferId);
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);
        //支付前商家查看一下订单
        JSONObject result = CallServiceUtil.callService("ms_c_cashierOverview_01", cashierOverviewPath, bisid);
        int beginOrderCount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getIntValue("totalOrderCount");
        Double beginOriginAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalOriginAmount");
        Double beginDealAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalDealAmount");
//支付前查看一下订单列表
        result = CallServiceUtil.callService("ms_c_cashierQuery_01", cashierqueryPath, bisid);
        JSONObject pageVo = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo");
        int beginOrderListCount;
        if (pageVo == null) {
            beginOrderListCount = 0;
        } else {
            beginOrderListCount = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo").getJSONArray("resultList").size();
        }

        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken, mtClient, serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");

        //5、订单详情页

       // CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, true);

        //6、申请退款

        OrderRefundUtil.orderRefundMT(mtToken, serializedId, "ms_c_huiFullProcess_101_orderRefund");

        log.info("用户已申请退款");
        //7、商家买单收银查看订单
       */
/* result = CallServiceUtil.callService("ms_c_cashierOverview_01", cashierOverviewPath, bisid);
        int afterOrderCount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getIntValue("totalOrderCount");
        Double afterOriginAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalOriginAmount");
        Double afterDealAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalDealAmount");
        Assert.assertTrue(afterOrderCount == beginOrderCount + 1, "afterOrderCount:" + afterOrderCount);
        Assert.assertTrue(afterDealAmount == beginDealAmount + 367, "afterDealAmount" + afterDealAmount);
        Assert.assertTrue(afterOriginAmount == beginOriginAmount + 400, "afterOriginAmount" + afterOriginAmount);
*//*

     */
/* //8、商家订单查询中查看订单
        result = CallServiceUtil.callService("ms_c_orderOverview_01", orderoverviewPath, bisid);
        afterOrderCount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getIntValue("totalOrderCount");
        afterOriginAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalOriginAmount");
        afterDealAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalDealAmount");
        Assert.assertTrue(afterOrderCount == beginOrderCount + 1, "afterOrderCount:" + afterOrderCount);
        Assert.assertTrue(afterDealAmount == beginDealAmount + 367, "afterDealAmount" + afterDealAmount);
        Assert.assertTrue(afterOriginAmount == beginOriginAmount + 400, "afterOriginAmount" + afterOriginAmount);
*//*

        //9、商家买单收银查询订单
       */
/* result = CallServiceUtil.callService("ms_c_cashierQuery_01", cashierqueryPath, bisid);
        int afterOrderListCount = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo").getJSONArray("resultList").size();
        Assert.assertTrue(afterOrderListCount == beginOrderListCount + 1, "cashierQuery_afterOrderListCount" + afterOrderListCount);
        //10、商家订单查询中查询订单
        log.info(String.valueOf(beginOrderListCount));
        result = CallServiceUtil.callService("ms_c_orderQuery_001", orderqueryPath, bisid);
        afterOrderListCount = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo").getJSONArray("resultList").size();
        Assert.assertTrue(afterOrderListCount == beginOrderListCount + 1, "orderQuery_afterOrderListCount" + afterOrderListCount);
        *//*
//7、商家同意退款
        merchantAgreeRefund(serializedId);
        log.info("商家已同意退款");

        //8、退款后查询订单状态

       // CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, false);

    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "app满减下单")
    public void ms_c_fullProcess4Verify_02() {
        //满减活动下单
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        log.info("满减couponOfferId:" + couponOfferId);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_01", couponOfferId);
        assertOrderResult(orderCreateResult, "满减");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_02", couponOfferId);
        assertOrderResult(orderCreateResult, "点评满减");
    }
    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "app折扣下单")
    public void ms_c_fullProcess4Verify_03() {
        //折扣下单
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_02");
        log.info("折扣couponOfferId:" + couponOfferId);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_03", couponOfferId);
        assertOrderResult(orderCreateResult, "折扣");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_04", couponOfferId);
        assertOrderResult(orderCreateResult, "点评折扣");
    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "app原价下单")
    public void ms_c_fullProcess4Verify_04() {
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_05");
        assertOrderResult(orderCreateResult, "原价");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_06");
        assertOrderResult(orderCreateResult, "点评原价");
    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "app智能支付下单")
    public void ms_c_fullProcess4Verify_05() {
        //智能支付下单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_unicashiercreateorder_118");
        assertOrderResult(orderCreateResult, "智能支付");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_unicashiercreateorder_018");
        assertOrderResult(orderCreateResult, "点评智能支付");
    }
    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "app商家券下单")
    public void ms_c_fullProcess4Verify_06() {
        //商家券下单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_001", 0, "swXLxrhmVEf7RXBtXdobMyDYmojklaVqUoiknCLVRj8%3D%23We86oOa3ziI7m5qwHPLq%2BvvHxDKtvDevszaZ7m69tRc%3D", null);
        assertOrderResult(orderCreateResult, "商家券");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_002", 0, "RzsctPZlAKPtxGicu7eHr38XASaKTLycTySLLcqmobQ%3D", null);
        assertOrderResult(orderCreateResult, "点评商家券");
    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "m站下单")
    public void ms_c_fullProcess4Verify_07() {
        //m站下单
        Map<String, String> orderMCreateResult = CreateOrderUtil.ajaxCreateOrder(dpToken, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36", "ms_c_ajaxcreateorder_01");
        Assert.assertTrue("创单成功".equals(orderMCreateResult.get("msg")));
        Assert.assertTrue(!orderMCreateResult.get("payToken").isEmpty());
        Assert.assertTrue(!orderMCreateResult.get("tradeNo").isEmpty());
        Assert.assertTrue(!orderMCreateResult.get("serializedId").isEmpty());
        log.info("m站下单成功：" + orderMCreateResult);
    }

   */
/* @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "mm站下单")
    public void ms_c_fullProcess4Verify_08() {
        Map<String, String> orderMCreateResult = CreateOrderUtil.mmCreateOrder(dpToken, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36", "ms_c_mmCreateOrder_01");
        Assert.assertTrue("创单成功".equals(orderMCreateResult.get("msg")));
        Assert.assertTrue(!orderMCreateResult.get("serializedId").isEmpty());
        log.info("mm站下单成功" + orderMCreateResult);
    }*//*


   */
/* @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "微信小程序下单")
    public void ms_c_fullProcess4Verify_09() {
        //微信小程序下单
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        Map<String, String> wxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(mtToken, "ms_c_wxacCreateOrder_101", couponOfferId, null);
        Assert.assertTrue("success".equals(wxaOrderCreateResult.get("msg")));
        Assert.assertTrue(!wxaOrderCreateResult.get("serializedId").isEmpty());
        Assert.assertTrue(!"0".equals(wxaOrderCreateResult.get("orderId")));
        log.info("美团微信小程序下单成功");
        wxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(dpToken, "ms_c_wxacCreateOrder_001", couponOfferId, null);
        Assert.assertTrue("success".equals(wxaOrderCreateResult.get("msg")));
        Assert.assertTrue(!wxaOrderCreateResult.get("serializedId").isEmpty());
        Assert.assertTrue(!"0".equals(wxaOrderCreateResult.get("orderId")));
        log.info("点评微信小程序下单成功");
      *//*
*/
/*  wxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(dpToken, "ms_c_wxacCreateOrder_018", 0, "+xqrtEinhODeoGXFchLsqXC3QFH9y5cHj+Mp25v5S298EJ6l4A84wwDVJZmu01DZ");
        Assert.assertTrue("success".equals(wxaOrderCreateResult.get("msg")));
        Assert.assertTrue(!wxaOrderCreateResult.get("serializedId").isEmpty());
        Assert.assertTrue(!"0".equals(wxaOrderCreateResult.get("orderId")));
        log.info("点评微信小程序商家券下单成功");*//*
*/
/*

    }*//*


   */
/* @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "自助验券单独验券")
    public void ms_c_fullProcess4Verify_10() {
        //自助验券单独验券
        CreateOrderUtil.receiptVerifyMT(mtToken, mtClient, "ms_c_fullProcess4receiptverify_01");
        log.info("美团自助验券成功");
        CreateOrderUtil.receiptVerifyDP(dpToken, dpClient, "ms_c_fullProcess4receiptverify_02");
        log.info("点评自助验券成功");
    }
    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "自助验券下单")
    public void ms_c_fullProcess4Verify_11() {
        //自助验券下单
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_03");
        log.info("couponOfferId:" + couponOfferId);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrderMT(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_07", 0);
        assertOrderResult(orderCreateResult, "美团自助验券下单");
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrderDP(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_08", couponOfferId);
        assertOrderResult(orderCreateResult, "点评自助验券下单");
    }
*//*

   @Test(groups = "P3")
   @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
           "抵用券+会员余额(美团)")
   public void ms_c_fullProcess4Verify_10() {

       //1、加载优惠台
       int couponOfferId= CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_00001");
       log.info("加载优惠台成功");

       //2、输入金额创建订单
       orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_00002");
       payToken = orderCreateResult.get(0);
       tradeNo = orderCreateResult.get(1);
       orderId = orderCreateResult.get(2);
       serializedId = orderCreateResult.get(3);
      // orderCreateTime=orderCreateResult.get(4);
       log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
               + " payToken = " + payToken);
       //3、支付
       CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

       //4、支付结果页
       try {
           TimeUnit.SECONDS.sleep(20);
       } catch (InterruptedException e) {
           log.error(e.toString());
       }
       CreateOrderUtil.queryMopayStatus(mtToken, mtClient, serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
       log.info("获取到订单支付结果");

       //5、订单详情页

       CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, true,orderId);

       //6、申请退款

        OrderRefundUtil.orderRefundMT(mtToken, serializedId, "ms_c_huiFullProcess_101_orderRefund");

        log.info("用户已申请退款");
       //7、商家同意退款
        merchantAgreeRefund(serializedId);
       log.info("商家已同意退款");

       //8、退款后查询订单状态

        CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, false,orderId);

   }

   @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "抵用券+会员余额(点评)")
    public void ms_c_fullProcess4Verify_11() {

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(dpToken, dpClient, "ms_c_4Verify_uniCashierCreateOrder_00003");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        // orderCreateTime=orderCreateResult.get(4);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);
        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken, mtClient, serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");

      */
/*  //5、订单详情页

        //CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, true);

        //6、申请退款

        OrderRefundUtil.orderRefundMT(mtToken, serializedId, "ms_c_huiFullProcess_101_orderRefund");

        log.info("用户已申请退款");
        //7、商家同意退款
        merchantAgreeRefund(serializedId);
        log.info("商家已同意退款");*//*


        //8、退款后查询订单状态

        // CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, false);

    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "普通下单(满减) 商家发起退款")
    public void ms_c_fullProcess4Verify_12() {

        //1、加载优惠台
        int couponOfferId= CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_01",couponOfferId);
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);
        //支付前商家查看一下订单
        JSONObject result = CallServiceUtil.callService("ms_c_cashierOverview_01", cashierOverviewPath, bisid);
        int beginOrderCount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getIntValue("totalOrderCount");
        Double beginOriginAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalOriginAmount");
        Double beginDealAmount = result.getJSONObject("huiOverviewResponseVo").getJSONObject("huiOverviewVo").getDouble("totalDealAmount");
//支付前查看一下订单列表
        result = CallServiceUtil.callService("ms_c_cashierQuery_01", cashierqueryPath, bisid);
        JSONObject pageVo = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo");
        int beginOrderListCount;
        if (pageVo == null) {
            beginOrderListCount = 0;
        } else {
            beginOrderListCount = result.getJSONObject("huiOrderPageResponseVo").getJSONObject("pageVo").getJSONArray("resultList").size();
        }

        //3、支付
        CreateOrderUtil.orderPay(payToken, tradeNo, mtToken);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken, mtClient, serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");

    }
  */
/*  @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "优惠码下单")
    public void ms_c_fullProcess4Verify_13() {
        //优惠码下单
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_013");
        log.info("couponOfferId:" + couponOfferId);
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_113", 0);
        assertOrderResult(orderCreateResult, "美团优惠码下单");

    }*//*


   */
/* @Test(groups = "P3")
    @MethodAnotation(author = "liukang", createTime = "2018-10-21", updateTime = "2018-10-21", des =
            "平台立减下单")
    public void ms_c_fullProcess4Verify_14() {
        //优惠码下单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_014", 0);
        assertOrderResult(orderCreateResult, "美团平台立减下单");

    }
*//*


    private void assertOrderResult(List<String> orderCreateResult, String s) {
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId=orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        Assert.assertTrue(!payToken.isEmpty());
        Assert.assertTrue(!tradeNo.isEmpty());
        Assert.assertTrue(!"0".equals(orderId));
        Assert.assertTrue(!serializedId.isEmpty());
        log.info(s + "创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);
    }

    public void merchantAgreeRefund( String serializedId) {
//        Response response = OrderRefundUtil.merchantAgreeRefund(bsid,serializedId);
//        Assert.assertTrue("退款成功".equals(response.getValueByJsonPath("$.msg")));
        String _APIPATH = "/RefundOrderBaseService.agreeRefund";
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift, _APIPATH, "ms_c_huiFullProcess_001_merchantAgreeRefund");
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, "ms_c_huiFullProcess_002_merchantAgreeRefund");
        }catch (Exception e){
            log.error(e.getMessage());
            return;
        }
        AgreeRefundReq refundReq = new AgreeRefundReq();
        refundReq.setPoiId(request.getInteger("poiId"));
        refundReq.setSerializedId(serializedId);
        refundReq.setOperator(0);
        refundReq.setOperatorIp("192.168.1.1");
        refundReq.setRefundReason("qa测试退款");
        refundReq.setSourceCode(OperationSourceCode.MTMERCHANTBACKEND);
        refundReq.setSourceName("商家后台");
        AgreeRefundResp refundOrderResp = refundOrderBaseService.agreeRefund(refundReq);
        log.info("商家退款获取到的状态：" + refundOrderResp.getResultDesc());
    }

}
*/
