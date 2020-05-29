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
//import com.sankuai.web.trade.client.request.RefundAuditRequest;
//import com.sankuai.web.trade.client.response.RefundResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/refundServiceI.refundAudit",
//        type = "thrift",des="交易平台用户发货后商家审核接口（已发货退货退款的是否确认退款申请部分）" +
//        "文档：https://km.sankuai.com/page/109497285")
//@Slf4j
//public class TestRefundAudit {
//	@ThriftAPI(appkey = "com.sankuai.web.tradeplatform.refund")
//    RefundServiceI refundServiceI;
//
//    RefundResponse refundResponse;
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    String _CASEPATH = "/refundServiceI.refundAudit";
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "商家允许发货")
//    public void ms_c_refundAudit_1(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_1",6115361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//        Assert.assertTrue(refundResponse.getData().getStatus() == 12);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "商家拒绝发货")
//    public void ms_c_refundAudit_2(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_2",7070361L);
//        Assert.assertTrue(0 == refundResponse.getErrCode());
//        Assert.assertTrue(refundResponse.isSuccess());
//        Assert.assertTrue(refundResponse.getData().getRefundOrderId()>0);
//        Assert.assertTrue(refundResponse.getData().getStatus() == 82);
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "refundOrderId小于等于0")
//    public void ms_c_refundAudit_3(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_3");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "refundOrderId不存在")
//    public void ms_c_refundAudit_4(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_4");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("查询数据异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "bizInfo为null")
//    public void ms_c_refundAudit_5(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_5");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "bizCode为空")
//    public void ms_c_refundAudit_6(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_6");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "tenantId 为空")
//    public void ms_c_refundAudit_7(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_7");
//        Assert.assertTrue(!refundResponse.isSuccess());
//        Assert.assertTrue("参数异常".equals(refundResponse.getErrMessage()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-11-19",updateTime = "2018-11-19",des = "订单退款类型不为退货退款")
//    public void ms_c_refundAudit_8(JSONObject request, JSONObject expect){
//        callService("ms_c_refundAudit_8",4082361L);
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
//        RefundAuditRequest refundAuditRequest = null;
//        String s = requests.getDataFromDBInFastjson().getString("thrift_param");
//        try {
//            refundAuditRequest = OBJECT_MAPPER.readValue(s, RefundAuditRequest.class);
//            if(refundOrderId > 0){
//                refundAuditRequest.setRefundOrderId(refundOrderId);
//            }
//        } catch (IOException e) {
//            log.info("转化json出错:" + e );
//        }
//        log.info("refundConfirmRequest:"  + JSONObject.toJSONString(refundAuditRequest));
//        refundResponse = refundServiceI.refundAudit(refundAuditRequest);
//        log.info(JSONObject.toJSONString("refundResponse:" + JSONObject.toJSONString(refundResponse)));
//
//    }
//}
