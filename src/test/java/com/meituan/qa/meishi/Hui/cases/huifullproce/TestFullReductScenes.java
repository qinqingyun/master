package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb.util.CallServiceUtil;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ClassAnnotation(author = "qqy", depart = "C", des = "test环境app满减流程")
@Slf4j
public class TestFullReductScenes extends TestDPLogin {
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
    @MethodAnotation(author = "qqy", createTime = "2019-09-10", updateTime = "2019-09-10", des =
            "普通下单(满减)")
    public void ms_c_fullReductScenes_01() throws Exception {

        //1、加载优惠台
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken, mtClient, "ms_c_4Verify_uniCashierCreateOrder_01", couponOfferId);
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

       /* //5、订单详情页

        CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, true,orderId);
*/
       /* //6、申请退款
        OrderRefundUtil.orderRefundMT(mtToken, serializedId, "ms_c_huiFullProcess_101_orderRefund");

        log.info("用户已申请退款");
        //7、商家同意退款
        merchantAgreeRefund_01(serializedId);
        log.info("商家已同意退款");

        //8、退款后查询订单状态

        CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, false, orderId);
   */
    }

    public void merchantAgreeRefund_01(String serializedId) {


        String _APIPATH = "/RefundOrderBaseService.agreeRefund";
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(_APIPATH, "ms_c_huiFullProcess_001_merchantAgreeRefund");
        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }
//        AgreeRefundReq refundReq = new AgreeRefundReq();
//        refundReq.setPoiId(request.getInteger("poiId"));
//        refundReq.setSerializedId(serializedId);
//        refundReq.setOperator(0);
//        refundReq.setOperatorIp("192.168.1.1");
//        refundReq.setRefundReason("qa测试退款");
//        refundReq.setSourceCode(OperationSourceCode.MTMERCHANTBACKEND);
//        refundReq.setSourceName("商家后台");
//        AgreeRefundResp refundOrderResp = refundOrderBaseService.agreeRefund(refundReq);
//        log.info("商家退款获取到的状态：" + refundOrderResp.getResultDesc());
//        Assert.assertTrue("退款成功".equals(refundOrderResp.getResultDesc()));
    }
}
