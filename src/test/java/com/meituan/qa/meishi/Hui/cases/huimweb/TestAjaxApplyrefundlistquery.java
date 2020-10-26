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

/**
 * urls = "http://m.dianping.com/hui/ajax/applyrefundlistquery",
 * 退款待办列表查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="退款待办列表查询")
@Slf4j
public class TestAjaxApplyrefundlistquery extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "退款待办列表查询")
    public void ms_c_ajaxApplyrefundlistquery_01(){
        ResponseMap responseMap = huiMWebApi.ajaxApplyrefundlistquery("ms_c_ajaxApplyrefundlistquery_01");
        log.info("退款待办列表查询结果：{}",responseMap.getResponseBody());
        JSONObject response = JSONObject.parseObject(responseMap.getResponseBody());
        JSONObject huiApplyRefundListResponseVo = JsonPath.read(response, "$.huiApplyRefundListResponseVo");
        JSONArray orderList = JsonPath.read(huiApplyRefundListResponseVo, "$.orderList");
        Assert.assertTrue(orderList.size() >= 0,"退款待办列表查询异常，门店信息：北海海鲜料理");
    }
}
