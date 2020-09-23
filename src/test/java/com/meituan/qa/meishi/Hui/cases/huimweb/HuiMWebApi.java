package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimeZone;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.maitonApi;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPApp;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.MTApp;
import static com.meituan.qa.meishi.Hui.util.CheckOrderUtil.getHuiOrderDetailVo;

@Slf4j
public class HuiMWebApi {
    static String merchantDtailUrl = "/hui/orderdetail";//商家订单详情页接口
    static String unicashierCouponDescUrl = "/hui/maiton/couponDesc"; //美团APP优惠详情页
    static String ajaxCashierqueryUrl = "/hui/ajax/cashierquery"; //买单收银页按门店活动查询
    static String ajaxCashieroverviewUrl = "/hui/ajax/cashieroverview";//买单收银页总览
    /**
     * pc端商家订单详情
     * 例：https://hui-e.51ping.com/hui/orderdetail?serializedId=HGKPET1Z6RZUB3AND&poiId=97224769
     */
    @LoopCheck(desc = "获取商家订单详情轮训", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public JSONObject getMerchentOrderDetail(String caseId) {
        maitonApi.replaceUserInfo(DPApp);
        String merchantBsid = maitonApi.getUserModel().get().getMerchantBsid();
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(merchantDtailUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + merchantBsid);
        log.info("商家订单详情查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.merchant.host", request);
        JSONObject jsonObject = getHuiOrderDetailVo(responseMap.getResponseBody());
        if(jsonObject == null){
            return null;
        }
        return jsonObject;
    }
    /**
     * 美团APP端优惠详情页
     * 例：http://m.51ping.com/hui/maiton/couponDesc?shopId=1450835
     * */
    @LoopCheck(desc = "美团APP端优惠详情页轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap unicashierCouponDesc(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(unicashierCouponDescUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","token" + maitonApi.getUserModel().get().getToken());
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","mt_token" + maitonApi.getUserModel().get().getToken());

        log.info("美团APP端优惠详情页查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);

        return responseMap;
    }
    /**
     * 买单收银页-按门店活动查询
     * 例：https://hui-e.51ping.com/hui/ajax/cashierquery
     * */
    @LoopCheck(desc = "买单收银页按门店活动查询轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxCashierquery(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxCashierqueryUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        request.getJSONObject("body").put("beginTime", getBeginTimeDate());
        request.getJSONObject("body").put("endTime", getEndTimeDate());

        log.info("买单收银页按门店活动查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }
    /**
     * 买单收银页-总览
     * 例：https://hui-e.51ping.com/hui/ajax/cashieroverview
     * */
    @LoopCheck(desc = "买单收银页总览轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxCashieroverview(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxCashieroverviewUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        request.getJSONObject("body").put("beginTime", getBeginTimeDate());
        request.getJSONObject("body").put("endTime", getEndTimeDate());

        log.info("买单收银页总览入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }
    /**获取当前时间所在的0点时段
     * @return
     */
    public Date getBeginTimeDate(){
        long current = System.currentTimeMillis();
        long zeroTime = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
        Date date = new Date();
        date.setTime(zeroTime);
        return date;
    }
    public Date getEndTimeDate(){
        long current = System.currentTimeMillis();
        long endTime = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset() + 86400000;
        Date date = new Date();
        date.setTime(endTime);
        return date;
    }
}
