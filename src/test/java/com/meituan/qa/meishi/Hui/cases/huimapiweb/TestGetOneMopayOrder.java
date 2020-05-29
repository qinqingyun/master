package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/getonemopayorder.hui",
        type = "mapi",des="请求一条买单信息")
@Slf4j
@HTTPAPI(apiPath = "/hui/getonemopayorder.hui")
public class TestGetOneMopayOrder extends TestDPLogin {

    private String _APIPATH = "/hui/getonemopayorder.hui";

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "正确用例")
    public void ms_c_getOneMopayOrder_001(JSONObject request, JSONObject expect){

        ResponseMap response = commonCall(dpToken,request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.VerifyStatus");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.StatusMsg");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "serializedid缺失或为空")
    public void ms_c_getOneMopayOrder_002(JSONObject request, JSONObject expect){
        ResponseMap response = commonCall(dpToken,request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.StatusCode");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "serializedid与用户id不一致")
    public void ms_c_getOneMopayOrder_003(JSONObject request, JSONObject expect){
        ResponseMap response = commonCall(dpToken,request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.StatusCode");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "用户未登录")
    public void ms_c_getOneMopayOrder_004(JSONObject request, JSONObject expect){
        ResponseMap response = commonCall("",request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.StatusCode");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
    }


    public ResponseMap commonCall(String token, JSONObject request)  {
	JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
       return  response;

    }

}
