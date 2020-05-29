package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/getmopaytoken.bin",
        type = "mapi",des="B扫C获取mopaytoken")
@Slf4j
@HTTPAPI(apiPath = "/hui/getmopaytoken.bin")
public class TestGetMopayToken extends TestDPLogin{
    private String _APIPATH = "/hui/getmopaytoken.bin";


    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "点评团购券获取mopaytoken")
    public void ms_c_getMopayToken_001(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getMopayToken_001","172768967953");
        log.info(response.toString());
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.mopayToken")).isEmpty());
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "美团团购券获取mopaytoken")
    public void ms_c_getMopayToken_101(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_101","123884463797");
        log.info(response.toString());
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.mopayToken")).isEmpty());
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "美团商家券获取mopaytoken")
    public void ms_c_getMopayToken_102(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_102","120000887026661");
        log.info(response.toString());
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.mopayToken")).isEmpty());
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "传递错误ticketType")
    public void ms_c_getMopayToken_103(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_103","120000874070361");
        log.info(response.toString());
        Assert.assertTrue("不支持的券类型".equals(response.getValueByJsonPath("$.Content")));
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "美团团购券已被使用再次获取mopaytoken")
    public void ms_c_getMopayToken_104(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_104","284012167793");
        log.info(response.toString());
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.mopayToken")).isEmpty());
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "美团商家券已被使用再次获取mopaytoken")
    public void ms_c_getMopayToken_105(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_105","120000874070361");
        log.info(response.toString());
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.mopayToken")).isEmpty());
    }
//没有相应的简单校验为null 空字符串啥的都没有
    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "ticket为空")
    public void ms_c_getMopayToken_106(){
        ResponseMap response = commonCall(mtToken,mtClient,"ms_c_getMopayToken_106","");
        log.info(response.toString());
        Assert.assertTrue("缺少必要参数!".equals(response.getValueByJsonPath("$.Content")));
    }

    public ResponseMap commonCall(String token, String userAgent, String caseId, String code){
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH,caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
        Map<String,String> prama = new HashMap<>();
        prama.put("ticket",code);
		JsonPathUtil.setJsonPathVaule(request, "$.body.ticket",code);
        log.info(request.toJSONString());
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        return response;
    }
}
