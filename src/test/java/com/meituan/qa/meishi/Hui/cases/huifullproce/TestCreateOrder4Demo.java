package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ClassAnnotation(author = "liukang",depart = "C", des="提供给商家演示账号下单使用case")
@Slf4j
public class TestCreateOrder4Demo {
    public static String mtClient = "MApi 1.1 (com.sankuai.meituan 9.0 meituaninternaltest MI_3; Android 4.4.4)";
    String mtToken = CommonLoginUtil.ms_c_MTLogin_01("ms_c_MTLogin4Demo_01");
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> orderCreateResult = new ArrayList();

    @Test(groups = "P3",dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-07-10",updateTime = "2018-07-10",des =
            "普通下单(满减)")
    public void ms_c_createOrder4Demo_101(JSONObject request, JSONObject expect) throws Exception {
        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken,mtClient,"ms_c_createOrder4Demo_101");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,mtToken,"201806");

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken,mtClient,serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");
    }

    @Test(groups = "P3",dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-07-10",updateTime = "2018-07-10",des =
            "普通下单(折扣)")
    public void ms_c_createOrder4Demo_102(JSONObject request, JSONObject expect) throws Exception {

        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken,mtClient,"ms_c_createOrder4Demo_102");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,mtToken,"201806");

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken,mtClient,serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");
    }

    @Test(groups = "P3",dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-07-10",updateTime = "2018-07-10",des =
            "普通下单(原价)")
    public void ms_c_createOrder4Demo_103(JSONObject request, JSONObject expect) throws Exception {

        orderCreateResult = CreateOrderUtil.uniCashierCreateOrder(mtToken,mtClient,"ms_c_createOrder4Demo_103");
        payToken = orderCreateResult.get(0);
        tradeNo = orderCreateResult.get(1);
        orderId = orderCreateResult.get(2);
        serializedId = orderCreateResult.get(3);
        log.info("创单成功！orderId = " + orderId +" serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

        //3、支付
        CreateOrderUtil.orderPay(payToken,tradeNo,mtToken,"201806");

        //4、支付结果页
        try {
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        CreateOrderUtil.queryMopayStatus(mtToken,mtClient,serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
        log.info("获取到订单支付结果");
    }
}
