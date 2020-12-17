package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * urls = "https://m.51ping.com/hui/maiton/ajax/ordercheck?shopId=24799161&shopUuid=G3S8S3ILaJWkoJoP&shopType=0&lat=0&lng=0",
 * 点评M站-支付前确认，距离检查
 * * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="点评M站-支付前确认")
@Slf4j
public class TestOrdercheck extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-12-03",des = "点评M站-支付前确认")
    public void ms_c_orderCheck_01(){
        ResponseMap responseMap = huiMWebApi.orderCheck("ms_c_orderCheck_01");
        log.info("点评M站-支付前确认结果：{}",responseMap.getResponseBody());
        AssertUtil.assertHttpCode(responseMap,200,"http状态码!=200");
    }
    @Test(groups = {"P3"})
    @MethodAnotation(author = "byq",createTime = "2020-12-10",des = "纬度为空")
    public void ms_c_orderCheck_02(){
        ResponseMap responseMap = huiMWebApi.orderCheck("ms_c_orderCheck_02");
        AssertUtil.assertHttp200(responseMap,"接口返回不为200");
    }

    @Test(groups = {"P3"})
    @MethodAnotation(author = "byq",createTime = "2020-12-10",des = "经度为空")
    public void ms_c_orderCheck_03(){
        ResponseMap responseMap = huiMWebApi.orderCheck("ms_c_orderCheck_03");
        AssertUtil.assertHttp200(responseMap,"接口返回不为200");
    }
}
