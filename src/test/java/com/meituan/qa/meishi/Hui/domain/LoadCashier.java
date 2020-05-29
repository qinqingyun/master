package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.dto.cashier.MaitonCashier;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Data
@Builder
public class LoadCashier {

    String token;
    String userAgent;
    String caseId;

    //app加载优惠台
    public Optional<CouponProduct> parseCouponOfferId() {
        String _APIPATH = "hui/loadunifiedcashier.bin";
        JSONObject request = DBDataProvider.getRequest(_APIPATH, caseId);
        //JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
        request.getJSONObject("headers").put("pragma-token", token);
        request.getJSONObject("headers").put("User-Agent", userAgent);
        request.getJSONObject("headers").put("pragma-os", userAgent);
        request.getJSONObject("headers").put("pragma-dpid", "-8765947759983332911");
        ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        log.info("loadunifiedcashier:code:{}, body:{}", response.getStatusCode(), response.getResponseBody());
        MaitonCashier maitonCashier = JSON.parseObject(response.getResponseBody(), MaitonCashier.class);
        if (maitonCashier == null) {
            return Optional.empty();
        }
        log.info("lcashier:{}", maitonCashier.getCouponProducts().stream().findFirst());
        return maitonCashier.getCouponProducts().stream().findFirst();
    }
}
