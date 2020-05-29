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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/getunicashierentry.hui",
        type = "mapi",des="点评APP商户页浮窗入口,丽人商户页入口")
@Slf4j
@HTTPAPI(apiPath = "/hui/getunicashierentry.hui")
public class TestGetUniCashierEntry extends TestDPLogin {

    private String _APIPATH = "/hui/getunicashierentry.hui";


    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "正确用例")
    public void ms_c_getUniCashierEntry_001(JSONObject request, JSONObject expect){

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getUniCashierEntry_001");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.PromosDesc");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "门店没有买单或优惠不可用")
    public void ms_c_getUniCashierEntry_002(JSONObject request, JSONObject expect){

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getUniCashierEntry_002");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.HasCashierEntry");
		AssertUtil.assertJSONPathExists(response, "$");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "shopid错误非法")
    public void ms_c_getUniCashierEntry_003(JSONObject request, JSONObject expect){

//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getUniCashierEntry_003");
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
		AssertUtil.assertJSONPathExists(response, "$");
    }

}
