package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * url: http://mm.51ping.com/hui/mm/createOrder
 * 场景：点评mm站提单页下单
 */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "/hui/mm/createOrder", type = "http",httpMethod = "post",des="mm站下单")
@Slf4j
public class TestMMCreateOrder_dp extends TestBase {

    @Test(groups = "{P1}")
    @MethodAnotation(author = "zhenyumin",createTime = "2020-10-27",updateTime = "2020-10-27",des = "正常下单，并查看订单详情")
    public void ms_c_dpmmCreateOrder_1(){
        ResponseMap responseMap = huiMWebApi.mmCreateOrder("ms_c_dpmmCreateOrder_1");
        log.info("mm站下单-返回结果：{}",responseMap.getResponseBody());


        AssertUtil.assertHttpCode(responseMap,200,"http状态码不是200!");
        AssertUtil.assertJSONPathExists(responseMap,"$.callbackUrl","返回结果中不存在callbackUrl！");
        AssertUtil.assertJSONPathExists(responseMap,"$.code","返回结果中不存在code！");
        AssertUtil.assertJSONPathExists(responseMap,"$.msg","返回结果中不存在msg！");


        JSONObject resBody = JSONObject.parseObject(responseMap.getResponseBody());

        AssertUtil.assertEquals(resBody.get("code"),200,"返回结果的code字段值不符合预期!");
        AssertUtil.assertEquals(resBody.get("msg"),"创单成功","返回结果中msg不符合预期!");

        AssertUtil.assertNotNull(responseMap,"$.callbackUrl","callbackUrl为空!");

    }
}
