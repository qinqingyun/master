package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * urls = "http://maiton.meituan.com/hui/unicashier/couponDesc",
 * 美团app版 优惠说明页
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="优惠说明页")
@Slf4j
public class TestUnicashierCouponDesc extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-23",des = "优惠说明页")
    public void ms_c_unicashierCouponDesc_01(){
        ResponseMap responseMap = huiMWebApi.unicashierCouponDesc("ms_c_unicashierCouponDesc_01");
        log.info("美团app版优惠说明页结果：{}",responseMap.getResponseBody());
        Assert.assertEquals(responseMap.getStatusCode(),200);
    }
}
