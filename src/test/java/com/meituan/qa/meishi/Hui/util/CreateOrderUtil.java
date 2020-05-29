package com.meituan.qa.meishi.Hui.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.api.PayApi;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.TRUE;

@Slf4j
public class CreateOrderUtil {

    private static String payToken;
    private static String tradeNo;
    private static Long orderId;
    private static String serializedId;
    private static String msg;

    //app加载优惠台
    public static int loadUnifiedCashier(String token,String userAgent,String caseId){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _APIPATH = "hui/loadunifiedcashier.bin";
        JSONObject request = new JSONObject();
        JSONObject expect = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
            expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        request.getJSONObject("headers").put("pragma-dpid","-8765947759983332911");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
        if (!response.toString().contains("CouponProducts")){
            return 0;
        }
        if(("[]").equals(response.getValueByJsonPath("$.CouponProducts"))){
            return 0;
        }else {
            return response.getValueByJsonPath("$.CouponProducts[0].CouponId");
        }
    }

//app下单接口
    //无需加载优惠平台接口
    public static List uniCashierCreateOrder(String token,String userAgent,String caseId){
        return uniCashierCreateOrder(token,userAgent,caseId,0,"",null);
    }
    //需要加载优惠平台接口，提供获取的couponofferid
    public static List uniCashierCreateOrder(String token,String userAgent,String caseId,int coupOfferId){
        return uniCashierCreateOrder(token,userAgent,caseId,coupOfferId,"",null);
    }
    //提供给自助验券使用
    public static List uniCashierCreateOrderDP(String token,String userAgent,String caseId,int coupOfferId) throws Exception {
        Map<String,Object> param = CreateOrderUtil.ms_c_tgDPCreateOrder(token,"ms_c_tgCreateOrder_02");
        return uniCashierCreateOrder(token,userAgent,caseId,coupOfferId,"",param);
    }
    public static List uniCashierCreateOrderMT(String token,String userAgent,String caseId,int coupOfferId) throws Exception {
        Map<String,Object> param = CreateOrderUtil.ms_c_tgCreateOrder("ms_c_tgCreateOrder_01");
        return uniCashierCreateOrder(token,userAgent,caseId,coupOfferId,"",param);
    }
    public static List uniCashierCreateOrder(String token, String userAgent, String caseid, int coupOfferId, String dpDealString, Map<String,Object> receipt){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _APIPATH = "hui/unicashiercreateorder.bin";
        JSONObject request = new JSONObject();
        String offerIdStr = "";
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseid);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);

        Map<String,String> param = new HashMap<>();
        if(coupOfferId > 0){
//            String offerIdStr = "{\"couponList\":[{\"ticketId\":0,\"productType\":202,\"couponId\":"+ coupOfferId +"}]}";
            offerIdStr = "{\"dealGroupId\":0,\"dealId\":0,\"needBuyDealCount\":0,\"useDealCount\":0,\"couponList\":[{\"productType\":201,\"couponId\":"+ coupOfferId +",\"ticketId\":\"0\"}]}";
            param.put("shopdealstring",offerIdStr);
        }
        if(!dpDealString.isEmpty()){
            param.put("dpdealstring",dpDealString);
        }

        if(receipt != null){
            param.put("receipt",(String)receipt.get("couponCode"));
            param.put("bizorderid",(String)receipt.get("orderid"));

        }

        if(coupOfferId > 0 || !dpDealString.isEmpty() || receipt != null){
            request.getJSONObject("body").put("dpdealstring",dpDealString);
            request.getJSONObject("body").put("shopdealstring",offerIdStr);
        }
        log.info("请求参数request:{}",request);
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
        List result = new ArrayList<String>();
        try {
            payToken = response.getValueByJsonPath("$.PayToken");
            tradeNo = response.getValueByJsonPath("$.Tradeno");
            orderId = (Long) response.getValueByJsonPath("$.OrderId");
            serializedId = response.getValueByJsonPath("$.SerializedId");
        } catch (Exception e) {
            log.info("有异常创单失败");
            return null;
        }
        //如果是预定金0元单，tradeNo==null
        if (tradeNo == null) {
            log.info("创单失败");
            return null;
        }
        if (payToken == null) {
            log.info("创单失败");
            return null;
        }
        result.add(payToken);
        result.add(tradeNo);
        result.add(orderId.toString());
        result.add(serializedId);
        return result;
    }

    public static List uniCashierCreateOrderResv(String token, String userAgent, String caseid, String bizOrderId){
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        //无需调起收银台的场景，预订0元场景
        String _APIPATH = "hui/unicashiercreateorder.bin";
        JSONObject request = new JSONObject();
        String offerIdStr = "";
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseid);
        }catch (Exception e){
            log.error(e.getMessage());}

        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        request.getJSONObject("body").put("bizorderid",bizOrderId);
        request.getJSONObject("body").put("bookrecordid",bizOrderId);
        log.info("请求request{}",request);
        /* 先注释掉，预订订单写死数据*/
