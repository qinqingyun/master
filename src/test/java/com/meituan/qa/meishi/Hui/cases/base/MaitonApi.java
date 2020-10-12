package com.meituan.qa.meishi.Hui.cases.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.PayApi;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.domain.HuiPromoDesk;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderMResult;
import com.meituan.qa.meishi.Hui.dto.OrderDetailCheck;
import com.meituan.qa.meishi.Hui.dto.UseCard;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.dto.cashier.MaitonCashier;
import com.meituan.qa.meishi.Hui.entity.LoginEnum;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.qa.meishi.Hui.util.EPassportUtil;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.DPCUser;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import com.meituan.toolchain.mario.util.MtraceUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.util.Strings;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.thriftApi;
import static java.lang.Boolean.TRUE;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
@Data
public class MaitonApi {
    public String envpath;
    private String dpToken = "";
    public String dpUserId = "";
    private static String mtToken = "";
    public String mtUserId = "";
    public String merchantBsid = "";
    private  String username = "maitonuser";
    public  String dpWxClient = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.8(0x17000820) NetType/WIFI Language/zh_CN";
    public  String mtClientNew = "MApi 1.1 (mtscope 10.1.400 appstore; iPhone 11.3.1 iPhone10,3; a0d0)";
    public  String dpClient = "MApi 1.1(dpscope 10.16.0 appstore; iPhone 12.3.2 iPhone10,2; a0d0)";
    public  String mClient="Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
    ThreadLocal<UserModel> userModel = new ThreadLocal<>();
    private LoginEnum doubleWriteModel;
    Map<String, UserModel> userModelMap = new ConcurrentHashMap();
    //OrderModel orderModel = new OrderModel();

    //Loopcheck重试次数和时间
    static final int interval = 1000;
    static final int timeout = 10000;

    //App侧相关接口
    static String appCreateOrderUrl = "hui/unicashiercreateorder.bin";  //双侧下单接口
    static String loadunifiedcashierUrl = "hui/loadunifiedcashier.bin"; //双侧加载收银台
    static String queryMopayStatusUrl = "hui/querymopaystatus.bin";  //双侧支付结果接口
    static String mtOrderDetailUrl = "/maiton/order/{orderid}";  //美团订单详情接口
    static String dpOrderDetailUrl = "/hui/maiton/order";//点评订单详情接口
    static String merchantDtailUrl = "/hui/orderdetail";//商家订单详情页接口
    static String mCreateOrderUrl = "hui/cashier/ajaxcreateorder";//M站下单接口


    @LoopCheck(desc = "登录接口", interval = interval, timeout = timeout)
    public String userLogin(String username) {
        envpath = ConfigMange.getValue("testData");
        DPCUser dpcUser = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, username);
        if (dpcUser == null || StringUtil.isBlank(dpcUser.getToken()))
            return null;
        dpToken = dpcUser.getToken();
        switch (username){
            case "maitonuseronlynew":
                dpUserId = ConfigMange.getValue("maitonuseronlynew_DP_C_USER_ID");
                break;
            case "maitonuser":
                dpUserId = ConfigMange.getValue("maitonuser_DP_C_USER_ID");
                break;
            case "maitonuseroldmain":
                dpUserId = ConfigMange.getValue("maitonuseroldmain_DP_C_USER_ID");
                break;
        }

