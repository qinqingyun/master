package com.meituan.qa.meishi.Hui.cases.base;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
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
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class MaitonApi {
    public String envpath;
    private  String dpTokenNew = "";
    private  static String mtTokenNew = "";
    private  String mtUserIdNew = "";
    private  String username = "user1";
    public  String dpWxClient = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.8(0x17000820) NetType/WIFI Language/zh_CN";
    public  String mtClientNew = "MApi 1.1 (mtscope 10.1.400 appstore; iPhone 11.3.1 iPhone10,3; a0d0)";
    public  String dpClient = "MApi 1.1(dpscope 10.16.0 appstore; iPhone 12.3.2 iPhone10,2; a0d0)";
    public  String mClient="Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
    UserModel userModel = new UserModel();
    //Loopcheck重试次数和时间
    static final int interval = 1000;
    static final int timeout = 10000;

    //下单接口
    static String dpAppCreateOrderUrl = "hui/unicashiercreateorder.bin";

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
     * APP下单接口，接口文档：
     */
    //无需加载优惠平台接口
    public List uniCashierCreateOrder(String caseId, OrderSourceEnum sourceEnum){
        replaceUserInfo(sourceEnum);
        return uniCashierCreateOrder(userModel.getToken(),userModel.getUserAgent(),caseId,0,"",null);
    }
    //需要加载优惠平台接口，提供获取的couponofferid
    public List uniCashierCreateOrder(String token,String userAgent,String caseId,int coupOfferId){
        return uniCashierCreateOrder(token,userAgent,caseId,coupOfferId,"",null);
    }
    public List uniCashierCreateOrder(String token, String userAgent, String caseid, int coupOfferId, String dpDealString, Map<String,Object> receipt){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String payToken;
        String tradeNo;
        Long orderId;
        String serializedId;

        JSONObject request = new JSONObject();
        String offerIdStr = "";
        try{
            request = DBDataProvider.getRequest(dpAppCreateOrderUrl, caseid);
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
}
