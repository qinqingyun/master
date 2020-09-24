package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.PromoResponse;
import com.meituan.qa.meishi.Hui.dto.UseCard;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Builder
@Data
public class HuiPromoDesk {

    private static String _APIPATH = "/pay/huipromo/v1/gethuipromodesk.pay";

    private String mttoken;

    private int useCardflag;
    private String client;
    private String caseid;

    public Optional<DeskCoupon> shopCouponCipher(String couponId) {

        JSONObject request = DBDataProvider.getRequest(_APIPATH, caseid);
//        JSONPath.remove(request, "$.headers.isMicroMessenger");
//        JSONPath.remove(request, "$.headers.platform");
//        JSONPath.remove(request, "$.headers.appVersion");
//        JSONPath.remove(request, "$.headers.appName");
        //JsonPathUtil.setJsonPathVaule(request, "$.headers.isMicroMessenger", "False");
        request.getJSONObject("headers").put("pragma-newtoken", mttoken);
        request.getJSONObject("headers").put("pragma-token", mttoken);
        request.getJSONObject("headers").put("user-agent", client);
        request.getJSONObject("headers").put("pragma-os", client);
        JsonPathUtil.setJsonPathVaule(request, "$.params.token", mttoken);
        log.info("查询商家券信息headers======"+request.getJSONObject("headers").toString());
        log.info("查询商家券信息request======"+request.toString());
        ResponseMap response = null;
        PromoResponse promoResponse = new PromoResponse();
        JSONObject responseJson = new JSONObject();
        for(int i = 0;i < 3;i++){
            response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
            responseJson = JSONObject.parseObject(response.getResponseBody());
            log.info("查询商家券信息response====== {}", response.getResponseBody());
            if(responseJson == null){
                continue;
            }
            promoResponse = JSON.parseObject(responseJson.getString("PromoDeskRule"), PromoResponse.class);
            if (promoResponse != null) {
                break;
            }
        }
        if (responseJson == null) {
            return Optional.empty();
        }
        promoResponse = JSON.parseObject(responseJson.getString("PromoDeskRule"), PromoResponse.class);
        if (promoResponse == null) {
            return Optional.empty();
        }

        /*平台券*/
        if (useCardflag == UseCard.USE_PLATFORM_CARD) {
            return promoResponse.getCoupon().getCoupons().stream()
//          .filter(x-> couponId.equals(x.getId()))
            .findFirst();
        }
        /*商家券*/
        if (useCardflag == UseCard.USE_MERCHANT_CARD) {
            return promoResponse.getShopcoupon().getCoupons().stream()
//           .filter(x-> couponId.equals(x.getId()))
            .findFirst();

        }
        return Optional.empty();
    }
}
