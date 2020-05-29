package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/maiton/order/{orderid}",
        type = "mapi",des="美团")
@Slf4j
@HTTPAPI(apiPath = "/maiton/order/{orderid}")
public class TestHuiMaitonOrderMT extends TestDPLogin {
    private String _APIPATH = "/maiton/order/{orderid}";

    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-28",updateTime = "2018-04-28",des = "美团订单详情页,支付成功")
    public void ms_c_huiMaitonOrder_101(JSONObject request, JSONObject expect){

        ResponseMap response = callService(request);
        String result = parseHtml(response.toString());
        Assert.assertTrue(result.equals("支付成功"),"获取到的状态是：" + result);
    }

    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-28",updateTime = "2018-04-28",des = "美团订单详情页,已退款")
    public void ms_c_huiMaitonOrder_102(JSONObject request, JSONObject expect){

        ResponseMap response = callService(request);
        String result = parseHtml(response.toString());
        Assert.assertTrue(result.equals("已退款"),"获取到的状态是：" + result);
    }

    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-28",updateTime = "2018-04-28",des = "美团订单详情页,未登录状态")
    public void ms_c_huiMaitonOrder_103(JSONObject request, JSONObject expect){
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http,_HOST,_PORT,_APIPATH,"ms_c_huiMaitonOrder_103");
		ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        String result = parseHtml(response.toString(),false);
        Assert.assertTrue(result.equals("请重新登录！"),"获取到的状态是：" + result);
    }

    @Test(groups = {"P3","maiton"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-04-28",updateTime = "2018-04-28",des = "美团订单详情页,订单错误")
    public void ms_c_huiMaitonOrder_104(JSONObject request, JSONObject expect){
        ResponseMap response = callService(request);
        String result = parseHtml(response.toString(),false);
        Assert.assertTrue(result.equals("订单不存在！"),"获取到的状态是：" + result);
    }

    private ResponseMap callService(JSONObject request){
        Map<String,String> token = new HashMap<String, String>();
        token.put("token",mtToken);
		JsonPathUtil.setJsonPathVaule(request, "$.params.token",mtToken);
		return DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
    }

    private String parseHtml(String html){
        return parseHtml(html,true);
    }

    private String parseHtml(String html,Boolean flag){
        Document doc = Jsoup.parse(html);
        String content = null;
        if(flag){
            content = doc.select("section[class=st-con st-succ]").get(0).select("div[class=st-wrap]").select("div[class=st-tit]").text();
        }else {
            content = doc.select("div[class=error-title]").text();
        }
        return content;
    }

}
