package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
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
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/getmopaypromos.hui",
        type = "mapi",des="闪惠商户页面获取优惠的url")
@Slf4j
@HTTPAPI(apiPath = "/hui/getmopaypromos.hui")
public class TestGetMopayPromos extends TestDPLogin{

    private String _APIPATH = "/hui/getmopaypromos.hui";


    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-09",updateTime = "2018-02-09",des = "正确用例")
    public void ms_c_getMopayProms_01(JSONObject request, JSONObject expect)throws Exception{
        ResponseMap response = callService(request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.ShowStatus");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Offers[0].Title");

    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-09",updateTime = "2018-02-09",des = "门店不存在优惠")
    public void ms_c_getMopayProms_02(JSONObject request, JSONObject expect)throws Exception{
        ResponseMap response = callService(request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("ShowStatus");add("Title");}};
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-09",updateTime = "2018-02-09",des = "shopid错误")
    public void ms_c_getMopayProms_03(JSONObject request, JSONObject expect)throws Exception{
        ResponseMap response = callService(request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("StatusCode");add("Content");}};
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-09",updateTime = "2018-02-09",des = "clientuuid缺失")
    public void ms_c_getMopayProms_04(JSONObject request, JSONObject expect)throws Exception{
        ResponseMap response = callService(request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("StatusCode");add("Content");}};
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

    private ResponseMap callService(JSONObject request){
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",dpToken);
		return DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
    }

}
