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
import org.testng.Assert;
import org.testng.annotations.Test;


@ClassAnnotation(author = "liukang",depart = "C",apiName = "getmopayorder.bin",
        type = "mapi",des="点评订单列表")
@Slf4j
@HTTPAPI(apiPath = "hui/getmopayorder.bin")
public class TestGetMopayOrder  extends TestDPLogin {

    private String _APIPATH = "hui/getmopayorder.bin";
//    private String _CASEFOLDER = "Hui/getmopayorder";

   /* @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2017-11-29",updateTime = "2017-11-29",des = "点评订单列表")
    public void ms_c_getmopayorder_01(JSONObject request, JSONObject expect){
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);

        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.List[0].BtnList[0]");
    }*/
    /*@Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2017-11-29",updateTime = "2017-11-29",des = "缺失token")
    public void ms_c_getmopayorder_02(JSONObject request, JSONObject expect){
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);

        ArrayList<String> keyList = new ArrayList<String>(){{add("Name");}};
		AssertUtil.assertJsonPathValueEquals(response, expect,"$", "$","");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.AlertLoginLink");
    }*/

    /*@Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "composestartindex传入16(rd代码中限制15)，即相当于翻一页")
    public void ms_c_getmopayorder_03(JSONObject request, JSONObject expect){
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);

        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.List[0].BtnList[0]");
    }*/

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "composestartindex传入较大数值")
    public void ms_c_getmopayorder_04(JSONObject request, JSONObject expect){
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        JSONObject jsonObject = JSON.parseObject(response.getValueByJsonPath("$"));
        Assert.assertTrue(jsonObject.getString("List").equals("[]"));
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "iscomposeend传入大于0的值")
    public void ms_c_getmopayorder_05(JSONObject request, JSONObject expect){
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.EmptyMsg");

    }

}
