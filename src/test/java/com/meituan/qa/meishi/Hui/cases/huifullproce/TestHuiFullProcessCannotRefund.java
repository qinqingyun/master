package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.OrderRefundUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ClassAnnotation(author = "liukang",depart = "C", des="商家流水不足")
@Slf4j
public class TestHuiFullProcessCannotRefund extends TestDPLogin {
    private String payToken;
    private String tradeNo;
    private String orderId;
    private String serializedId;
    private String bsid;//商家端登录后获取
    private String ssoid;//客服登录后获取
    List<String> orderCreateResult = new ArrayList();
    @Test(groups = "P3",dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-18",updateTime = "2018-05-18",des =
            "普通下单，商家流水不足")
    public void ms_c_HuiFullProcessCannotRefund_01() throws Exception {
        //1、加载优惠台
        CreateOrderUtil.loadUnifiedCashier(mtToken,mtClient,  "ms_c_4Verify_loadUnifiedCashier_01");
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
     /*   //5、等待24小时后申请退款
        try {
            TimeUnit.HOURS.sleep(24);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        */
        bsid = CommonLoginUtil.merchantAPPLogin();
        log.info("获取到商家后台bsid：" + bsid);
        //5、商家直接进行退款操作({"code":505,"msg":"由于账户今日流水不足，暂无法退款，请联系在线客服或拨打商服电话10105557。"})
        merchantOrderRefund();
        log.info("商家直接退款流水不足");

        //6、客服提交退款申请
        misRefundCheck();//校验未通过订单进入待抵扣列表
        log.info("客服退款流水不足，订单进入待抵扣列表");

        //7、用户申请退款
        OrderRefundUtil.orderRefundMT(mtToken,serializedId,"ms_c_huiFullProcess_101_orderRefund");
        log.info("用户已申请退款");

        //8、商家同意退款
        merchantAgreeRefund();
        log.info("商家同意退款流水不足");

        //9、客服同意退款，试了一下还是refundcheck接口
        misRefundCheck();//校验未通过订单进入待抵扣列表
        log.info("客服退款流水不足，订单进入待抵扣列表");
    }

    //商家直接进行退款
    public void merchantOrderRefund(){
        ResponseMap response = OrderRefundUtil.merchantOrderRefund(bsid,serializedId);
        Assert.assertTrue("由于账户今日流水不足，暂无法退款，请联系在线客服或拨打商服电话10105557。".equals(response.getValueByJsonPath("$.msg")));

    }

    //客服提交退款申请
    public void misRefundCheck(){
        ResponseMap response = OrderRefundUtil.misRefundCheck(ssoid,serializedId);
        Assert.assertTrue("校验未通过，进行拦截".equals(response.getValueByJsonPath("$.msg")));
    }

    //商家同意退款
    public void merchantAgreeRefund(){
        ResponseMap response = OrderRefundUtil.merchantAgreeRefund(bsid,serializedId);
        Assert.assertTrue("由于账户今日流水不足，暂无法退款，请联系在线客服或拨打商服电话10105557。".equals(response.getValueByJsonPath("$.msg")));
    }

}
