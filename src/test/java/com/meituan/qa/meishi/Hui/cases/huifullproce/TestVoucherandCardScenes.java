package com.meituan.qa.meishi.Hui.cases.huifullproce;


import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
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
public class TestVoucherandCardScenes extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @Test(groups = "P3")
    @MethodAnotation(author = "qqy", createTime = "2019-09-11", updateTime = "2019-09-11", des =
            "买单使用会员余额(美团)")
    public void ms_c_voucherandCardScenes_01() throws Exception {

        //1、加载优惠台
        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_00001");
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

       /* //5、订单详情页

        CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, true, orderId);

        //6、申请退款

        OrderRefundUtil.orderRefundMT(mtToken, serializedId, "ms_c_huiFullProcess_101_orderRefund");

        log.info("用户已申请退款");
        //7、商家同意退款
        merchantAgreeRefund_02(serializedId);
        log.info("商家已同意退款");

        //8、退款后查询订单状态

        CreateOrderUtil.huiMaitonOrderMT("ms_c_huiFullProcess_101_huiMaitonOrderMT", mtToken, false, orderId);
   */ }
    public void merchantAgreeRefund_02( String serializedId) {

        String _APIPATH = "/RefundOrderBaseService.agreeRefund";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, "ms_c_huiFullProcess_002_merchantAgreeRefund");
        }catch (Exception e){
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
    }
    }
