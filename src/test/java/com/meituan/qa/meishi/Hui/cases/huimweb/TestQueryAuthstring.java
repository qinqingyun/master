package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/maiton/queryauthstring",
        type = "http",des="B扫C，商家券获取mopaytoken。https://km.sankuai.com/page/96688993")
@Slf4j
@HTTPAPI(apiPath = "/hui/maiton/queryauthstring")
public class TestQueryAuthstring {
    protected final Log logger = LogFactory.getLog(this.getClass());
    String apiPath = "/hui/maiton/queryauthstring";
    String mtToken = CommonLoginUtil.ms_c_MTLogin_01();
    String dpToken = CommonLoginUtil.ms_c_DPLogin_02();
    private String _MTHOST = ConfigMange.getValue("env.api.meishi.hui.maiton.host.mt");
    private String _DPHOST = ConfigMange.getValue("env.api.meishi.hui.maiton.host.dp");

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "点评侧商家券获取authstring")
    public void ms_c_queryauthstring_001(){
        ResponseMap response = commonCall(dpToken,_DPHOST,"ms_c_queryauthstring_001","120039394291998");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data")).isEmpty());
        Assert.assertTrue("1".equals(response.getValueByJsonPath("$.code")));
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "点评团购券获取authstring")
    public void ms_c_queryauthstring_002(){
        ResponseMap response = commonCall(dpToken,_DPHOST,"ms_c_queryauthstring_002","293924779951");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data")).isEmpty());
        Assert.assertTrue("1".equals(response.getValueByJsonPath("$.code")));
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "美团商家券获取authstring")
    public void ms_c_queryauthstring_101(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_queryauthstring_101","120012403162928");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data")).isEmpty());
        Assert.assertTrue("1".equals(response.getValueByJsonPath("$.code")));
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "美团团购券获取authstring")
    public void ms_c_queryauthstring_102(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_queryauthstring_102","293924779951");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data")).isEmpty());
        Assert.assertTrue("1".equals(response.getValueByJsonPath("$.code")));
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "券码为空")
    public void ms_c_queryauthstring_103(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_queryauthstring_102","");
        log.info(response.getResponseBody());
        Assert.assertTrue("参数错误".equals(response.getValueByJsonPath("$.msg")));
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "用户未登录")
    public void ms_c_queryauthstring_104(){
        ResponseMap response = commonCall("",_MTHOST,"ms_c_queryauthstring_102","293924779951");
        log.info(response.getResponseBody());
        Assert.assertTrue("未登录，请重新登录".equals(response.getValueByJsonPath("$.msg")));
    }

    public ResponseMap commonCall(String token, String host, String caseId, String code){
        JSONObject request = DBDataProvider.getRequest(apiPath, caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
        
		JsonPathUtil.setJsonPathVaule(request, "$.body.ticket",code);
		JsonPathUtil.setJsonPathVaule(request, "$.body.token",token);
        log.info(request.toString());
        ResponseMap response = DBCaseRequestUtil.post(host, request);
        return response;
    }
}
