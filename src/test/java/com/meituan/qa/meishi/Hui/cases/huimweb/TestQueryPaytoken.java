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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/maiton/querypaytoken",
        type = "http",des="B扫C，商家券轮询接口。https://km.sankuai.com/page/96688993")
@Slf4j
@HTTPAPI(apiPath = "/hui/maiton/querypaytoken")
public class TestQueryPaytoken {
    protected final Log logger = LogFactory.getLog(this.getClass());
    String apiPath = "/hui/maiton/querypaytoken";
    String mtToken = CommonLoginUtil.ms_c_MTLogin_01();
    String dpToken = CommonLoginUtil.ms_c_DPLogin_02();
    private String _MTHOST = ConfigMange.getValue("env.api.meishi.hui.maiton.host.mt");
    private String _DPHOST = ConfigMange.getValue("env.api.meishi.hui.maiton.host.dp");

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "点评商家券获取paytoken")
    public void ms_c_querypaytoken_001(){
        ResponseMap response = commonCall(dpToken,_DPHOST,"ms_c_querypaytoken_001","120039394291998","E8E3FE258EBE0C72B2F7CB4E185779F9");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data.payToken")).isEmpty());

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "点评团购券获取订单结果")
    public void ms_c_querypaytoken_002(){
        ResponseMap response = commonCall(dpToken,_DPHOST,"ms_c_querypaytoken_002","120000887029061","B26043D6CCAC537BA603BBA0534ABD3A");
        log.info(response.getResponseBody());

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "美团商家券获取paytoken")
    public void ms_c_querypaytoken_101(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_querypaytoken_101","120012403162928","BB352D6D693E6B2F6F25E5FC4EEC1C04");
        log.info(response.getResponseBody());
        Assert.assertTrue("成功".equals(response.getValueByJsonPath("$.msg")));
        Assert.assertTrue(!"0".equals(response.getValueByJsonPath("$.data.orderId")));
        Assert.assertTrue(!((String)response.getValueByJsonPath("$.data.payToken")).isEmpty());

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-17",updateTime = "2018-10-17",des = "美团团购券获取订单结果")
    public void ms_c_querypaytoken_102(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_querypaytoken_102","120012403162928","5320EC0BA8D213D78FF3C12416BA6E7F");
        log.info(response.getResponseBody());

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "未下单查询结果")
    public void ms_c_querypaytoken_103(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_querypaytoken_101","120000887029061","B26043D6CCAC537BA603BBA0534ABD3A");
        log.info(response.getResponseBody());
        Assert.assertTrue("暂未获取到订单信息，请继续".equals(response.getValueByJsonPath("$.msg")));

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "券id为空")
    public void ms_c_querypaytoken_104(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_querypaytoken_101","","B26043D6CCAC537BA603BBA0534ABD3A");
        log.info(response.getResponseBody());
        Assert.assertTrue("参数错误".equals(response.getValueByJsonPath("$.msg")));

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "authString为空")
    public void ms_c_querypaytoken_105(){
        ResponseMap response = commonCall(mtToken,_MTHOST,"ms_c_querypaytoken_101","120000887029061","");
        log.info(response.getResponseBody());
        Assert.assertTrue("参数错误".equals(response.getValueByJsonPath("$.msg")));

    }

    @Test(groups = {"P3","http"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "用户未登录")
    public void ms_c_querypaytoken_106(){
        ResponseMap response = commonCall("123",_MTHOST,"ms_c_querypaytoken_101","120000887029061","B26043D6CCAC537BA603BBA0534ABD3A");
        log.info(response.getResponseBody());
        Assert.assertTrue("参数错误".equals(response.getValueByJsonPath("$.msg")));

    }

    //不自动化
    @MethodAnotation(author = "liukang",createTime = "2018-10-22",updateTime = "2018-10-22",des = "authorString与用户不一致")
    public void ms_c_querypaytoken_107(){
        ResponseMap response = commonCall(dpToken,_DPHOST,"ms_c_querypaytoken_101","120000887029061","B26043D6CCAC537BA603BBA0534ABD3A");
        log.info(response.getResponseBody());
        Assert.assertTrue("用户鉴权错误".equals(response.getValueByJsonPath("$.msg")));

    }

    public ResponseMap commonCall(String token, String host, String caseId, String code, String authString){
        JSONObject request = DBDataProvider.getRequest(apiPath,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.ticket",code);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.params.authString",authString);
        log.info(request.toString());
        ResponseMap responseMap = DBCaseRequestUtil.get(host,request);
        return responseMap;
    }
}
