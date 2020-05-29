package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.base.business.enums.OperationSourceCode;
import com.dianping.hui.business.ecom.request.AgreeRefundReq;
import com.dianping.hui.business.ecom.request.RefundOrderReq;
import com.dianping.hui.business.ecom.response.AgreeRefundResp;
import com.dianping.hui.business.ecom.response.RefundOrderResp;
import com.dianping.hui.business.ecom.service.RefundOrderBaseService;
import com.dianping.hui.business.mis.request.MisRefundReq;
import com.dianping.hui.business.mis.response.MisRefundResp;
import com.dianping.hui.business.mis.service.MisRefundOrderBaseService;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
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

@ClassAnnotation(author = "liukang",depart = "C", des="买单流程")
@Slf4j
public class TestHuiFullProcess extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String bsid;//商家端登录后获取
    //用于标识调用美团还是点评（true为点评）
    private boolean flag = true;
    List<String> orderCreateResult = new ArrayList();

	@PigeonAPI(url = "http://service.dianping.com/hui/huiBusinessService/misRefundOrderBaseService_1.0.0")
    private MisRefundOrderBaseService misRefundOrderBaseService;

    @PigeonAPI(url ="http://service.dianping.com/hui/huiBusinessService/refundOrderBaseService_1.0.0")
    private RefundOrderBaseService refundOrderBaseService;

    @PigeonAPI(url="http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @Test(groups = "P3",dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-05-18",updateTime = "2018-05-18",des =
            "普通下单(满减)，退款6小时内商家同意退款")
    public void ms_c_huiFullProcess_01(String token,String userAgent,String caseId) throws Exception {

        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(token,userAgent,caseId + "_loadUnifiedCashier");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(token,userAgent,caseId + "_uniCashierCreateOrder");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,token);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(token,userAgent,serializedId,caseId + "_queryMopayStatus");
        log.info("获取到订单支付结果");

       /* //5、订单详情页

        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,true);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,true,orderId);
        }

        //6、申请退款
        if(flag) {
            OrderRefundUtil.orderRefundDP(token, serializedId, caseId + "_orderRefund");
        }else {
            OrderRefundUtil.orderRefundMT(token, serializedId, caseId + "_orderRefund");
        }
        log.info("用户已申请退款");

        //7、商家同意退款
        merchantAgreeRefund(serializedId);
        log.info("商家已同意退款");

        //8、退款后查询订单状态
        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,false);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,false,orderId);
        }

        flag = !flag;
   */ }

    @Test(groups = "P3",dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-05-22",updateTime = "2018-05-22",des =
            "普通下单，24小时内商家直接退款")
    public void ms_c_huiFullProcess_02(String token,String userAgent,String caseId) throws Exception {


        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(token,userAgent,caseId + "_loadUnifiedCashier");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(token,userAgent,caseId + "_uniCashierCreateOrder");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,token);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(30);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(token,userAgent,serializedId,caseId + "_queryMopayStatus");
        log.info("获取到订单支付结果");

     /*   //5、订单详情页
        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,true);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,true,orderId);
        }

        //商家登录获取bsid
//         merchantLogin();

        //6、商家直接退款
        merchantOrderRefund(serializedId);

        //7、退款后查询订单状态
        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,false);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,false,orderId);
        }

        flag = !flag;
    */}

    @Test(groups = "P3",dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-05-22",updateTime = "2018-05-22",des =
            "普通下单，客服直接退款")
    public void ms_c_huiFullProcess_03(String token,String userAgent,String caseId) throws Exception {

        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(token,userAgent,caseId + "_loadUnifiedCashier");
        log.info("加载优惠台成功");

        //2、输入金额创建订单
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(token,userAgent,caseId + "_uniCashierCreateOrder");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,token);

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(30);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(token,userAgent,serializedId,caseId + "_queryMopayStatus");
        log.info("获取到订单支付结果");

      /*  //5、订单详情页
        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,true);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,true,orderId);
        }

        //6、客服直接退款
        misRefund(serializedId);

        //7、退款后查询订单状态
        if(flag){
            CreateOrderUtil.huiMaitonOrderDP(caseId + "_huiMaitonOrderDP",dpToken,false);
        }else {
            CreateOrderUtil.huiMaitonOrderMT(caseId + "_huiMaitonOrderMT",mtToken,false,orderId);
        }

        flag = !flag;
   */ }

    public void merchantLogin(){
        bsid = CommonLoginUtil.merchantAPPLogin();
        log.info("获取到商家后台bsid：" + bsid);
    }

    //商家同意退款
    public void merchantAgreeRefund(String serializedId){
//        Response response = OrderRefundUtil.merchantAgreeRefund(bsid,serializedId);
//        Assert.assertTrue("退款成功".equals(response.getValueByJsonPath("$.msg")));
        String _APIPATH = "/RefundOrderBaseService.agreeRefund";
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_huiFullProcess_001_merchantAgreeRefund");
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_merchantAgreeRefund");
        }catch (Exception e){
            log.error(e.getMessage());
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
        log.info("商家退款获取到的状态："+refundOrderResp.getResultDesc());
    }

    //商家直接进行退款
    public void merchantOrderRefund(String serializedId){
//        Response response = OrderRefundUtil.merchantOrderRefund(bsid,serializedId);
//        Assert.assertTrue("退款成功".equals(response.getValueByJsonPath("$.msg")));
        String _APIPATH = "/RefundOrderBaseService.refund";
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_huiFullProcess_001_merchantRefund");
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_merchantRefund");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        RefundOrderReq refundReq = new RefundOrderReq();
        refundReq.setPoiId(request.getInteger("poiId"));
        refundReq.setSerializedId(serializedId);
        refundReq.setOperator("0");
        refundReq.setOperatorIp("192.168.1.1");
        refundReq.setRefundReason("qa测试退款");
        refundReq.setSourceCode(OperationSourceCode.MTMERCHANTBACKEND);
        refundReq.setSourceName("商家后台");
        RefundOrderResp refundOrderResp = refundOrderBaseService.refund(refundReq);
        log.info("商家退款获取到的状态："+refundOrderResp.getResultDesc());
    }
//客服直接退款
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
        MisRefundResp misAgreeRefundResp = misRefundOrderBaseService.refund(misRefundReq);
        System.out.println(misAgreeRefundResp.toString());
    }
}
