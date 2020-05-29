package com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb.util;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CallServiceUtil {

    public static JSONObject callService(String caseId, long beginTime, long endTime, String apiPath, String bisid) {
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        Map<String, Long> bodyParam = new HashMap<String, Long>();
        if(beginTime != 0 && endTime != 0){
            bodyParam.put("beginTime", beginTime);
            bodyParam.put("endTime", endTime);
			JsonPathUtil.setJsonPathVaule(request, "$.body.endTime",endTime);
			JsonPathUtil.setJsonPathVaule(request, "$.body.beginTime",beginTime);
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie", "hui_bsid_https=" + bisid);
		String str = DBCaseRequestUtil.post("env.api.meishi.merchant.host", request).getResponseBody();
        if(str != null){
            return JSONObject.parseObject(str);
        }
        return null;
    }

    public static JSONObject callService(String caseId, String apiPath, String bisid) {
        long endTime = System.currentTimeMillis();
        long beginTime = endTime - 30 * 60 * 1000;
        return callService(caseId, beginTime, endTime,apiPath,bisid);
    }
}
