package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.business.mis.request.MisRefundReq;
import com.dianping.hui.business.mis.response.MisRefundResp;
import com.dianping.hui.business.mis.service.MisRefundOrderBaseService;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.OrderRefundUtil;
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

@ClassAnnotation(author = "liukang",depart = "C", des="买单流程")
@Slf4j

public class TestHuiFullProcessHours extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String ssoid;
    List<String> orderCreateResult = new ArrayList();

    @PigeonAPI(url =  "http://service.dianping.com/hui/huiBusinessService/misRefundOrderBaseService_1.0.0")
    private MisRefundOrderBaseService misRefundOrderBaseService;

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang",createTime = "2018-05-18",updateTime = "2018-05-18",des =
            "普通下单，超过12小时申请退款，订单流转客服")
    public void ms_c_HuiFullProcessHours_01() throws Exception {
        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(mtToken,mtClient,  "ms_c_huiFullProcess_101_loadUnifiedCashier");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken,mtClient,  "ms_c_huiFullProcess_101_uniCashierCreateOrder");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,mtToken);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(30);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken,mtClient,serializedId,"ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");
        //5、等待12小时后申请退款
        try {
            TimeUnit.HOURS.sleep(13);
        }catch (InterruptedException e){
            log.error(e.toString());
        }

        //6、申请退款
        OrderRefundUtil.orderRefundMT(mtToken,serializedId,"ms_c_huiFullProcess_101_orderRefund");
        log.info("用户已申请退款");

        //7、客服同意退款

    }

    @Test(groups = "P3")
    @MethodAnotation(author = "liukang",createTime = "2018-05-18",updateTime = "2018-05-18",des =
            "普通下单，退款后超过6小时商家未处理，订单流转客服")
    public void ms_c_HuiFullProcessHours_02() throws Exception {
        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(mtToken,mtClient,  "ms_c_huiFullProcess_101_loadUnifiedCashier");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken,mtClient,  "ms_c_huiFullProcess_101_uniCashierCreateOrder");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,mtToken);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(30);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken,mtClient,serializedId,"ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");

        //5、申请退款
        OrderRefundUtil.orderRefundMT(mtToken,serializedId,"ms_c_huiFullProcess_101_orderRefund");
        log.info("用户已申请退款");

        //5、等待6小时
        try {
            TimeUnit.HOURS.sleep(7);
        }catch (InterruptedException e){
            log.error(e.toString());
        }

        //6、客服同意退款（需状态流转为客服处理中）

        misRefund(serializedId);
    }

    private void misRefund(String serializedId){
        String _APIPATH = "/MisRefundOrderBaseService.agreeRefund";
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_huiFullProcess_001_misRefund");
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_misRefund");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        MisRefundReq misRefundReq = new MisRefundReq();
        misRefundReq.setPoiId(request.getInteger("poiId"));
        misRefundReq.setSerializedId(serializedId);
        misRefundReq.setOperator("0");
        misRefundReq.setOperatorIp("192.168.1.1");
        misRefundReq.setRefundReason("qa测试退款");
        misRefundReq.setSourceCode(OperationSourceCode.MTCUSTOMERSERVICE);
        misRefundReq.setSourceName("美团客服");
        MisRefundResp misAgreeRefundResp = misRefundOrderBaseService.agreeRefund(misRefundReq);
        System.out.println(misAgreeRefundResp.toString());
    }

}
