package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.sankuai.nibqa.trade.api.dto.ValidResponse;
import com.sankuai.nibqa.trade.api.dto.VerifyDataRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public class PlatformCheckInfo  extends TestDPLogin {
    int flag;
    Long orderid;
    JSONObject verifyRequest;
    ValidResponse validResponse;
    String buyer;

    /**
     * 履约有三步：创建、发货、核销
     * paysuccess是校验创建，verifyPayAndDeliverySuccess校验发货，verifyCheckSuccess校验核销
     **/

    public  ValidResponse  PlatformCheckInfo() {
        VerifyDataRequest verifyDataRequest = new VerifyDataRequest();
        verifyDataRequest = JSONObject.parseObject(verifyRequest.toString(), VerifyDataRequest.class);
        switch (flag) {
            case 1:
                validResponse = tradeVerifyService.verifyCreateSuccess(orderid, verifyDataRequest);
                break;
            case 2:
                validResponse = tradeVerifyService.verifyPayAndCheckSuccess(orderid, verifyDataRequest);
                //validResponse = tradeVerifyService.verifyPaySuccess(orderid, verifyDataRequest);
                //validResponse = tradeVerifyService.verifyPayAndDeliverySuccess(orderid, verifyDataRequest);
                break;
            case 3:
                validResponse = tradeVerifyService.verifyCheckSuccess(orderid, verifyDataRequest);
                break;
            case 4:
                verifyDataRequest.setBuyerId(buyer);
                validResponse = tradeVerifyService.verifyCheckedRefundSuccess(orderid, verifyDataRequest);
                break;
            default:
                log.info("买单侧没有进行平台校验请知悉");
                break;
            }
        return validResponse;
    }
}
