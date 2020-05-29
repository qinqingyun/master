package com.meituan.qa.meishi.Hui.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.mopayprocess.refundflow.request.*;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OrderRefundUtil {


   /* //用户申请退款
   public ApplyRefundRequest applyRefundRequest(Long orderId){
       ApplyRefundRequest refundReq = new ApplyRefundRequest();
       refundReq.setOrderId(orderId);
      // refundReq.set


   }
*/

    //用户申请退款
    public static void orderRefund(String cookie,String serializedId,String caseId){
        String _APIPATH = "hui/maiton/ajax/requestrefund";

//        String cookie = "dper="+dpToken;
//        if(!flag){
//            cookie = "mt_token="+mtToken;
//        }
        JSONObject request = new JSONObject();
        JSONObject expect = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,caseId);
            expect =  (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		//JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        request.getJSONObject("headers").put("Cookie",cookie);
        Map<String,String> sId = new HashMap<String, String>();
        sId.put("serializedId",serializedId);
		//JsonPathUtil.setJsonPathVaule(request, "$.params.serializedId",serializedId);
        request.getJSONObject("params").put("serializedId",serializedId);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("msg");}};

        AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()), expect, JSONCompareMode.LENIENT, keyList, true);

    }

    public static void orderRefundDP(String token,String serializedId,String caseId){
        String cookie = "dper=" + token;
        orderRefund(cookie,serializedId,caseId);
    }

    public static void orderRefundMT(String token,String serializedId,String caseId){
        String cookie = "mt_token=" + token;
        orderRefund(cookie,serializedId,caseId);
    }

    //商家同意退款
    public static ResponseMap merchantAgreeRefund(String bsid, String serializedId){
        String _APIPATH = "/hui/ajax/agreerefund";
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,"",_APIPATH,"ms_c_huiFullProcess_001_merchantAgreeRefund");
        String cookie = "hui_bsid="+bsid;
//        String cookie = "hui_bsid=fHSjtohvM3pPAmSVjLoJYzcII3m9LmmSwo9p6J_BEJ7ffAST88UUSuQEDYD24lQ77Nup4vaAF1VF3jmlmMAVyQ";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_merchantAgreeRefund");

        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        Map<String,String> sId = new HashMap<String, String>();
        sId.put("serializedId",serializedId);
//        sId.put("serializedId","HFGMNM1RZN36QGA09&");
		JsonPathUtil.setJsonPathVaule(request, "$.body.serializedId",serializedId);
		return DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);

    }

    //商家直接进行退款
    public static ResponseMap merchantOrderRefund(String bsid, String serializedId){
        String _APIPATH = "/hui/ajax/refundorder";
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,"",_APIPATH,"ms_c_huiFullProcess_002_merchantOrderRefund");
        String cookie = "hui_bsid="+bsid;
//        String cookie = "hui_bsid=q-xb4QhkDzIns33XkDExPKf5iiMVNTBda8k0jdFMunM88FexsvS9E6dGR-UM9hCSV_xwPCCQzZcuMscpN3j18Q";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_002_merchantOrderRefund");

        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        Map<String,String> sId = new HashMap<String, String>();
        sId.put("serializedId",serializedId);
//        sId.put("serializedId","HEFK2M1RZN36QGA2S");
		JsonPathUtil.setJsonPathVaule(request, "$.body.serializedId",serializedId);
		return DBCaseRequestUtil.post("env.api.meishi.merchant.host", request);
    }

    //客服提交退款申请检查
    public static ResponseMap misRefundCheck(String ssoid, String serializedId){
        String _APIPATH = "/mis/hui/refundCheck";
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,"",_APIPATH,"ms_c_huiFullProcess_001_misRefundCheck");
        Map<String,String> sId = new HashMap<String, String>();
        String cookie = "ssoid="+ssoid;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_misRefundCheck");

        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        sId.put("serializedId",serializedId);
		JsonPathUtil.setJsonPathVaule(request, "$.body.serializedId","HEFK2M1RZN36QGA2S");
		return DBCaseRequestUtil.post("env.api.meishi.mis.host", request);
    }
    //客服进行退款(暂时未调通，直接调用后端接口)
    public static ResponseMap misRefund(String ssoid, String serializedId){
        String _APIPATH = "/mis/hui/refund";
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,"",_APIPATH,"ms_c_huiFullProcess_001_misRefund");
        Map<String,String> sId = new HashMap<String, String>();
        String cookie = "ssoid="+ssoid;
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,"ms_c_huiFullProcess_001_misRefund");

        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);
        sId.put("serializedId",serializedId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.serializedId",serializedId);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.mis.host", request);
        System.out.println(response);
        return response;
    }

}
