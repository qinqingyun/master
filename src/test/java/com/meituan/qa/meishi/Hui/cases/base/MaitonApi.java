package com.meituan.qa.meishi.Hui.cases.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.PayApi;
import com.meituan.qa.meishi.Hui.dto.OrderDetailCheck;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.OrderModel;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
@Data
public class MaitonApi{
    public String envpath;
    private  String dpTokenNew = "";
    private  static String mtTokenNew = "";
    public   String mtUserIdNew = "";
    private  String username = "user1";
    public  String dpWxClient = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.8(0x17000820) NetType/WIFI Language/zh_CN";
    public  String mtClientNew = "MApi 1.1 (mtscope 10.1.400 appstore; iPhone 11.3.1 iPhone10,3; a0d0)";
    public  String dpClient = "MApi 1.1(dpscope 10.16.0 appstore; iPhone 12.3.2 iPhone10,2; a0d0)";
    public  String mClient="Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
    UserModel userModel = new UserModel();
    OrderModel orderModel = new OrderModel();
    //Loopcheck重试次数和时间
    static final int interval = 1000;
    static final int timeout = 10000;

    //App侧相关接口
    static String appCreateOrderUrl = "hui/unicashiercreateorder.bin";  //双侧下单接口
    static String loadunifiedcashierUrl = "hui/loadunifiedcashier.bin"; //双侧加载收银台
    static String queryMopayStatusUrl = "hui/querymopaystatus.bin";  //双侧支付结果接口
    static String mtOrderDetailUrl = "/maiton/order/{orderid}";  //美团订单详情接口

    @LoopCheck(desc = "登录接口", interval = interval, timeout = timeout)
    public String userLogin(){
        envpath = ConfigMange.getValue("testData");
//        DPCUser dpcUser = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, username);
//        if( dpcUser == null ||  StringUtil.isBlank(dpcUser.getToken()))
//            return null;
//        dpTokenNew = dpcUser.getToken();

        MTCUser mtUser = (MTCUser) LoginUtil.login(LoginType.MT_C_LOGIN, username);
        if( mtUser == null ||  StringUtil.isBlank(mtUser.getToken()))
            return null;
        mtTokenNew = mtUser.getToken();
        mtUserIdNew = String.valueOf(mtUser.getId());
        return "登录成功";
    }


    //替换token
    public void replaceUserInfo(OrderSourceEnum sourceEnum){

        switch (sourceEnum){
            case DPApp:
                userModel.setToken(dpTokenNew);
                userModel.setUserAgent(dpClient);
                break;
            case MTApp:
                userModel.setToken(mtTokenNew);
                userModel.setUserAgent(mtClientNew);
                break;
        }

    }

    /**
     * 订单支付，调取收银台，使用支付宝支付
     * PayApi obj = new PayApi("stable.pay.test.sankuai.com");//美团测试
     * PayApi obj = new PayApi("stable.pay.st.sankuai.com");//美团测试
     * PayApi obj = new PayApi("cashier.qa.pay.test.sankuai.com");//点评侧域名
     *
     */
    public void orderPay(OrderModel orderModel,OrderSourceEnum sourceEnum) throws Exception {
        replaceUserInfo(sourceEnum);
        String payHost = ConfigMange.getValue("env.api.meishi.hui.pay");
        PayApi obj = new PayApi(payHost);
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "2");  //type {0:绑卡，1:余额 2.支付宝} 建议用余额支付
        params.put("tradeno", orderModel.getTradeNo());
        params.put("pay_token", orderModel.getPayToken());
        //params.put("pay_password", passWord); //设置支付密码http://payc.fsp.test.sankuai.com/rstpwd/index.htm
        params.put("token", userModel.getToken()); //获取token http://payc.fsp.test.sankuai.com/user/index.htm 或者 http://admin-user.wpt.test.sankuai.com/service/normal 或参考下面代码调用用户中心接口动态获取
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

