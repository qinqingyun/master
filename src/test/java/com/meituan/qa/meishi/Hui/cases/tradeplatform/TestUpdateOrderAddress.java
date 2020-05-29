package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.ClassAnnotation;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.MethodAnotation;
//import com.sankuai.web.trade.client.api.AddressServiceI;
//import com.sankuai.web.trade.client.request.AddressUpdateRequest;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/AddressServiceI.updateOrderAddress",
//        type = "http",des="餐电商修改邮递地址接口" +
//        "文档：https://km.sankuai.com/page/78421950")
//@Slf4j
//public class TestUpdateOrderAddress {
//    int reslutResponse = -1;
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.order")
//    AddressServiceI addressService;
//
//    String CASEPATH = "/AddressServiceI.updateOrderAddress";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-09-21",updateTime = "2018-09-21",des = "修改地址")
//    public void ms_c_updateOrderAddress_1(JSONObject request, JSONObject expect){
//        callService("ms_c_updateOrderAddress_1",1000011001828L);
//        Assert.assertTrue(1 == reslutResponse);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-09-21",updateTime = "2018-09-21",des = "orderid小于等于0")
//    public void ms_c_updateOrderAddress_2(JSONObject request, JSONObject expect){
//        callService("ms_c_updateOrderAddress_1",0);
//        Assert.assertTrue(0 == reslutResponse);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-09-21",updateTime = "2018-09-21",des = "orderid不存在")
//    public void ms_c_updateOrderAddress_3(JSONObject request, JSONObject expect){
//        callService("ms_c_updateOrderAddress_1",9999L);
//        Assert.assertTrue(0 == reslutResponse);
//    }
//
//
//    private void callService(String caseId,long orderId){
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(CASEPATH,caseId,"");
//        AddressUpdateRequest addressUpdateRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        try {
//            addressUpdateRequest = OBJECT_MAPPER.readValue(s, AddressUpdateRequest.class);
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        addressUpdateRequest.setOrderId(orderId);
//        log.info("refundRequest:"  + JSONObject.toJSONString(addressUpdateRequest));
//        reslutResponse = addressService.updateOrderAddress(addressUpdateRequest);
//
//    }
//}
