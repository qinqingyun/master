package com.meituan.qa.meishi.Hui.cases.huimweb;

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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "hui/maiton/ajax/requestrefund",
        type = "http",des="申请退款")
@Slf4j
@HTTPAPI(apiPath = "hui/maiton/ajax/requestrefund")
public class TestOrderRefund extends TestDPLogin {
    private String _APIPATH = "hui/maiton/ajax/requestrefund";
//    private String _CASEFOLDER = "Hui/Maiton/refund";
    private boolean flag = true;//标识美团点评，true为点评

    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-01-02",updateTime = "2018-01-03",des = "点评订单申请退款")
    public void ms_c_huiOrderRefund_001(JSONObject request, JSONObject expect)throws Exception{
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,_PORT,_APIPATH,"ms_c_huiOrderRefund_001");
        String cookie = "dper="+dpToken;
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);

		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("msg");}};

		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }
    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-01-02",updateTime = "2018-01-03",des = "美团订单申请退款")
    public void ms_c_huiOrderRefund_101(JSONObject request, JSONObject expect)throws Exception{
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,_PORT,_APIPATH,"ms_c_huiOrderRefund_101");
        String cookie = "mt_token="+mtToken;
		JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie",cookie);

		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.maiton.host.dp", request);
        ArrayList<String> keyList = new ArrayList<String>(){{add("msg");}};

		AssertUtil.assertJSONPathExists(response, "$");
AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);    }

//    private Response callService(String caseId,String serializedId){
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,_PORT,_APIPATH,caseId);
//        Map<String,String> paramList = new HashMap<String, String>();
//        if(flag){
//            String cookie = "dper="+dpToken;
//            request.setHeaderParams("Cookie",cookie);
//            paramList.put("serializedId",serializedId);
//        }else{
//            String cookie = "mt_token="+mtToken;
//            request.setHeaderParams("Cookie",cookie);
//        }
//        flag = !flag;
//        return request.post() ;
//    }

}
