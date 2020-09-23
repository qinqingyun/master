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

import java.math.BigDecimal;

/**
 * urls = "http://m.dianping.com/hui/ajax/cashieroverview",
 * 买单收银页-总览
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="买单收银页-总览")
@Slf4j
public class TestAjaxCashieroverview extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "买单收银页-总览")
    public void ms_c_ajaxCashieroverview_01(){
        ResponseMap responseMap = huiMWebApi.ajaxCashieroverview("ms_c_ajaxCashieroverview_01");
        log.info("买单收银页总览查询结果：{}",responseMap.getResponseBody());
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject huiOverviewResponseVo = JsonPath.read(response, "$.huiOverviewResponseVo");
        JSONObject huiOverviewVoResponse = JsonPath.read(huiOverviewResponseVo, "$.huiOverviewVo");
        BigDecimal totalOriginAmount = JsonPath.read(huiOverviewVoResponse, "$.totalOriginAmount");
        Assert.assertTrue(totalOriginAmount.compareTo(BigDecimal.ZERO) == 1,"查询收银页总览查询为空，门店信息：北海海鲜料理");
    }
}
