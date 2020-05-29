package com.meituan.qa.meishi.Hui.cases.huimapidemoteweb;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "getmopaypromosinfo.hui",
        type = "mapi",des = "获取买单优惠信息(0开头用例表示点评，1表示美团)")
@Slf4j
@HTTPAPI(apiPath = "hui/getmopaypromosinfo.hui")
public class TestGetMopayPromosinfo extends TestDPLogin{
    private String _APIPATH = "hui/getmopaypromosinfo.hui";
//    private String _CASEFOLDER0 = "Hui/mopaypromosinfo";
    String dpClientNew = "MApi 1.1 (com.dianping.v1 10.5.0 huidutest MI_MAX_2; Android 7.1.1)";


    @Test(groups = {"Functest1","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2017-10-23",updateTime = "2017-12-26",des = "点评获取优惠信息")
    public void ms_c_getmopaypromosinfo_01()throws Exception{
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_001");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_001");
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.Promos[0]"),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2017-11-3",updateTime = "2017-12-26",des = "店铺没有买单优惠信息")
    public void ms_c_getmopaypromosinfo_02(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_002");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_002");
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Promos");

    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2017-11-6",updateTime = "2017-11-6",des = "shopid不存在")
    public void ms_c_getmopaypromosinfo_03(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_003");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_003");
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Promos");

    }

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2017-11-8",updateTime = "2017-11-8",des="shopid不合法")
    public void ms_c_getmopaypromosinfo_04(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_004");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_004");
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");

    }

//以下case涉及的是半年销量

    @Test(groups = {"P3","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des="半年销量小于5位数")
    public void ms_c_getmopaypromosinfo_05(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_005");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_005");
        log.info(response.toString());
        String[] resSplit = ((String)response.getValueByJsonPath("$.OrderNumDesc")).split(" ");
        String result = expect.getString("OrderNumDesc");
        int num = Integer.parseInt(resSplit[1]);
        Assert.assertTrue(resSplit[0].equals(result));
        Assert.assertTrue(num > 0);

    }

//    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @Test()
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des="半年销量大于4位数")
    public void ms_c_getmopaypromosinfo_06()throws Exception{
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_006");
        log.info(response.toString());
        String[] resSplit = ((String) response.getValueByJsonPath("$.OrderNumDesc")).split(" ");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_006");
        String result = expect.getString("OrderNumDesc");
        Assert.assertTrue(resSplit[0].equals(result));
        Assert.assertTrue(resSplit[1].matches("^\\d+\\.?\\d?万\\+?"));
    }



//此case只做测试验证，因为线上跑case若有人在门店买单超过5单就会报错，而且不跑此case无影响
    @Test()
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des="半年销量小于5返回为空")
    public void ms_c_getmopaypromosinfo_07()throws Exception{
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_007");
        Assert.assertTrue(((String)response.getValueByJsonPath("$.OrderNumDesc")).isEmpty());
    }

    @Test(groups = {"Functest1","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-18",updateTime = "2018-09-18",des = "门店为折扣优惠")
    public void ms_c_getmopaypromosinfo_08()throws Exception{
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_008");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_008");
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.Promos[0]"),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    @Test(groups = {"Functest1","mapi"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-25",updateTime = "2018-09-25",des = "版本超过10.3只展示一个优惠")
    public void ms_c_getmopaypromosinfo_09()throws Exception{
        ResponseMap response = commonCall(dpToken,dpClientNew,"ms_c_getmopaypromosinfo_009");
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,"ms_c_getmopaypromosinfo_009");
        ArrayList<String> keyList = new ArrayList<String>(){{add("Title");add("IsShow");}};
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.Promos[0]"),expect.getJSONObject("$.Promos[0]"),JSONCompareMode.LENIENT,keyList,true);
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()).getJSONObject("$.Promos[1]"),expect.getJSONObject("$.Promos[1]"),JSONCompareMode.LENIENT,keyList,true);

    }
//还未写case
//    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-08-2",updateTime = "2018-08-2",des="新买单门店半年销量")
    public void ms_c_getmopaypromosinfo_88(){
        ResponseMap response = commonCall(dpToken,dpClient,"ms_c_getmopaypromosinfo_008");
        log.info(response.toString());
        String[] resSplit = ((String)response.getValueByJsonPath("$.OrderNumDesc")).split(" ");
        int num = Integer.parseInt(resSplit[1]);
        Assert.assertTrue(num > 0);
    }




    public ResponseMap commonCall(String token, String userAgent, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
        return response;
    }

}
