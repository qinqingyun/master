package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderMResult;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.util.Strings;

import java.math.BigDecimal;
import java.net.URLEncoder;

@Builder
@Data
@Slf4j
public class HuiCreateOrder {
    String token;
    String userAgent;
    String caseid;

    CouponProduct couponProduct;
    DeskCoupon deskcoupon;

    public HuiCreateOrderResult requestCreate() {
        Assert.assertNotNull(token);
        Assert.assertNotNull(userAgent);
        Assert.assertNotNull(caseid);
        HuiCreateOrderResult result = new HuiCreateOrderResult();
        String _APIPATH = "hui/unicashiercreateorder.bin";
        try {
            JSONObject request = DBDataProvider.getRequest(_APIPATH, caseid);
            request.getJSONObject("headers").put("pragma-token", token);
            request.getJSONObject("headers").put("pragma-newtoken", token);
            request.getJSONObject("headers").put("User-Agent", userAgent);
            request.getJSONObject("headers").put("pragma-os", userAgent);

            JSONObject body = request.getJSONObject("body");

            if (couponProduct != null) {
                String offerIdStr = "{\"dealGroupId\":0,\"dealId\":0,\"needBuyDealCount\":0,\"useDealCount\":0,\"couponList\":[{\"productType\":" + couponProduct.getProductType() + ",\"couponId\":" + couponProduct.getCouponID() + ",\"ticketId\":\"0\"}]}";
                // todo 先不使用方案优惠
//                body.put("shopdealstring", offerIdStr);
            }

            if (deskcoupon != null) {
                if (!Strings.isNullOrEmpty(deskcoupon.getCipher())) {
                    body.put("dpdealstring", URLEncoder.encode(deskcoupon.getCipher(), "utf-8"));
                }
                BigDecimal couponAmount = BigDecimal.valueOf(deskcoupon.getAmount());
                if (BigDecimal.ZERO.compareTo(couponAmount) < 0) {
                    BigDecimal userAmount = body.getBigDecimal("originamount").subtract(couponAmount);
                    if(userAmount.compareTo(BigDecimal.ZERO) == 0){
                        body.put("useramount", "0");
                    }else {
                        body.put("useramount", String.valueOf(userAmount));
                    }

                }
            }
            log.info("request:{}" ,request);
            ResponseMap response = null;
            try {
                response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
            }catch (Exception e){
                log.error("下单接口请求失败，异常为：{}",e.getMessage());
                return null;
            }
            if(response.getStatusCode() != 200){
                return null;
            }
            String payToken = response.getValueByJsonPath("$.PayToken");
            String tradeNo = response.getValueByJsonPath("$.Tradeno");
            Long orderId = response.getValueByJsonPath("$.OrderId");
            String serializedId = response.getValueByJsonPath("$.SerializedId");
            if (tradeNo == null) {
                log.info("创单失败");
                return null;
            }
            result.setSerializedId(serializedId);
            result.setOrderId(orderId);
            result.setTradeNo(tradeNo);
            result.setPayToken(payToken);
        } catch (Exception e) {
            log.error("request create order error", e);
            return null;
        }
        return result;
    }

    public HuiCreateOrderMResult requestMCreate() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        Assert.assertNotNull(token);
        Assert.assertNotNull(userAgent);
        Assert.assertNotNull(caseid);
        String _MAPIPATH = "hui/cashier/ajaxcreateorder";
        String cookie = "dper=" + token;
        JSONObject mRequest = DBDataProvider.getRequest(_MAPIPATH, caseid);
        mRequest.getJSONObject("headers").put("pragma-token", token);
        mRequest.getJSONObject("headers").put("pragma-newtoken", token);
        mRequest.getJSONObject("headers").put("User-Agent", userAgent);
        mRequest.getJSONObject("headers").put("Cookie", cookie);
        ResponseMap response = DBCaseRequestUtil.post("env.api.51ping.host", mRequest);
        String responseBody = response.getResponseBody();
        HuiCreateOrderMResult mResult = JSON.parseObject(responseBody, HuiCreateOrderMResult.class);
        if (mResult == null) {
            Assert.fail("创单失败");
        }
        Assert.assertNotNull(mResult.getPayToken());
        Assert.assertNotNull(mResult.getTradeNo());
        Assert.assertNotNull(mResult.getPayOrderID());
        String url= mResult.getSuccessURL();
        String orderid= url.substring(url.indexOf('=')+1,url.indexOf('&'));
        mResult.setPayOrderID(Long.valueOf(orderid));
        return mResult;
    }
}
