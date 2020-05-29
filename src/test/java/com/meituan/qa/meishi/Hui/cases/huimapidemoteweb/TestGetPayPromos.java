package com.meituan.qa.meishi.Hui.cases.huimapidemoteweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/getpaypromos.hui",
        type = "mapi",des="获取商户闪付优惠信息（老接口，老版本部分到综门店在用）")
@Slf4j
@HTTPAPI(apiPath = "/hui/getpaypromos.hui")
public class TestGetPayPromos extends TestDPLogin {

    private String _APIPATH = "/hui/getpaypromos.hui";

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-12",updateTime = "2018-02-12",des = "正确用例")
    public void ms_c_getPayPromos_001(JSONObject request, JSONObject expect){
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getPayPromos_001");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.ShowStatus");
    }

    @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "门店没有闪惠")
    public void ms_c_getPayPromos_002(JSONObject request, JSONObject expect){
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getPayPromos_002");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.ShowStatus");
    }

  /*  @Test(groups = {"P3","mapi"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-02-23",updateTime = "2018-02-23",des = "shopid错误")
    public void ms_c_getPayPromos_003(JSONObject request, JSONObject expect){
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.mapi,_HOST,"",_APIPATH,"ms_c_getPayPromos_003");
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",dpToken);
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
		AssertUtil.assertJsonPathValueEquals(response, expect,"$.Content");
    }*/

}
