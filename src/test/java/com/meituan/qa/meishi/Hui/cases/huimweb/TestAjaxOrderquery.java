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
 * urls = "http://m.dianping.com/hui/ajax/orderquery",
 * 订单查询页-全部门店订单查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="订单查询页-全部门店订单查询")
@Slf4j
public class TestAjaxOrderquery extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "订单查询页-全部门店订单查询")
    public void ms_c_ajaxOrderquery_01(){
        ResponseMap responseMap = huiMWebApi.ajaxOrderquery("ms_c_ajaxOrderquery_01");
        log.info("买单收银页订单查询结果：{}",responseMap.getResponseBody());
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject huiOrderPageResponseVo = JsonPath.read(response, "$.huiOrderPageResponseVo");
        JSONObject pageVo = JsonPath.read(huiOrderPageResponseVo, "$.pageVo");
        Integer totalCount = JsonPath.read(pageVo, "$.totalCount");
        Assert.assertTrue(totalCount > 0,"全部门店订单查询结果为空");
    }
}
