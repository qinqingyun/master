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
//import com.sankuai.web.trade.client.api.RefundServiceI;
//import com.sankuai.web.trade.client.request.RefundConfirmRequest;
//import com.sankuai.web.trade.client.response.RefundResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/refundServiceI.refundConfirm",
//        type = "thrift",des="交易平台商家确认接口(未发货退款的确认退款，已发货仅退款的确认退款，已发货退货退款的确认收货)" +
//        "文档：https://km.sankuai.com/page/109540505")
//@Slf4j
//public class TestRefundConfirm {
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.refund")
//    RefundServiceI refundServiceI;
//
//    RefundResponse refundResponse;
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    String _CASEPATH = "/refundServiceI.refundConfirm";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "商家同意退款")
//    public void ms_c_refundConfirm_1(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_1",7101361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//        Assert.assertTrue(refundResponse.getData().getStatus() == 14);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "商家不同意退款")
//    public void ms_c_refundConfirm_2(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_2",7092361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getStatus() == 84);
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "refundOrderId小于等于0")
//    public void ms_c_refundConfirm_3(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_3");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "refundOrderId不存在")
//    public void ms_c_refundConfirm_4(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_4");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("查询数据异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "bizInfo为null")
//    public void ms_c_refundConfirm_5(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_5");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "bizCode为空")
//    public void ms_c_refundConfirm_6(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_6");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "tenantId 为空")
//    public void ms_c_refundConfirm_7(JSONObject request, JSONObject expect){
//        callService("ms_c_refundConfirm_7");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    private void callService(String caseId){
//        callService(caseId,0);
//    }
//
//    private void callService(String caseId,long refundOrderId){
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
////        RequestsFromDB requests = new RequestsFromDB(_CASEPATH,caseId,"");
//        RefundConfirmRequest refundConfirmRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        try {
//            refundConfirmRequest = OBJECT_MAPPER.readValue(s, RefundConfirmRequest.class);
//            if(refundOrderId > 0){
//                refundConfirmRequest.setRefundOrderId(refundOrderId);
//            }
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundConfirmRequest:"  + JSONObject.toJSONString(refundConfirmRequest));
//        refundResponse = refundServiceI.refundConfirm(refundConfirmRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//}
