package com.meituan.qa.meishi.Hui.cases.huimapiweb;

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

@ClassAnnotation(des = "获取B扫C临时会话凭证(B扫C验券场景，C端首先发起，生成凭证)")
@Slf4j
@HTTPAPI(apiPath = "hui/getmopaytoken.bin")
public class TestGetMopayTokenAction extends TestDPLogin {
    private HuiMapiWebLoopCheck loopCheck = new HuiMapiWebLoopCheck();

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin",createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-美团团购券获取mopaytoken")
    public void ms_c_getmopaytokenaction_01(JSONObject request, JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        ResponseMap responseMap = loopCheck.postLoopQuery(mtToken,mtClient,request);
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);
        AssertUtil.assertNotNull(responseMap.getValueByJsonPath("$.mopayToken"));
    }
}

/*
* {
  "cityid": "",
  "unionid": "",
  "ticketype": "",
  "lat": "",
  "lng": "",
  "cx": "",
  "ticket": ""
}


{
    "ticket": "78867677",
    "ticketype": 0,
    "lng": "116.48763",
    "lat": "40.00839",
    "cx": "123",
    "unionid": "0392DC9B24CA871F0D053F390A69DCA6B957D094920BD56D9433C83EEE319164",
    "cityid": "8000"
}
* */
