package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
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
/**
 * urls = "http://m.51ping.com/hui/mm/wxacheck",
 * 点评微信下单check接口
 * 接口文档：
 * @return
 */

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/mm/wxacheck",
        type = "https",des="微信小程序距离检查。https://wiki.sankuai.com/pages/viewpage.action?pageId=1219765460")
@Slf4j
@HTTPAPI(apiPath = "/hui/mm/wxacheck")
public class TestWxaCheck extends TestBase {
    private String _APIPATH = "/hui/mm/wxacheck";

    @Test(groups = {"weixin","P1"})
    @MethodAnotation(author = "byq",createTime = "2018-03-15",updateTime = "2018-03-15",des = "弹出距离弹窗")
    public void ms_c_wxaCheck_01(){
        ResponseMap responseMap = huiMWebApi.wxaCheck("ms_c_wxaCheck_01");
        AssertUtil.assertHttp200(responseMap,"接口返回不为200");
    }

    @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "lk",createTime = "2018-03-16",updateTime = "2018-03-16",des = "纬度为空")
    public void ms_c_wxaCheck_02(){
        ResponseMap responseMap = huiMWebApi.wxaCheck("ms_c_wxaCheck_02");
        AssertUtil.assertHttp200(responseMap,"接口返回不为200");
    }

    @Test(groups = {"weixin","P3"})
    @MethodAnotation(author = "liukang",createTime = "2018-03-16",updateTime = "2018-03-16",des = "经度为空")
    public void ms_c_wxaCheck_03(){
        ResponseMap responseMap = huiMWebApi.wxaCheck("ms_c_wxaCheck_03");
        AssertUtil.assertHttp200(responseMap,"接口返回不为200");
    }

}
