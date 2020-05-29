package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/huipreprocess.hui",
        type = "mapi",des="闪惠预处理页")
@Slf4j
@HTTPAPI(apiPath = "/hui/huipreprocess.hui")
public class TestHuiPreProcess {
//必填字段主要流程case
    private String _APIPATH = "/hui/huipreprocess.hui";
/*
    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-13",updateTime = "2018-02-13",des = "正确用例")
    public void ms_c_huiPreProcess_001(JSONObject request, JSONObject expect){

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_huiPreProcess_001");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.UrlScheme");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Code");
    }*/


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-13",updateTime = "2018-02-13",des = "门店没有优惠")
    public void ms_c_huiPreProcess_002(JSONObject request, JSONObject expect){

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_huiPreProcess_002");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Message");
    }

    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-13",updateTime = "2018-02-13",des = "shopid非法")
    public void ms_c_huiPreProcess_003(JSONObject request, JSONObject expect) {

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi, _HOST, "", _APIPATH, "ms_c_huiPreProcess_003");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.StatusCode");
    }
}
