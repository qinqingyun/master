package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Builder
@Data
@Slf4j
public class QueryOrderState {

    String token;
    String userAgent;
    String serializedId;
    String orderId;
    String caseId;

    //app支付结果页
    public String queryMopayStatus() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _APIPATH = "hui/querymopaystatus.bin";
        JSONObject request = DBDataProvider.getRequest(_APIPATH, caseId);
        request.getJSONObject("headers").put("pragma-token", token);
        request.getJSONObject("headers").put("pragma-newtoken", token);
        request.getJSONObject("headers").put("User-Agent", userAgent);
        request.getJSONObject("headers").put("pragma-os", userAgent);
        request.getJSONObject("params").put("serializedid", serializedId);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        JSONObject response= (JSONObject) DBDataProvider.getExpectResponse(_APIPATH, caseId);
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
    //m 站支付结果页
    public String queryMStatus() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _MPATH = "hui/mopay/ajax/order";
        String cookie = "dper=" + token;
        JSONObject mrequest = DBDataProvider.getRequest(_MPATH, caseId);
        mrequest.getJSONObject("headers").put("pragma-token", token);
        mrequest.getJSONObject("headers").put("pragma-newtoken", token);
        mrequest.getJSONObject("headers").put("User-Agent", userAgent);
        mrequest.getJSONObject("headers").put("pragma-os", userAgent);
        mrequest.getJSONObject("params").put("serializedid", serializedId);
        mrequest.getJSONObject("headers").put("Cookie", cookie);

        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.51ping.host", mrequest);
        JSONObject response= (JSONObject) DBDataProvider.getExpectResponse(_MPATH, caseId);
        if (responseMap == null) {
            log.error("querymopaystatus error:{}", serializedId);
            return "";
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        //Assert.assertEquals(jsonObject.getJSONObject("StatusMsg"), response.getJSONObject("StatusMsg"));
        return responseMap.getResponseBody();
    }
    public  String orderDetail(){
        String _MdPATH = "/hui/maiton/order";
        JSONObject mdequest = DBDataProvider.getRequest(_MdPATH, caseId);
        mdequest.getJSONObject("headers").put("pragma-token", token);
        mdequest.getJSONObject("headers").put("pragma-newtoken", token);
        mdequest.getJSONObject("headers").put("User-Agent", userAgent);
        mdequest.getJSONObject("headers").put("pragma-os", userAgent);
        mdequest.getJSONObject("params").put("serializedid", serializedId);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.51ping.host", mdequest);
        JSONObject response= (JSONObject) DBDataProvider.getExpectResponse(_MdPATH, caseId);
        if (responseMap == null) {
            log.error("querymopaystatus error:{}", serializedId);
            return "";
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        Assert.assertEquals(jsonObject.getJSONObject("StatusMsg"), response.getJSONObject("StatusMsg"));
        return responseMap.getResponseBody();
    }


}
