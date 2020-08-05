package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/*
* 场景：点评、美团APP门店详情页查询买单优惠、券信息
* */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi",des = "商户页面获取优惠")
@Slf4j
@HTTPAPI(apiPath = "hui/getmopaypromosinfo.hui")
public class TestGetMOPayPromosInfoAction extends TestDPLogin {
    private HuiMapiWebLoopCheck hmpLoopCheck = new HuiMapiWebLoopCheck();
//    private String dp_userAgent = "MApi 1.3 (dpscope 10.32.0 appstore; iPhone 12.2 iPhone10,1; a0d0)";

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-获取POI页买单优惠")
    public void ms_c_getMOPayPromosInfoAction_01(JSONObject request, JSONObject expect){


        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = hmpLoopCheck.getLoopQuery(mtToken,mtClient,request);      // 美团用户

        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));
        AssertUtil.assertNotNull(responseMap,"response为空！");

        AssertUtil.assertJSONPathExists(responseMap,"$.Promos[0].Title");
        AssertUtil.assertJSONPathExists(responseMap,"$.Promos[0].PromoDesc");


        AssertUtil.assertNotNull(responseMap.getJSONObjectByJsonPath("$.Promos[0]").get("Title"));
        AssertUtil.assertNotNull(responseMap.getJSONObjectByJsonPath("$.Promos[0]").get("PromoDesc"));

    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-(点评侧)获取POI页买单优惠")
    public void ms_c_getMOPayPromosInfoAction_101(JSONObject request, JSONObject expect){

        log.info("入参：{}",JSONObject.toJSONString(request));
        ResponseMap responseMap = hmpLoopCheck.getLoopQuery(dpToken,dpClient,request);    // 点评用户
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        AssertUtil.assertNotNull(responseMap,"response为空！");

        AssertUtil.assertJSONPathExists(responseMap,"$.Promos[0].Title");
        AssertUtil.assertJSONPathExists(responseMap,"$.Promos[0].PromoDesc");


        AssertUtil.assertNotNull(responseMap.getJSONObjectByJsonPath("$.Promos[0]").get("Title"));
        AssertUtil.assertNotNull(responseMap.getJSONObjectByJsonPath("$.Promos[0]").get("PromoDesc"));

    }
}