    /**
     * APP下单接口，接口文档：
     * 美团点评根据不同token和useragent区分平台
     */
    //无需加载优惠平台接口
    public OrderModel uniCashierCreateOrder(String caseId, OrderSourceEnum sourceEnum){
        //替换token
        replaceUserInfo(sourceEnum);
        return uniCashierCreateOrder(userModel.getToken(),userModel.getUserAgent(),caseId,0,"",null);
    }
    //需要加载优惠平台接口，提供获取的couponofferid
    public OrderModel uniCashierCreateOrder(String caseId,int coupOfferId,OrderSourceEnum sourceEnum){
        //替换token
        replaceUserInfo(sourceEnum);
        return uniCashierCreateOrder(userModel.getToken(),userModel.getUserAgent(),caseId,coupOfferId,"",null);
    }
    public OrderModel uniCashierCreateOrder(String token, String userAgent, String caseid, int coupOfferId, String dpDealString, Map<String,Object> receipt){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String payToken;
        String tradeNo;
        Long orderId;
        String serializedId;

        JSONObject request = new JSONObject();
        String offerIdStr = "";
        try{
            request = DBDataProvider.getRequest(appCreateOrderUrl, caseid);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        request.getJSONObject("headers").put("pragma-token",token);
        request.getJSONObject("headers").put("pragma-newtoken",token);
        request.getJSONObject("headers").put("User-Agent",userAgent);
        request.getJSONObject("headers").put("pragma-os",userAgent);

        Map<String,String> param = new HashMap<>();
        if(coupOfferId > 0){
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
        String payAmount = JSON.toJSONString(request.getJSONObject("body").get("useramount"));
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
        orderModel.setOrderId(orderId.toString());
        orderModel.setPayToken(payToken);
        orderModel.setTradeNo(tradeNo);
        orderModel.setSerializedId(serializedId);
        orderModel.setPayAmount(payAmount);
        return orderModel;
    }
    /**
     * APP加载优惠台接口，接口文档：
     *
     */
    public int loadUnifiedCashier(String caseId,OrderSourceEnum sourceEnum){
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        //替换token
        replaceUserInfo(sourceEnum);
        JSONObject request = new JSONObject();
        JSONObject expect = new JSONObject();
        try{
            request = DBDataProvider.getRequest(loadunifiedcashierUrl, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        request.getJSONObject("headers").put("pragma-token",userModel.getToken());
        request.getJSONObject("headers").put("User-Agent",userModel.getUserAgent());
        request.getJSONObject("headers").put("pragma-os",userModel.getUserAgent());
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

    /**
     * APP支付结果，接口文档：
     *
     */
    public String queryMopayStatus(String caseId,OrderSourceEnum sourceEnum,String serializedId) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        //替换token
        replaceUserInfo(sourceEnum);
        JSONObject request = DBDataProvider.getRequest(queryMopayStatusUrl, caseId);
        request.getJSONObject("headers").put("pragma-token", userModel.getToken());
        request.getJSONObject("headers").put("pragma-newtoken", userModel.getToken());
        request.getJSONObject("headers").put("User-Agent", userModel.getUserAgent());
        request.getJSONObject("headers").put("pragma-os", userModel.getUserAgent());
        request.getJSONObject("params").put("serializedid", serializedId);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        JSONObject response= (JSONObject) DBDataProvider.getExpectResponse(queryMopayStatusUrl, caseId);
        if (responseMap == null) {
            log.error("querymopaystatus error:{}", serializedId);
            return null;
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        if(!(jsonObject.getString("StatusMsg")).equals(response.getString("StatusMsg"))){
            return "";
        }
        return jsonObject.getString("StatusMsg");
    }
    /**
     * APP订单详情页，接口文档：
     *
     */
    public String MtOrderDetail(String caseId,OrderSourceEnum sourceEnum,String orderId) {
        // 生成新Trace
        TracerUtil.initAndLogTrace();
        //替换token
        replaceUserInfo(sourceEnum);

        ResponseMap responseMap=null;

        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(mtOrderDetailUrl, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        request.getJSONObject("params").put("token",userModel.getToken());
        request.put("path",request.getString("path").replaceAll("\\{orderid\\}",orderId));

        responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        String body= responseMap.getResponseBody();
        OrderDetailCheck orderDetailinfo = parseHtml(responseMap.getResponseBody());
        String orderDetailinfoContent = orderDetailinfo.getContent();
        return orderDetailinfoContent;
    }
    /**
     * html页面解析
     */
    public OrderDetailCheck parseHtml(String html){
        OrderDetailCheck orderDetailCheck =new OrderDetailCheck();
        Document doc = Jsoup.parse(html);
        String content = doc.select("section[class=st-con st-succ]").get(0).select("div[class=st-wrap]").select("div[class=st-tit]").text();
        String  totalAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(0).text();
        String  payAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(1).text();
        orderDetailCheck.setContent(content);
        orderDetailCheck.setPayAmount(payAmount);
        orderDetailCheck.setTotalAmount(totalAmount);
        return orderDetailCheck;
    }


}
