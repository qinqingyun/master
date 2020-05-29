package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "unicashiercreateorder.bin",
type = "mapi",des="下单页创建订单")
@Slf4j
public class TestUniCashierCreateOrder extends TestDPLogin {
    private String _APIPATH = "hui/unicashiercreateorder.bin";
    private String _APIPATH1 = "/mapi/usercenter/uniorderdelete.bin";
//    private String _CASEFOLDER0 = "Hui/unicashiercreateorder";
    private Boolean flag = true ;//点评美团返回结果不同，通过标识来判断点评结果还是美团结果，true为点评

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-2",updateTime = "2017-12-4",des = "正确用例")
    public void ms_c_unicashiercreateorder_01(String token,String userAgent,String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);

        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        if (flag){
            assertCall(response,expect,keyList);
            String orderId = response.getValueByJsonPath("SerializedId");
//            try {//点评推模式后不会推未支付订单
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            orderDelete(orderId);
        }else{
			AssertUtil.assertJSONPathExists(response, "$");

        }
        flag = !flag;
    }



    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-20",updateTime = "2017-12-4",des = "originamount输入错误为0")
    public void ms_c_unicashiercreateorder_02(String token,String userAgent,String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-20",updateTime = "2017-12-4",des = "useramount输入错误为负值")
    public void ms_c_unicashiercreateorder_03(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-20",updateTime = "2017-12-4",des = "shopid非法为0")
    public void ms_c_unicashiercreateorder_04(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-20",updateTime = "2017-12-4",des = "没有买单或店铺不存在")
    public void ms_c_unicashiercreateorder_05(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2017-11-20",updateTime = "2017-12-4",des = "bizordertype为0")
    public void ms_c_unicashiercreateorder_06(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        if (flag){
            assertCall(response,expect,keyList);
            String orderId = response.getValueByJsonPath("SerializedId");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            orderDelete(orderId);
        }else{
			AssertUtil.assertJSONPathExists(response, "$");
        }
        flag = !flag;
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "买单优惠过期不存在")
    public void ms_c_unicashiercreateorder_07(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "originamount原价小于nodiscountamount的金额")
    public void ms_c_unicashiercreateorder_08(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "nodiscountamount传入与原价相同金额")
    public void ms_c_unicashiercreateorder_09(String token,String userAgent,String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        if (flag){
            assertCall(response,expect,keyList);
            String orderId = response.getValueByJsonPath("SerializedId");
//            try {//点评推模式后不会推未支付订单
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            orderDelete(orderId);
        }else{
			AssertUtil.assertJSONPathExists(response, "$");

        }
        flag = !flag;
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "shopid字段缺失")
    public void ms_c_unicashiercreateorder_10(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "originamount字段缺失")
    public void ms_c_unicashiercreateorder_11(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "useramount字段缺失")
    public void ms_c_unicashiercreateorder_12(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "nodiscountamount字段缺失")
    public void ms_c_unicashiercreateorder_13(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "nodiscountamount字段缺失")
    public void ms_c_unicashiercreateorder_14(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"test"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "自助验券")
    public void ms_c_unicashiercreateorder_15(String token,String userAgent,String caseId) throws Exception {
        ResponseMap response = null;
        if(envpath.contains("test")){
            Map<String,Object> result = CreateOrderUtil.ms_c_tgCreateOrder("ms_c_tgCreateOrder_01");
            String orderid = (String)result.get("orderid");
            List<String> coupons =  (List<String>)result.get("couponList");
            response = commonCallNew(token,userAgent,caseId,orderid,coupons.get(0));
        }else {
            response = commonCall(token,userAgent,caseId);
        }
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        if (flag){
            assertCall(response,expect,keyList);
            String orderId = response.getValueByJsonPath("SerializedId");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            orderDelete(orderId);
        }else{
			AssertUtil.assertJSONPathExists(response, "$");

        }
        flag = !flag;
    }

    @Test(groups = {"test"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = "自助验券，券已使用")
    public void ms_c_unicashiercreateorder_16(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        assertCall(response,expect,keyList);
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-09-13",updateTime = "2018-09-13",des = " bizorderType为30,bizorderid为空")
    public void ms_c_unicashiercreateorder_17(String token,String userAgent,String caseId){

        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        ArrayList<String> keyList = new ArrayList<String>(){{add("Content");}};
        assertCall(response,expect,keyList);
        //收银台有下单频率校验，10s超3次就会被拦截掉
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"P3","mapi"},dataProvider = "param")
    @MethodAnotation(author = "liukang",createTime = "2018-10-16",updateTime = "2018-10-16",des = "智能支付买单")
    public void ms_c_unicashiercreateorder_18(String token,String userAgent,String caseId){
        ResponseMap response = commonCall(token,userAgent,caseId);
        JSONObject expect = (JSONObject) DBDataProvider.getExpectResponse(_APIPATH,caseId);
        log.info(response.getResponseBody());
        ArrayList<String> keyList = new ArrayList<String>(){{add("Message");}};
        if (flag){
            assertCall(response,expect,keyList);
            String orderId = response.getValueByJsonPath("SerializedId");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            orderDelete(orderId);
        }else{
			AssertUtil.assertJSONPathExists(response, "$");

        }
        flag = !flag;
    }

    public ResponseMap commonCall(String token, String userAgent, String caseId){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        return response;
    }
    public void assertCall(ResponseMap response, JSONObject expect, ArrayList<String> keyList){
		AssertUtil.assertJSONPathExists(response, "$");
		AssertUtil.assertJsonEquals(JSON.parseObject(response.getResponseBody()),expect,JSONCompareMode.LENIENT,keyList,true);
    }

    private void orderDelete(String orderId) {
//        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.mapi,_HOST, _PORT,_APIPATH1,"ms_c_orderDelete_001");
        JSONObject request = DBDataProvider.getRequest(_APIPATH1,"ms_c_orderDelete_001");
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",dpToken);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",dpToken);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",dpClient);
        Map<String,String > param = new HashMap<String,String>();
        param.put("orderid",orderId);
		JsonPathUtil.setJsonPathVaule(request, "$.params.orderid",orderId);
		DBCaseRequestUtil.get("env.api.meishi.hui.host", request);
    }
//提供自助验券
    public ResponseMap commonCallNew(String token, String userAgent, String caseId, String orderId, String coupon){
        JSONObject request = DBDataProvider.getRequest(_APIPATH,caseId);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-token",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.pragma-newtoken",token);
		JsonPathUtil.setJsonPathVaule(request, "$.headers.User-Agent",userAgent);
        Map<String ,String> param =  new HashMap<>();
        param.put("receipt",coupon);
        param.put("bizorderid",orderId);
		JsonPathUtil.setJsonPathVaule(request, "$.body.orderid",orderId);
		JsonPathUtil.setJsonPathVaule(request, "$.body.receipt",coupon);
		JsonPathUtil.setJsonPathVaule(request, "$.body.bizorderid",orderId);
		ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.hui.host", request);
        return response;
    }
}
