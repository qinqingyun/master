package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;

/*
* 场景：点评、美团APP，当前门店支持买单时加载买单收银台【买单收银台，填写买单订单相关信息】
* */

@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "hui/loadunifiedcashier.bin", type = "mapi",des = "进入收银台(0开头用例表示点评，1表示美团)")
@Slf4j
@HTTPAPI(apiPath = "hui/loadunifiedcashier.bin")
public class TestLoadUnifiedCashierAction extends TestDPLogin {
//    private Boolean flag = true ;//点评美团返回结果不同，通过标识来判断点评结果还是美团结果，true为点评

    private HuiMapiWebLoopCheck hmwLoop = new HuiMapiWebLoopCheck();

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例：进入收银台")
    public void ms_c_loadunifiedcashieraction_01(JSONObject request,JSONObject expect)throws Exception {
        log.info("入参：{}",JSONObject.toJSONString(request));

        ResponseMap responseMap = hmwLoop.getLoopQuery(mtToken,mtClient,request);
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        // 校验
        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap,"HTTP code不是200！");
        JSONObject couponProducts = responseMap.getJSONObjectByJsonPath("$.CouponProducts[0]");
        AssertUtil.assertNotNull(couponProducts.get("CouponId"),"CouponId为空");
        AssertUtil.assertTrue(couponProducts.getIntValue("ProductType")>0,"ProductType小于0");
    }
}
