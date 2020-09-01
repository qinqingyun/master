package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/*
场景：买单C端,美团生活小程序收银台页面
urls： "http://mm.dianping.com/hui/mm/wxapoi"
请求方式：post
 */

@Slf4j
@HTTPAPI(apiPath = "hui/mm/wxapoi")
@ClassAnnotation(author = "zhenyumin",depart = "C",type = "http",des = "买单C端,美团生活小程序收银台页面")
public class TestWxaPoi extends TestDPLogin {
    private HuiMWebLoopCheck loopCheck = new HuiMWebLoopCheck();

    @Test(groups = "P1",dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-10",updateTime = "2020-08-14")
    public void ms_c_wxapoi_01(JSONObject request,JSONObject expect){
     log.info("入餐：{}",request);
     ResponseMap responseMap = loopCheck.getLoopQuery(request,dpClient,dpToken);
     log.info("返回结果：{}",JSONObject.toJSONString(responseMap));
    }
}
