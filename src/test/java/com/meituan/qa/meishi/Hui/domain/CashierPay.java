package com.meituan.qa.meishi.Hui.domain;

import com.api.PayApi;
import com.meituan.toolchain.mario.config.ConfigMange;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

@Builder
@Data
@Slf4j
public class CashierPay {

    String payToken;
    String tradeNo;
    String token;
    String passWord;

    //支付
    public boolean orderPay() throws InterruptedException {
        String payHost = ConfigMange.getValue("env.api.meishi.hui.pay");
        PayApi obj = new PayApi(payHost);
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "2");  //type {0:绑卡，1:余额} 建议用余额支付
        params.put("tradeno", tradeNo);
        params.put("pay_token", payToken);
        //params.put("pay_password", passWord); //设置支付密码http://payc.fsp.test.sankuai.com/rstpwd/index.htm
        params.put("token", token); //获取token http://payc.fsp.test.sankuai.com/user/index.htm 或者 http://admin-user.wpt.test.sankuai.com/service/normal 或参考下面代码调用用户中心接口动态获取
        //params.put("nb_app", "...");//非必传，默认值是group
//        try {
//            boolean ret = obj.doPayNew(params);   //支付成功会返回true
//            log.info("支付状态:{}", ret);
//            return ret;
//        } catch (Exception e) {
//            log.info("支付异常", e);
//        }
        Boolean payRequest = false;
        for (int i = 0; i < 3; i++){
            try {
                payRequest = obj.doPayNew(params);   //支付成功会返回true
                log.info("支付状态： " + payRequest);
                //Assert.assertEquals(payRequest,true);
            } catch (Exception e) {
                log.error("支付异常：",e.getMessage());
                if(e.getMessage().contains("cashier_payorder_already_payed")){
                    payRequest = TRUE;
                    break;
                }
                log.error("第"+(i + 1)+"次支付失败",e);
            }
            if(payRequest == TRUE)
                break;
            Thread.sleep(500);
        }
        return false;
    }

}
