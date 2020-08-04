package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/*
* 场景：买单下单时，用户提交订单信息前调用此接口，校验用户与门店间距离，大于1000米时进行弹窗提示
* 返回结果：
*   RichMessage JSON格式描述信息
*   Status      10: 通过, 20: 提醒, 30:拒绝
* */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi",des = "用户与门店间距离检查")
@Slf4j
@HTTPAPI(apiPath = "hui/ordercheck.hui")
public class TestOrderCheckAction extends TestDPLogin {
    private HuiMapiWebLoopCheck loopCheck = new HuiMapiWebLoopCheck();

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-用户与门店不在同一位置，弹窗提示")
    public void ms_c_ordercheckaction_01(JSONObject request, JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);
        AssertUtil.assertJsonPathValueEquals(responseMap,20,"$.Status","Status不为20，不进行弹窗提示！");
        AssertUtil.assertEquals(responseMap.getJSONArrayByJsonPath("$.RichMessage").size(), 3, "返回结果中字段RichMessage的大小不为3");
    }
/*
    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-用户与门店在同一位置，通过check")
    public void ms_c_ordercheckaction_02(JSONObject request, JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        ResponseMap responseMap = loopCheck.orderCheckLoopQuery(mtToken,mtClient,request);
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);
        AssertUtil.assertJsonPathValueEquals(responseMap,10,"$.Status","Status不为10，未通过check！");
    }

        @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-拒绝生成订单，未通过check")
    public void ms_c_ordercheckaction_02(JSONObject request, JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        ResponseMap responseMap = loopCheck.orderCheckLoopQuery(mtToken,mtClient,request);
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);
        AssertUtil.assertJsonPathValueEquals(responseMap,30,"$.Status","Status不为10，未通过check！");
    }

 */

}
