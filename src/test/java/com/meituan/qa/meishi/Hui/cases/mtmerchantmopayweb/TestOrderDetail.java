package com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.EPassportUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/orderdetail",
        type = "http",des="商家后台订单查询订单详情页")
@Slf4j
@HTTPAPI(apiPath = "/hui/orderdetail")
public class TestOrderDetail {
//    String bisid = CommonLoginUtil.merchantAPPLogin();
    String bisid = "kzXJmOHVNZew4nsvs9d-oyrj_nhE-LgGHh7XDNo4xYJAuQVrPerQGwsVqNrHt2EIQcOf0fuuGB4p6-3PakR3GA";
    private final Log logger = LogFactory.getLog(this.getClass());
    String apiPath = "/hui/orderdetail";

    @Test(groups = {"P3","orderquery"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-29",updateTime = "2018-09-29",des = "订单详情页")
    public void ms_c_orderDetail_01(){
        ResponseMap response = callService("ms_c_orderDetail_001","HGKPET1Z6RZUB3AND");
        log.info(response.toString());
        JSONObject jsonObject = getContent(response.getResponseBody());
        if(jsonObject == null){
            Assert.assertTrue(false,"未查询到信息");
        }
        Assert.assertTrue(jsonObject.getBigDecimal("dealAmount").compareTo(new BigDecimal(0)) >0);
    }

    private ResponseMap callService(String caseId, String serializedId) {
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(apiPath, caseId);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        if(serializedId != null){
            Map<String,String> urlParam = new HashMap<String, String>();
            urlParam.put("serializedId",serializedId);
			JsonPathUtil.setJsonPathVaule(request, "$.params.serializedId",serializedId);
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + bisid);
		return DBCaseRequestUtil.get("env.api.meishi.merchant.host", request);
    }

    private ResponseMap callService(String caseId){
        return callService(caseId,null);
    }

    private JSONObject getContent(String baseStr){
        Pattern p = Pattern.compile("huiOrderDetailVo:(.*?)};");
        Matcher m = p.matcher(baseStr);
        if(m.find()){
            return JSONObject.parseObject(m.group(1));
        }
        return null;
    }
}
