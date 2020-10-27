package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimeZone;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.maitonApi;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.*;
import static com.meituan.qa.meishi.Hui.util.CheckOrderUtil.getHuiOrderDetailVo;

@Slf4j
public class HuiMWebApi {
    static String merchantDtailUrl = "/hui/orderdetail";//商家订单详情页接口
    static String unicashierCouponDescUrl = "/hui/maiton/couponDesc"; //美团APP优惠详情页
    static String ajaxCashierqueryUrl = "/hui/ajax/cashierquery"; //买单收银页按门店活动查询
    static String ajaxCashieroverviewUrl = "/hui/ajax/cashieroverview";//买单收银页总览
    static String ajaxCashierquerybyorderidUrl = "/hui/ajax/cashierquerybyorderid";//买单收银页订单查询
    static String ajaxOrderqueryUrl = "/hui/ajax/orderquery";//订单查询页
    static String ajaxOrderovervieUrl = "/hui/ajax/orderoverview";//全量订单查询总览
    static String ajaxApplyrefundlistqueryUrl = "/hui/ajax/applyrefundlistquery";//退款待办列表查询
    // mm
    static String mmWxaPoiUrl = "/hui/mm/wxapoi";  //点评m站进入POI页面，加载POI页门店优惠信息
    static String mmShopUrl = "/hui/mm/shop";      //点评mm站提单页入口/跳转页面
    static String mmCashierUrl = "/hui/mm/cashier";      //点评mm站提单页入口
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
    /**
     * 买单收银页-订单查询
     * 例：https://hui-e.51ping.com/hui/ajax/cashierquerybyorderid
     * */
    @LoopCheck(desc = "买单收银页订单查询轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxCashierquerybyorderid(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxCashierquerybyorderidUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        log.info("买单收银页订单查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }
    /**
     * 订单查询页-订单查询
     * 例：https://hui-e.51ping.com/hui/ajax/orderquery
     * */
    @LoopCheck(desc = "订单查询页订单查询轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxOrderquery(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxOrderqueryUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        request.getJSONObject("body").put("beginTime", getBeginTimeDate());
        request.getJSONObject("body").put("endTime", getEndTimeDate());
        log.info("订单查询页订单查询轮询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }
    /**
     * 全量订单查询-总览
     * 例：https://hui-e.51ping.com/hui/ajax/orderoverview
     * */
    @LoopCheck(desc = "全量订单查询总览轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxOrderovervie(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxOrderovervieUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        request.getJSONObject("body").put("beginTime", getBeginTimeDate());
        request.getJSONObject("body").put("endTime", getEndTimeDate());

        log.info("全量订单查询总览入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }
    /**
     * 退款待办列表查询
     * 例：https://hui-e.51ping.com/hui/ajax/applyrefundlistquery
     * */
    @LoopCheck(desc = "退款待办列表查询轮询", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap ajaxApplyrefundlistquery(String caseId) {
        maitonApi.replaceUserInfo(MTApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(ajaxApplyrefundlistqueryUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + maitonApi.getUserModel().get().getMerchantBsid());
        log.info("退款待办列表查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
        return responseMap;
    }

    /**
     * 点评m站进入POI页面，加载POI页门店优惠信息
     * 例子：[test]http://mm.51ping.com/hui/mm/wxapoi?shopId=24799161
     *      [prod]http://mm.dianping.com/hui/mm/wxapoi?shopId=24799161
     */
    @LoopCheck(desc="美团小程序收银台页/点评侧POI页面",interval = 500,timeout = 1000 * 10)
    public ResponseMap mmWxaPoi(String caseId){
        maitonApi.replaceUserInfo(DPWx);
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(mmWxaPoiUrl,caseId);
            request.getJSONObject("headers").put("param-token",maitonApi.getDpToken());
            request.getJSONObject("headers").put("user-agent",maitonApi.dpWxClient);
        }catch (Exception e){
            log.error("获取/hui/mm/wxapoi的请求参数时发生异常：",e.getMessage());
        }

        log.info("{点评m站}加载POI页门店优惠信息-请求参数,{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.mm51ping.host",request);
        return responseMap;

    }

    /**
     * 点评mm站提单页面入口
     * 例：http://mm.51ping.com/hui/mm/shop?shopId=66526423
     * */
    @LoopCheck(desc = "点评mm站提单页面入口/跳转页面", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap mmShop(String caseId, OrderSourceEnum source, String access) {
        maitonApi.replaceUserInfo(source);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(mmShopUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        request.getJSONObject("headers").put("param-token",maitonApi.getDpToken());
        request.getJSONObject("headers").put("user-agent",access);
        log.info("点评mm站提单页面入口-入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.mm51ping.host", request);
        return responseMap;
    }

    /**
     * 点评mm站提单页面/收银页面
     * 例：http://mm.51ping.com/hui/mm/cashier?shopType=0&shopId=24799161&amountlocked=0
     * */
    @LoopCheck(desc = "点评mm站提单页面入口/跳转页面", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap mmCashier(String caseId) {
        maitonApi.replaceUserInfo(DPApp);
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(mmCashierUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        request.getJSONObject("headers").put("param-token",maitonApi.getDpToken());
        request.getJSONObject("headers").put("user-agent",maitonApi.getDpWxClient());
        log.info("点评mm站提单页面/收银页面-入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.mm51ping.host", request);
        return responseMap;
    }
    /**
     * 买单收银页-无筛选条件
     * 例：https://hui-e.51ping.com/hui/cashierquery
     * */
    @LoopCheck(desc = "买单收银页-无筛选条件", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap cashierquery(String caseId) {
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
     * 成功返回结果的常规校验
     * 例子：{"code":0,"msg": "success"}
     */
    public void commonAssertSuccess(String successRes){
        JSONObject responseJSON = JSONObject.parseObject(successRes);
        AssertUtil.assertEquals(responseJSON.get("code"),0);
        AssertUtil.assertEquals(responseJSON.get("msg"),"success");
    }

}