//        Map<String,String> param = new HashMap<>();
//        if(!bizOrderId.isEmpty()){
//            param.put("bizorderid",bizOrderId);
//            param.put("bookrecordid",bizOrderId);
//        }

        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        List result = new ArrayList<String>();
        try {
            orderId = (Long) response.getValueByJsonPath("$.OrderId");
            serializedId = response.getValueByJsonPath("$.SerializedId");
        } catch (Exception e) {
            log.info("有异常创单失败");
            return null;
        }

        result.add(orderId.toString());
        result.add(serializedId.toString());
        return result;
    }

    public static void orderPay(String payToken,String tradeNo,String token) throws Exception {
        orderPay(payToken,tradeNo,token,"991609");
    }

   //支付
    public static void orderPay(String payToken,String tradeNo,String token,String passWord) throws Exception {
        String payHost = ConfigMange.getValue("env.api.meishi.hui.pay");
        PayApi obj = new PayApi(payHost);
//        PayApi obj = new PayApi("stable.pay.test.sankuai.com");//美团测试
//        PayApi obj = new PayApi("stable.pay.st.sankuai.com");//美团测试
//        PayApi obj = new PayApi("cashier.qa.pay.test.sankuai.com");//点评侧域名
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "2");  //type {0:绑卡，1:余额 2.支付宝} 建议用余额支付
        params.put("tradeno", tradeNo);
        params.put("pay_token", payToken);
        //params.put("pay_password", passWord); //设置支付密码http://payc.fsp.test.sankuai.com/rstpwd/index.htm
        params.put("token", token); //获取token http://payc.fsp.test.sankuai.com/user/index.htm 或者 http://admin-user.wpt.test.sankuai.com/service/normal 或参考下面代码调用用户中心接口动态获取
        //params.put("nb_app", "...");//非必传，默认值是group
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
        Assert.assertEquals(payRequest,Boolean.TRUE,"重试3次仍支付失败");
        Thread.sleep(1000);
    }


    //app支付结果页
    public static void queryMopayStatus(String token,String userAgent,String serializedId,String caseId){
        String _APIPATH = "hui/querymopaystatus.bin";
        JSONObject request = DBDataProvider.getRequest(_APIPATH, caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);
        request.getJSONObject("params").put("serializedid",serializedId);
		 DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
    }

    //点评订单详情
    public static void huiMaitonOrderDP(String caseId,String dpToken,Boolean resflag) {
        String _APIPATH = "hui/maiton/order";
        String cookie = "dper="+dpToken;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		//JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        request.getJSONObject("headers").put("Cookie",cookie);
        Map<String,String> sId = new HashMap<String, String>();
        sId.put("serializedId",serializedId);
		//JsonPathUtil.setJsonPathVaule(request, "$.params.serializedId",serializedId);
        request.getJSONObject("params").put("serializedid",serializedId);
		ResponseMap result = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        log.info("点评订单详情页获取状态：" + result);
        if(resflag){
            Assert.assertTrue(result.equals("支付成功"),"获取到的状态是：" + result);
        }else {
            Assert.assertTrue(result.equals("已退款"),"获取到的状态是：" + result);
        }
    }
    //美团+点评 订单详情页
    public static void huiMaitonOrder(String caseId,String mtToken,Boolean resflag,String orderid,int channelId){
         ResponseMap responseMap=null;
         String _APIPATH = "/maiton/order/{orderid}";
         JSONObject request = new JSONObject();
        try{
             request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        request.getJSONObject("params").put("token",mtToken);
        request.put("path",request.getString("path").replaceAll("\\{orderid\\}",orderid));
        if(channelId==1){
              responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        }
        else {

            responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        }
		String result = parseHtml(responseMap.getResponseBody());
        log.info("订单详情页获取状态：" + result);
        if(resflag){
            Assert.assertTrue(result.equals("支付成功"),"获取到的状态是：" + result);
            // todo   1.新老订单的映射  2.根据mis 查询老系统订单的状态


        }else {
            Assert.assertTrue(result.equals("已退款"),"获取到的状态是：" + result);
            // todo   1.新老订单的映射  2.根据mis 查询老系统订单的状态
        }
    }
    //获取订单详情页订单状态
    private static String parseHtml(String html){
        Document doc = Jsoup.parse(html);
        String content = doc.select("section[class=st-con st-succ]").get(0).select("div[class=st-wrap]").select("div[class=st-tit]").text();
        return content;
    }

//	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    public void test12(JSONObject request, JSONObject expect){
//        String token = CommonLoginUtil.ms_c_DPLogin_02();
//        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
//        CreateOrderUtil.loadUnicashier(token,userAgent,"ms_c_loadunicashier_01");
//    }
    //m站加载收银台
    public static int loadUnicashier(String token ,String userAgent,String caseId){
        String apiPath = "/hui/unicashier/loadUnicashier";
        String cookie = "dper="+token;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);*/
        request.getJSONObject("headers").put("Cookie",cookie);
        request.getJSONObject("headers").put("User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        String result = response.getResponseBody();
        Pattern p = Pattern.compile("\"offerId\":(\\d*?),");
        Matcher m = p.matcher(result);
        if(m.find()){
            return Integer.parseInt(m.group(1));
        }else {
            return 0;
        }

    }

    //m站下单(抓包看到企业买单用的也是这个接口)
    public static Map<String,String> ajaxCreateOrder(String token,String userAgent,String caseId){
        return ajaxCreateOrder(token,userAgent,caseId,0,"");
    }
    public static Map<String,String> ajaxCreateOrder(String token,String userAgent,String caseId,int coupOfferId){
        return ajaxCreateOrder(token,userAgent,caseId,coupOfferId,"");
    }
    public static Map<String,String> ajaxCreateOrder(String token,String userAgent,String caseId,int coupOfferId,String dpDealString){
        String apiPath = "hui/cashier/ajaxcreateorder";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        String cookie = "dper="+token;
		/*JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);*/
        request.getJSONObject("headers").put("Cookie",cookie);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        String offerIdStr = "";
        Map<String,String> param = new HashMap<>();
        if(coupOfferId > 0){
            offerIdStr = "[{\"offerId\":"+ coupOfferId +",\"productType\":202}]";
            param.put("offerIdStr",offerIdStr);
        }
        if(!dpDealString.isEmpty()){
            param.put("dpDealString",dpDealString);
        }
        if(coupOfferId > 0 || !dpDealString.isEmpty()){
			/*JsonPathUtil.setJsonPathVaule(request, "$.body.offerIdStr",offerIdStr);
			JsonPathUtil.setJsonPathVaule(request, "$.body.dpDealString",dpDealString);*/
            request.getJSONObject("body").put("offerIdStr",offerIdStr);
            request.getJSONObject("body").put("dpDealString",dpDealString);


        }
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        Map<String,String> result = new HashMap<String,String>();
        try {
            payToken = response.getValueByJsonPath("$.payToken");
            tradeNo = response.getValueByJsonPath("$.tradeNo");
            msg = response.getValueByJsonPath("$.msg");
            serializedId = ((String)response.getValueByJsonPath("$.successUrl")).split("=")[1].split("&")[0];
        }catch (Exception e){
            Assert.assertTrue(false,"有异常创单失败");
        }
        if(tradeNo == null){
            Assert.assertTrue(false,"创单失败");
        }
        result.put("payToken",payToken);
        result.put("tradeNo",tradeNo);
        result.put("msg",msg);
        result.put("serializedId",serializedId);
        return result;
    }

    //mm站微信扫码下单
    public static Map<String,String> mmCreateOrder(String token,String userAgent,String caseId){
        return mmCreateOrder(token,userAgent,caseId,0,"");
    }
    public static Map<String,String> mmCreateOrder(String token,String userAgent,String caseId,int coupOfferId){
        return mmCreateOrder(token,userAgent,caseId,coupOfferId,"");
    }
    public static Map<String,String> mmCreateOrder(String token,String userAgent,String caseId,int coupOfferId,String dpDealString){
        String apiPath = "/hui/mm/createOrder";
        String cookie = "dper="+token;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		//JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        request.getJSONObject("headers").put("Cookie",cookie);
//        request.setHeaderParams("User-Agent",userAgent);
        String offerIdStr = "";
        Map<String,String> param = new HashMap<>();
        if(coupOfferId > 0){
            offerIdStr = "[{\\\"offerId\\\":"+ coupOfferId +",\\\"productType\\\":1}]";
            param.put("offerIdStr",offerIdStr);
        }
        if(!dpDealString.isEmpty()){
            param.put("promoString",dpDealString);
        }
        if(coupOfferId > 0 || !dpDealString.isEmpty()){
			/*JsonPathUtil.setJsonPathVaule(request, "$.body.offerIdStr",offerIdStr);
			JsonPathUtil.setJsonPathVaule(request, "$.body.promoString",dpDealString);*/
            request.getJSONObject("body").put("offerIdStr",offerIdStr);
            request.getJSONObject("body").put("promoString",dpDealString);

        }
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        Map<String,String> result = new HashMap<String,String>();
        try {
            msg = response.getValueByJsonPath("$.msg");
            serializedId = ((String)response.getValueByJsonPath("$.callbackUrl")).split("=")[1].split("&")[0];
        }catch (Exception e){
            Assert.assertTrue(false,"有异常创单失败");
        }

        result.put("msg",msg);
        result.put("serializedId",serializedId);
        return result;
    }

    //点评微信小程序加载收银台
    public static int wxaloadUnicashier(String token ,String userAgent,String caseId){
        String apiPath = "/hui/mm/wxaloadcashier";
        String cookie = "dper="+token;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);*/
        request.getJSONObject("headers").put("Cookie",cookie);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        String result = response.getResponseBody();
        Pattern p = Pattern.compile("\"offerId\":(\\d*?),");
        Matcher m = p.matcher(result);
        if(m.find()){
            return Integer.parseInt(m.group(1));
        }else {
            return 0;
        }

    }
    //美团微信小程序加载收银台
    public static int wxMtaloadUnicashier(String token ,String userAgent,String caseId){
        String apiPath = "/hui/mm/wxaloadcashier";
        //String cookie = "dper="+token;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);*/
        //request.getJSONObject("params").put("token",token);
        //request.getJSONObject("headers").put("User-Agent",userAgent);
        ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        String result = response.getResponseBody();
        Pattern p = Pattern.compile("\"CouponProducts\":(\\d*?),");
        Matcher m = p.matcher(result);
        if(m.find()){
            return Integer.parseInt(m.group(1));
        }else {
            return 0;
        }

    }
    //点评微信小程序下单接口
    public static Map<String,String> wxaCreateOrder(String token, String caseId, int couponOfferId, String dpDealString, DeskCoupon deskCoupon) throws UnsupportedEncodingException {
        String _APIPATH = "/hui/mm/wxacreateorder";
        String offerIdStr = null;

        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }

		JsonPathUtil.setJsonPathVaule(request, "$.body.token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.body.dpDealString",dpDealString);
        request.getJSONObject("body").put("token",token);
        request.getJSONObject("body").put("dpDealString",dpDealString);
        request.getJSONObject("body").put(null,offerIdStr);
        if(couponOfferId > 0){
            offerIdStr = "{\"offerIdStr\":[{\"couponId\":" + couponOfferId + ", \"productType\":201}]}";
            request.getJSONObject("body").put("offerIdStr",offerIdStr);
        }
        if (deskCoupon != null) {
            if (!Strings.isNullOrEmpty(deskCoupon.getCipher())) {
                request.getJSONObject("body").put("dpDealString", URLEncoder.encode(deskCoupon.getCipher(), "utf-8"));
            }
//            BigDecimal couponAmount = BigDecimal.valueOf(deskCoupon.getAmount());
//            if (BigDecimal.ZERO.compareTo(couponAmount) < 0) {
//                BigDecimal userAmount = request.getJSONObject("body").getBigDecimal("originAmount").subtract(couponAmount);
//                request.getJSONObject("body").put("userAmount", String.valueOf(userAmount));
//            }

        }
        log.info("请求参数request{}",request);

        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        JSONObject responseJson = JSONObject.parseObject(response.getResponseBody()).getJSONObject("data");
        Map<String,String> result = new HashMap<String,String>();
        try {
            orderId = responseJson.getLong("orderId");
        }catch (Exception e){
            Assert.assertTrue(false,"有异常创单失败");
        }
        result.put("orderId",orderId.toString());
        return result;
    }
    //美团微信小程序下单接口
    public static Map<String,String> wxaMtCreateOrder(String token, String caseId, int couponOfferId, String dpDealString, DeskCoupon deskCoupon) throws UnsupportedEncodingException {
        String _APIPATH = "/hui/mm/wxacreateorder";

        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        JsonPathUtil.setJsonPathVaule(request, "$.body.token",token);
        JsonPathUtil.setJsonPathVaule(request, "$.body.dpDealString",dpDealString);
        request.getJSONObject("body").put("token",token);
        request.getJSONObject("body").put("dpDealString",dpDealString);
//        if(couponOfferId > 0){
//            offerIdStr = "{\"offerIdStr\":[{\"couponId\":" + couponOfferId + ", \"productType\":201}]}";
//            request.getJSONObject("body").put("offerIdStr",offerIdStr);
//        }
        if (deskCoupon != null) {
            if (!Strings.isNullOrEmpty(deskCoupon.getCipher())) {
                request.getJSONObject("body").put("dpDealString", URLEncoder.encode(deskCoupon.getCipher(), "utf-8"));
            }
            BigDecimal couponAmount = BigDecimal.valueOf(deskCoupon.getAmount());
            if (BigDecimal.ZERO.compareTo(couponAmount) < 0) {
                BigDecimal userAmount = request.getJSONObject("body").getBigDecimal("originAmount").subtract(couponAmount);
                request.getJSONObject("body").put("userAmount", String.valueOf(userAmount));
            }
        }
        log.info("请求参数request{}",request);

        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.mt", request);
        JSONObject responseJson = JSONObject.parseObject(response.getResponseBody()).getJSONObject("data");
        Map<String,String> result = new HashMap<String,String>();
        try {
            orderId = responseJson.getLong("orderId");
        }catch (Exception e){
            Assert.assertTrue(false,"有异常创单失败");
        }
        result.put("orderId",orderId.toString());
        return result;
    }


    @Test()
    public void test111() throws Exception {
//        String token = CommonLoginUtil.ms_c_MTLogin_01();
//        CreateOrderUtil.ms_c_tgCreateOrder(token,"ms_c_tgCreateOrder_01");
        String token = CommonLoginUtil.ms_c_DPLogin_02();
        CreateOrderUtil.receiptVerifyDP(token,"MApi 1.1 (com.dianping.v1 10.4.0 huidutest MI_MAX_2; Android 7.1.1)","ms_c_fullProcess4receiptverify_01");
//        CreateOrderUtil.ms_c_tgDPCreateOrder(token,"ms_c_tgCreateOrder_02");
    }
    //买单自助验券，单独验券接口
    public static void receiptVerifyDP(String token, String userAgent,String caseId) throws Exception {
        Map<String,Object> param = CreateOrderUtil.ms_c_tgDPCreateOrder(token,"ms_c_tgCreateOrder_02");
        receiptVerify(token,userAgent,caseId,param);
    }
    public static void receiptVerifyMT(String token, String userAgent,String caseId) throws Exception {
        Map<String,Object> param = CreateOrderUtil.ms_c_tgCreateOrder("ms_c_tgCreateOrder_01");
        receiptVerify(token,userAgent,caseId,param);
    }
    public static void receiptVerify(String token,String userAgent,String caseId, Map<String,Object> param){
        String _APIPATH = "/hui/receiptverify.bin";

        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }

		//JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
        request.getJSONObject("headers").put("pragma-token",token);
		//JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        //JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
        request.getJSONObject("headers").put("User-Agent",userAgent);

        Map<String,String> body = new HashMap<>();
        body.put("grouponorderid",(String)param.get("orderid"));
        body.put("receipt",((ArrayList<String>)param.get("couponList")).get(0));
		//JsonPathUtil.setJsonPathVaule(request, "$.body.receipt",((ArrayList<String>)param.get("couponList")).get(0));
        request.getJSONObject("body").put("receipt",((ArrayList<String>)param.get("couponList")).get(0));
		//JsonPathUtil.setJsonPathVaule(request, "$.body.grouponorderid",(String)param.get("orderid"));
        request.getJSONObject("body").put("grouponorderid",(String)param.get("orderid"));
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        Assert.assertTrue("恭喜,验券成功!".equals(response.getValueByJsonPath("$.statusMsg")));
//        return response;
    }


    //美团团购下单
    public static Map<String, Object> ms_c_tgCreateOrder(String caseid) throws Exception {
        String token = CommonLoginUtil.ms_c_MTLogin_01();
        String _APIPATH="/api/foodorder";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseid);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.params.userid","29042361");*/
        request.getJSONObject("params").put("token",token);
        request.getJSONObject("params").put("userid","29042361");
		ResponseMap response = DBCaseRequestUtil.post("env.api.foodorder.get.host", request);

        JSONArray jsonObject = JSONArray.parseArray(response.getResponseBody());
        String tradeno = jsonObject.getJSONObject(0).getString("tradeno");
        String pay_token = jsonObject.getJSONObject(0).getString("pay_token");
        String orderId = jsonObject.getJSONObject(0).getString("orderid");
        CreateOrderUtil.orderPay(pay_token,tradeno,token);
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        return CreateOrderUtil.paysuccess(token,orderId,"ms_c_tgPaysuccess_01","mt");
    }

    //点评团购下单
    public static Map<String, Object> ms_c_tgDPCreateOrder(String token,String caseId) throws Exception {
        String _APIPATH="/api/foodorder";
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,"",_APIPATH,caseId,"dp");
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		/*JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.params.userid","772634893");*/
        request.getJSONObject("params").put("token",token);
        request.getJSONObject("params").put("userid","772634893");
		ResponseMap response = DBCaseRequestUtil.post("env.api.foodorder.get.host", request);

        JSONArray jsonObject = JSONArray.parseArray(response.getResponseBody());
        String tradeno = jsonObject.getJSONObject(0).getString("tradeno");
        String pay_token = jsonObject.getJSONObject(0).getString("pay_token");
        String orderId = jsonObject.getJSONObject(0).getString("orderid");
        CreateOrderUtil.orderPay(pay_token,tradeno,token);
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            log.error(e.toString());
        }
        return CreateOrderUtil.paysuccess(token,orderId,"ms_c_tgPaysuccess_02","dp");
    }
//团购支付成功页
    public static Map<String,Object> paysuccess(String token,String orderId ,String caseId,String source){

        String apiPath = "/group/v1/user/{userid}/foodorder/paysuccess";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        //发送get请求时,取的只是form中的参数,而form参数来自RequestUpdate
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("orderid",orderId);
		/*JsonPathUtil.setJsonPathVaule(request, "$.params.orderid",orderId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);*/
        request.getJSONObject("params").put("orderid",orderId);
        request.getJSONObject("params").put("token",token);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.dealDetail.host", request);
        log.info(response.getResponseBody());
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.getResponseBody());
        Assert.assertEquals(jsonObject.getString("success"),"0");
        JSONArray couponArray = jsonObject.getJSONObject("data").getJSONArray("coupon");
//                .getJSONObject(0).getString("code");

        if (couponArray == null || couponArray.isEmpty()){
            Assert.assertTrue(1==2,"获取美团团购券码错误");
        }
        List<String> coupons = new ArrayList<>();
        for(int i =0;i<couponArray.size();i++){
            coupons.add(couponArray.getJSONObject(i).getString("code"));
        }
        log.info("couponCode:" + coupons);
        map.put("couponList",coupons);
        return map;
    }
}
