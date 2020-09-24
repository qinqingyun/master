package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONArray;
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
 * urls = "http://m.dianping.com/hui/ajax/cashierquerybyorderid",
 * 买单收银页-订单查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="买单收银页-订单查询")
@Slf4j
public class TestAjaxCashierquerybyorderid extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "买单收银页-订单查询")
    public void ms_c_ajaxCashierquerybyorderid_01(){
        ResponseMap responseMap = huiMWebApi.ajaxCashierquerybyorderid("ms_c_ajaxCashierquerybyorderid_01");
        log.info("买单收银页订单查询结果：{}",responseMap.getResponseBody());
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject huiOrderListResponseVo = JsonPath.read(response, "$.huiOrderListResponseVo");
        JSONArray resultList = JsonPath.read(huiOrderListResponseVo, "$.resultList");
        resultList.size();
        Assert.assertTrue(resultList.size() > 0,"查询收银页订单查询为空，门店信息：北海海鲜料理");
    }
}
