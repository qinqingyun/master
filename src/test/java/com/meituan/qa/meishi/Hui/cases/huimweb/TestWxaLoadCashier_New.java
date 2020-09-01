package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
@HTTPAPI(apiPath = "hui/mm/wxaloadcashier")
@ClassAnnotation(author = "zhenyumin",depart = "C", type = "http",des = "")
public class TestWxaLoadCashier_New extends TestDPLogin {


    @Test(groups = "P1",dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    public void ms_c_wxaLoadCashieraction_01(JSONObject request,JSONObject expect){

        return;
    }

}
