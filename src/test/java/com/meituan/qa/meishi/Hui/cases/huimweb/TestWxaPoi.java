package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.huiMWebApi;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;

/*
场景：买单C端,美团生活小程序收银台页面
urls： "http://mm.dianping.com/hui/mm/wxapoi"
请求方式：post
 */

@Slf4j
@HTTPAPI(apiPath = "hui/mm/wxapoi")
@ClassAnnotation(author = "zhenyumin",depart = "C",type = "http",des = "买单C端,美团生活小程序收银台页面")
public class TestWxaPoi extends TestBase {

    @Test(groups = "P2")
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-10",updateTime = "2020-10-26",des = "美团生活小程序收银台页面,带有7折优惠")
    public void ms_c_wxapoi_01(){
        ResponseMap responseMap = huiMWebApi.mmWxaPoi("ms_c_wxapoi_01");
        log.info("美团生活小程序收银台页面-返回结果：{}",responseMap.getResponseBody());

        //校验返回结果
        huiMWebApi.commonAssertSuccess(responseMap.getResponseBody());
        //校验返回结果中的买单优惠信息不为空
        AssertUtil.assertJSONPathExists(responseMap,"$.data.promos","返回结果不存在promos字段");
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject data = JsonPath.read(response, "$.data");
        JSONArray promosList = JsonPath.read(data, "$.promos");
        Assert.assertTrue(promosList.size() > 0,"当前门店买单优惠信息为空，门店信息：北海海鲜料理");
    }
    @Test(groups = "P3")
    @MethodAnotation(author = "byq",createTime = "2020-12-10",des = "美团生活小程序收银台页面,原价买单")
    public void ms_c_wxapoi_02(){
        ResponseMap responseMap = huiMWebApi.mmWxaPoi("ms_c_wxapoi_02");
        log.info("美团生活小程序收银台页面-返回结果：{}",responseMap.getResponseBody());
        //校验返回结果
        huiMWebApi.commonAssertSuccess(responseMap.getResponseBody());
        //校验返回结果中的买单优惠信息不为空
        AssertUtil.assertJSONPathExists(responseMap,"$.data.promos","返回结果不存在promos字段");
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject data = JsonPath.read(response, "$.data");
        JSONArray promosList = JsonPath.read(data, "$.promos");
        Assert.assertTrue(promosList.size() == 0,"当前门店买单优惠信息不为空，门店信息：西昌小院火盆烧烤");
    }


}
