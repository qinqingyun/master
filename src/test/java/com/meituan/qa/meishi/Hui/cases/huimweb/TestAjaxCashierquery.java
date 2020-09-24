package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * urls = "http://m.dianping.com/hui/ajax/cashierquery",
 * 买单收银页-按门店活动查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="买单收银页-按门店活动查询")
@Slf4j
public class TestAjaxCashierquery extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "买单收银页-按门店活动查询")
    public void ms_c_ajaxCashierquery_01(){
        ResponseMap responseMap = huiMWebApi.ajaxCashierquery("ms_c_ajaxCashierquery_01");
        log.info("买单收银页按门店活动查询结果：{}",responseMap.getResponseBody());
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject huiOrderPageResponse = JsonPath.read(response, "$.huiOrderPageResponseVo");
        JSONObject pageVoResponse = JsonPath.read(huiOrderPageResponse, "$.pageVo");
        Integer totalCount = JsonPath.read(pageVoResponse, "$.totalCount");
        Assert.assertTrue(totalCount >0,"查询门店结果为空，门店信息：北海海鲜料理");
    }

}