        MTCUser mtUser = (MTCUser) LoginUtil.login(LoginType.MT_C_LOGIN, username);
        if (mtUser == null || StringUtil.isBlank(mtUser.getToken()))
            return null;
        mtToken = mtUser.getToken();
        mtUserId = String.valueOf(mtUser.getId());
        return "登录成功";
    }

    /**
     * 商家登录
     * 获取商家登录token，即bisd
     */
    public String merchantLogin() {
        try {
            merchantBsid = EPassportUtil.getEPassportToken(111703267);
        } catch (Exception e) {
        }
        return merchantBsid;
    }
    //替换token
    public void replaceUserInfo2(LoginEnum loginEnum) {

        UserModel userModel = new UserModel();
        userModel.setToken(mtToken);
        userModel.setUserAgent(mtClientNew);
        userModel.setUserId(mtUserId);
        userModel.setMerchantBsid(merchantBsid);
        userModelMap.put(loginEnum.getText() + "_" + OrderSourceEnum.MTApp.getText(), userModel);

        UserModel dpUserModel = new UserModel();
        dpUserModel.setToken(dpToken);
        dpUserModel.setUserId(dpUserId);
        dpUserModel.setUserAgent(dpClient);
        dpUserModel.setMerchantBsid(merchantBsid);
        userModelMap.put(loginEnum.getText() + "_" + OrderSourceEnum.DPApp.getText(), dpUserModel);
    }
    public void setCurrentUserModel(String main) {
        this.doubleWriteModel = LoginEnum.getByName(main);
    }
    public void replaceUserInfo(OrderSourceEnum sourceEnum){
        UserModel userModel = userModelMap.get(doubleWriteModel.getText() + "_" + sourceEnum.getText());
        this.userModel.set(userModel);
    }

    /**
     * 订单支付，调取收银台，使用支付宝支付
     * PayApi obj = new PayApi("stable.pay.test.sankuai.com");//美团测试
     * PayApi obj = new PayApi("stable.pay.st.sankuai.com");//美团测试
     * PayApi obj = new PayApi("cashier.qa.pay.test.sankuai.com");//点评侧域名
     */
    public void orderPay(OrderModel orderModel) throws Exception {
        String payHost = ConfigMange.getValue("env.api.meishi.hui.pay");
        PayApi obj = new PayApi(payHost);
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "2");  //type {0:绑卡，1:余额 2.支付宝} 建议用余额支付
        params.put("tradeno", orderModel.getTradeNo());
        params.put("pay_token", orderModel.getPayToken());
        //params.put("pay_password", passWord); //设置支付密码http://payc.fsp.test.sankuai.com/rstpwd/index.htm
        params.put("token", userModel.get().getToken()); //获取token http://payc.fsp.test.sankuai.com/user/index.htm 或者 http://admin-user.wpt.test.sankuai.com/service/normal 或参考下面代码调用用户中心接口动态获取
        //params.put("nb_app", "...");//非必传，默认值是group
        Boolean payRequest = false;
        for (int i = 0; i < 3; i++) {
            try {
                payRequest = obj.doPayNew(params);   //支付成功会返回true
                log.info("支付状态： " + payRequest);
                //Assert.assertEquals(payRequest,true);
            } catch (Exception e) {
                log.error("支付异常：", e.getMessage());
                if (e.getMessage().contains("cashier_payorder_already_payed")) {
                    payRequest = TRUE;
                    break;
                }
                log.error("第" + (i + 1) + "次支付失败", e);
            }
            if (payRequest == TRUE)
                break;
            Thread.sleep(500);
        }
        Assert.assertEquals(payRequest, Boolean.TRUE, "重试3次仍支付失败");
        Thread.sleep(1000);
    }

    /**
     * APP下单接口，接口文档：
     * 美团点评根据不同token和useragent区分平台
     */
    //无需加载优惠平台接口
    public OrderModel uniCashierCreateOrder(String caseId) throws UnsupportedEncodingException {
        return uniCashierCreateOrder(userModel.get().getToken(),userModel.get().getUserAgent(),caseId,null,null,null,0);
    }

    //需要加载优惠平台接口，下单使用买单优惠
    public OrderModel uniCashierCreateOrder(String caseId,CouponProduct couponProduct) throws UnsupportedEncodingException {
        return uniCashierCreateOrder(userModel.get().getToken(),userModel.get().getUserAgent(),caseId,couponProduct,null,null,0);
    }

    //需要加载优惠平台接口，下单使用优惠券场景
    public OrderModel uniCashierCreateOrder(String caseId, CouponProduct couponProduct, DeskCoupon deskcoupon,Integer isZero) throws UnsupportedEncodingException {
        return uniCashierCreateOrder(userModel.get().getToken(), userModel.get().getUserAgent(), caseId, couponProduct, deskcoupon, null, isZero);
    }

    //无需加载优惠平台接口,下单使用预订金
    public OrderModel uniCashierCreateOrder(String caseId, String resvOrderId) throws UnsupportedEncodingException {
        return uniCashierCreateOrder(userModel.get().getToken(),userModel.get().getUserAgent(),caseId,null,null,resvOrderId,0);
    }

    //M站下单接口
    public OrderModel mCreateOrder(String caseId) {
        return mCreateOrder(userModel.get().getToken(),userModel.get().getUserAgent(),caseId);
     }

    public OrderModel uniCashierCreateOrder(String token, String userAgent, String caseid, CouponProduct couponProduct, DeskCoupon deskcoupon, String receipt, Integer isZero) throws UnsupportedEncodingException {
        // 生成新Trace
        MtraceUtil.generatTrace("App下单请求");

        String payToken;
        String tradeNo;
        Long orderId;
        String shopdealstring;

        JSONObject request = new JSONObject();

        try {
            request = DBDataProvider.getRequest(appCreateOrderUrl, caseid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        request.getJSONObject("headers").put("pragma-token", token);
        request.getJSONObject("headers").put("pragma-newtoken", token);
        request.getJSONObject("headers").put("User-Agent", userAgent);
        request.getJSONObject("headers").put("pragma-os", userAgent);
        JSONObject body = request.getJSONObject("body");

        Map<String, String> param = new HashMap<>();
        if (couponProduct != null) {
            shopdealstring = "{\"dealGroupId\":0,\"dealId\":0,\"needBuyDealCount\":0,\"useDealCount\":0,\"couponList\":[{\"productType\":" + couponProduct.getProductType() + ",\"couponId\":" + couponProduct.getCouponID() + ",\"ticketId\":\"0\"}]}";
            param.put("shopdealstring", shopdealstring);
            request.getJSONObject("body").put("shopdealstring", shopdealstring);
        }
        if (deskcoupon != null) {
            if (!Strings.isNullOrEmpty(deskcoupon.getCipher())) {
                body.put("dpdealstring", URLEncoder.encode(deskcoupon.getCipher(), "utf-8"));
            }
            if (isZero == 1) {
                body.put("useramount", "0");
            } else {
                BigDecimal couponAmount = BigDecimal.valueOf(deskcoupon.getAmount());
                if (BigDecimal.ZERO.compareTo(couponAmount) < 0) {
                    BigDecimal userAmount = body.getBigDecimal("originamount").subtract(couponAmount);
                    if (userAmount.compareTo(BigDecimal.ZERO) == 0) {
                        body.put("useramount", "0");
                    } else {
                        body.put("useramount", String.valueOf(userAmount));
                    }

                }
            }
        }
        if (receipt != null) {
            request.getJSONObject("body").put("bizorderid", receipt);
            request.getJSONObject("body").put("bookrecordid", receipt);
        }
        log.info("买单创建订单请求参数request:{}", request.toString());
        ResponseMap response = null;
        try {
            response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
            log.info("买单创建订单结果返回response:{}", response.toString());
        } catch (Exception e) {
            log.error("下单接口请求失败，异常为：{}", e.getMessage());
            return null;
        }
        if (response.getStatusCode() != 200) {
            return null;
        }
        try {
            payToken = response.getValueByJsonPath("$.PayToken");
            tradeNo = response.getValueByJsonPath("$.Tradeno");
            orderId = response.getValueByJsonPath("$.OrderId");
        } catch (Exception e) {
            log.info("有异常创单失败");
            return null;
        }
        //如果是预定金0元单，tradeNo==null
        if (receipt == null) {
            if (tradeNo == null || payToken == null) {
                log.info("创单失败：tradeNo为null或者payToken为null");
                return null;
            }
        }
        return setOrderModel(orderId.toString(), payToken, tradeNo);
    }

    public OrderModel setOrderModel(String orderId, String payToken, String tradeNo) {
        OrderModel orderModel = new OrderModel();
        QueryOrderResponse maidonOrder = thriftApi.getMaidonOrder(orderId);
        BigDecimal originAmount = maidonOrder.getOrderDTO().getOriginAmount().multiply(new BigDecimal(100));
        BigDecimal payAmount = maidonOrder.getOrderDTO().getUserAmount().multiply(new BigDecimal(100));
        BigDecimal platformAmount = maidonOrder.getOrderDTO().getPlatformAmount().multiply(new BigDecimal(100));
        BigDecimal merchantAmount = maidonOrder.getOrderDTO().getMerchantDiscountAmount().multiply(new BigDecimal(100));
        BigDecimal discountAmount = maidonOrder.getOrderDTO().getDiscountAmount().multiply(new BigDecimal(100));
        BigDecimal allPromoAmount = platformAmount.add(merchantAmount).add(discountAmount);
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

        orderModel.setOriginAmount(decimalFormat.format(Double.valueOf(originAmount.toString())));
        orderModel.setPayAmount(decimalFormat.format(Double.valueOf(payAmount.toString())));
        orderModel.setPlatformAmount(decimalFormat.format(Double.valueOf(platformAmount.toString())));
        orderModel.setMerchantAmount(decimalFormat.format(Double.valueOf(merchantAmount.toString())));
        orderModel.setDiscountAmount(decimalFormat.format(Double.valueOf(discountAmount.toString())));
        orderModel.setPromoAmount(decimalFormat.format(Double.valueOf(allPromoAmount.toString())));
        orderModel.setSchemeId(String.valueOf(maidonOrder.getOrderDTO().getSchemeId()));
        orderModel.setOrderId(orderId);
        orderModel.setPayToken(payToken);
        orderModel.setTradeNo(tradeNo);
        orderModel.setSerializedId(String.valueOf(maidonOrder.getOrderDTO().getSerializedId()));
        orderModel.setMtShopId(String.valueOf(maidonOrder.getOrderDTO().getMtShopId()));
        return orderModel;
    }

    /**
     * APP加载优惠台接口，接口文档：
     */
    public Optional<CouponProduct> loadUnifiedCashier(String caseId) {
        // 生成新Trace
        MtraceUtil.generatTrace("App加载优惠台");
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(loadunifiedcashierUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        request.getJSONObject("headers").put("pragma-token",userModel.get().getToken());
        request.getJSONObject("headers").put("User-Agent",userModel.get().getUserAgent());
        request.getJSONObject("headers").put("pragma-os",userModel.get().getUserAgent());
        request.getJSONObject("headers").put("pragma-dpid","-8765947759983332911");
        ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        log.info("加载优惠台接口返回loadunifiedcashier:code:{}, body:{}", response.getStatusCode(), response.getResponseBody());
        MaitonCashier maitonCashier = JSON.parseObject(response.getResponseBody(), MaitonCashier.class);
        log.info("优惠台返回结果{}", JSON.toJSONString(maitonCashier));
        if (maitonCashier == null) {
            return Optional.empty();
        }
        log.info("优惠台返回第一个方案信息:{}", maitonCashier.getCouponProducts().stream().findFirst());
        return maitonCashier.getCouponProducts().stream().findFirst();
    }

    /**
     * APP支付结果，接口文档：
     */
    public String queryMopayStatus(String caseId, String serializedId) {
        // 生成新Trace
        MtraceUtil.generatTrace("支付结果查询请求");

        JSONObject request = DBDataProvider.getRequest(queryMopayStatusUrl, caseId);
        request.getJSONObject("headers").put("pragma-token", userModel.get().getToken());
        request.getJSONObject("headers").put("pragma-newtoken", userModel.get().getToken());
        request.getJSONObject("headers").put("User-Agent", userModel.get().getUserAgent());
        request.getJSONObject("headers").put("pragma-os", userModel.get().getUserAgent());
        request.getJSONObject("params").put("serializedid", serializedId);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        JSONObject response = (JSONObject) DBDataProvider.getExpectResponse(queryMopayStatusUrl, caseId);
        if (responseMap == null) {
            log.error("querymopaystatus error:{}", serializedId);
            return null;
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        if (!(jsonObject.getString("StatusMsg")).equals(response.getString("StatusMsg"))) {
            return "";
        }
        return jsonObject.getString("StatusMsg");
    }

    /**
     * 美团侧APP订单详情页，接口文档：
     */
    public String MtOrderDetail(String caseId, String orderId) {
        // 生成新Trace
        MtraceUtil.generatTrace("美团app订单详情页查询");

        ResponseMap responseMap = null;

        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(mtOrderDetailUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        request.getJSONObject("params").put("token",userModel.get().getToken());
        request.put("path",request.getString("path").replaceAll("\\{orderid\\}",orderId));

        responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        String body = responseMap.getResponseBody();
        OrderDetailCheck orderDetailinfo = parseHtml(responseMap.getResponseBody());
        String orderDetailinfoContent = orderDetailinfo.getContent();
        return orderDetailinfoContent;
    }

    /**
     * 点评侧APP订单详情页，接口文档：
     */
    public String DpOrderDetail(String caseId, String orderId) {
        // 生成新Trace
        MtraceUtil.generatTrace("点评app订单详情查询");
        log.info("MTOrderDetail Enter");

        ResponseMap responseMap = null;
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(dpOrderDetailUrl, caseId);
        } catch (Exception e) {
            log.info("DBDataProvider.getRequest调用 excepiton", e);
        }
        request.getJSONObject("params").put("token",userModel.get().getToken());
        request.getJSONObject("params").put("orderId",orderId);
        request.getJSONObject("params").put("product","dpapp");
        long currentTime = System.currentTimeMillis();
        try {
            responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        } catch (Exception e) {
            log.info("查询订单详情Exception, Request:{}, 耗时: {}",
                    JSON.toJSONString(request),
                    System.currentTimeMillis() - currentTime,
                    e);
        }
        String body = responseMap.getResponseBody();
        OrderDetailCheck orderDetailinfo = parseHtml(responseMap.getResponseBody());
        String orderDetailinfoContent = orderDetailinfo.getContent();
        //Assert.assertEquals(orderDetailinfo.getContent(),"支付成功");
        // Assert.assertEquals(orderDetailinfo.getTotalAmount(),"消费金额：1.00元");
        log.info("订单详情页获取状态=======" + orderDetailinfo.getContent() + "原价金额=====" + orderDetailinfo.getTotalAmount() + "用户金额=====" + orderDetailinfo.getPayAmount());
        return orderDetailinfoContent;
    }

    /**
     * html页面解析
     */
    public OrderDetailCheck parseHtml(String html) {
        OrderDetailCheck orderDetailCheck = new OrderDetailCheck();
        Document doc = Jsoup.parse(html);
        String content = doc.select("section[class=st-con st-succ]").get(0).select("div[class=st-wrap]").select("div[class=st-tit]").text();
        String totalAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(0).text();
        String payAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(1).text();
        orderDetailCheck.setContent(content);
        orderDetailCheck.setPayAmount(payAmount);
        orderDetailCheck.setTotalAmount(totalAmount);
        return orderDetailCheck;
    }

    /**
     * 获取用户商家优惠券信息
     */
    public DeskCoupon getShopCouponCipher(String hongbaoid,String caseId){
        HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(userModel.get().getToken()).useCardflag(UseCard.USE_MERCHANT_CARD).client(userModel.get().getUserAgent()).caseid(caseId).build();
        DeskCoupon deskCoupon ;

        try {
            deskCoupon = promoDesk.shopCouponCipher(hongbaoid).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));
        } catch (RuntimeException e) {
            e.getMessage();
            return null;
        }
        return deskCoupon;
    }

    /**
     * 获取用户平台优惠券信息
     */
    public DeskCoupon getPlatformCouponCipher(String hongbaoid,String caseId){
        HuiPromoDesk promoDesk = HuiPromoDesk.builder().mttoken(userModel.get().getToken()).useCardflag(UseCard.USE_PLATFORM_CARD).client(userModel.get().getUserAgent()).caseid(caseId).build();
        DeskCoupon deskCoupon ;
        try {
            deskCoupon = promoDesk.shopCouponCipher(hongbaoid).orElseThrow(() -> new RuntimeException("DeskCoupon not found"));
        } catch (RuntimeException e) {
            e.getMessage();
            return null;
        }
        return deskCoupon;
    }

    /**
     * pc端商家订单详情
     * 例：https://hui-e.51ping.com/hui/orderdetail?serializedId=HGKPET1Z6RZUB3AND&poiId=97224769
     */
    public ResponseMap getMerchentOrderDetail(String caseId, String serializedId, String mtShopId) {
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(merchantDtailUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.params.serializedId",serializedId);
        JsonPathUtil.setJsonPathVaule(request, "$.params.poiId",mtShopId);
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + userModel.get().getMerchantBsid());
        log.info("商家订单详情查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.merchant.host", request);
        return responseMap;
    }

    /**
     * 获取case对应的expect信息，包含预期商家订单详情预期结果
     */
    public JSONObject getExpect(String caseId) {
        JSONObject expect = null;
        try {
            expect = (JSONObject) DBDataProvider.getExpectResponse(appCreateOrderUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return expect;
    }
    /**
     * 点评M站下单接口
     */
    public OrderModel mCreateOrder(String token, String userAgent, String caseid) {
        // 生成新Trace
        MtraceUtil.generatTrace("点评M站下单接口");

        String cookie = "dper=" + token;
        JSONObject mRequest = DBDataProvider.getRequest(mCreateOrderUrl, caseid);
        mRequest.getJSONObject("headers").put("pragma-token", token);
        mRequest.getJSONObject("headers").put("pragma-newtoken", token);
        mRequest.getJSONObject("headers").put("User-Agent", userAgent);
        mRequest.getJSONObject("headers").put("Cookie", cookie);
        ResponseMap response = DBCaseRequestUtil.post("env.api.51ping.host", mRequest);
        String responseBody = response.getResponseBody();
        HuiCreateOrderMResult mResult = JSON.parseObject(responseBody, HuiCreateOrderMResult.class);
        if (mResult == null || mResult.getPayToken() == null || mResult.getTradeNo() == null) {
            return null;
        }
        String url= mResult.getSuccessURL();
        String orderid= url.substring(url.indexOf('=')+1,url.indexOf('&'));
        mResult.setPayOrderID(Long.valueOf(orderid));
        return setOrderModel(orderid, mResult.getPayToken(), mResult.getTradeNo());
    }
}
