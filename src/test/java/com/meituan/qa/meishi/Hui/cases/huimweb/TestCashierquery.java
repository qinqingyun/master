package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * url：http://mm.51ping.com/hui/mm/cashier?shopId=24799161
 * 场景：点评mm站提单页面/收银页
 */
@ClassAnnotation(author = "zhenyumin",depart = "C",type = "http",httpMethod = "get",des = "点评mm站提单页面/收银页")
@Slf4j
public class TestCashierquery extends TestBase {
    @Test(groups = "{P1}")
    @MethodAnotation(author = "zhenyumin",createTime = "2020-10-26",des = "点评mm站提单页面/收银页")
    public void ms_c_mmCashier_01(){
        ResponseMap responseMap = huiMWebApi.cashierquery("ms_c_cashierquery_01");
        log.info("返回结果：{}",responseMap.getResponseBody());

        AssertUtil.assertHttpCode(responseMap,200,"http状态码不是200!");
        Assert.assertTrue(responseMap.getHeader("Content-Type").equals("text/html;charset=UTF-8"),"返回结果不是html格式!");
    }

}

