package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 商家后台订单详情页查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="商家后台订单详情页查询")
@Slf4j
public class TestGetMerchantOrderDetail extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-18",des = "商家后台订单详情页查询")
    public void ms_c_getMerchantOrderDetai_01(){
        JSONObject merchentOrderDetail = huiMWebApi.getMerchentOrderDetail("ms_c_orderDetail_001");
        log.info("商家订单详情页结果：{}",merchentOrderDetail.toString());
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "dealAmount").toString(),"10.00","商家订单dealAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "originAmount").toString(),"10.00","商家订单originAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "discountAmount").toString(),"0.00","商家订单discountAmount金额与预期不符");
        Assert.assertEquals(JsonPath.read(merchentOrderDetail, "maitonAmount").toString(),"10.00","商家订单maitonAmount金额与预期不符");
    }
}
