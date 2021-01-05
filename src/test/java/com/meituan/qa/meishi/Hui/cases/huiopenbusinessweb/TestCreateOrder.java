package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateReq;
import com.dianping.hui.openbusiness.order.request.EcomOrderCreateResp;
import com.dianping.hui.openbusiness.order.service.EcomOrderCreateService;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;


/**
 * 大客下单api接口，文档：https://km.sankuai.com/page/335188001
 * @throws Exception
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "thrift",des="大客下单api接口")
@Slf4j
@PigeonAPI(methodName = "/EcomOrderCreateService/createOrder")
public class TestCreateOrder extends TestDPLogin {
    @PigeonAPI(url = "com.dianping.hui.openbusiness.order.service.EcomOrderCreateService")
    EcomOrderCreateService ecomOrderCreateService;

    @Parameters({ "DoubleWriteMode" })
    @BeforeClass
    void updateUserId(String doubleWriteMode) throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(mtUserId+"_1");
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        if( doubleWriteMode.equals("OLD")){
            LionUtil.setUserBlackList(mtUserId+"_1");
            LionUtil.setUserBlackList(dpUserId+"_0");
        }
    }
    @Test(groups = {"P1"})
    @MethodAnotation(author = "byq", createTime = "20200529", des = "美团侧正常下单")
    public void ms_c_createOrder_01() {
        EcomOrderCreateReq ecomOrderCreateReq = new EcomOrderCreateReq();
        ecomOrderCreateReq.setShopId(65731456);
        ecomOrderCreateReq.setOriginAmount(BigDecimal.valueOf(1));
        ecomOrderCreateReq.setUserAmount(BigDecimal.valueOf(1));
        ecomOrderCreateReq.setShopType(1);
        ecomOrderCreateReq.setUserId(859799548);
        ecomOrderCreateReq.setUserType(1);
        ecomOrderCreateReq.setUserAgent("123");
        ecomOrderCreateReq.setCityId(1);
        ecomOrderCreateReq.setFingerPrint("123");
        ecomOrderCreateReq.setIp("127.0.0.1");
        ecomOrderCreateReq.setMobile("15528302855");
        EcomOrderCreateResp order = ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info(JSON.toJSONString(order));
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200529", des = "点评侧正常下单")
    public void ms_c_createOrder_02(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧正常下单")
    public void ms_c_createOrder_03(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，shopId不传")
    public void ms_c_createOrder_04(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getTradeNo() == null && createResp.getPayToken() == null
                && createResp.getMaitonBaseResponse().getResultMessage().equals("参数错误"),"参数错误，下单失败");

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，shopId传0")
    public void ms_c_createOrder_05(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getTradeNo() == null && createResp.getPayToken() == null
                && createResp.getMaitonBaseResponse().getResultMessage().equals("参数错误") && createResp.getMaitonBaseResponse().getOutterResultMessage().equals("shop=0"),"参数错误，下单失败");

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，shopId传非买单门店")
    public void ms_c_createOrder_06(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getPayToken() == null);
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，originAmount不传")
    public void ms_c_createOrder_07(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("原价金额不正确"),"参数错误，下单失败");

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，originAmount传0")
    public void ms_c_createOrder_08(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getTradeNo() == null && createResp.getPayToken() == null
                && createResp.getMaitonBaseResponse().getResultMessage().equals("参数错误"),"参数错误，下单失败");

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userAmount不传,originAmount传1.11")
    public void ms_c_createOrder_09(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        if(doubleWriteMode.equals("OLD")){
            Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
        }

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userAmount传0,originAmount传0")
    public void ms_c_createOrder_10(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()==0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("原价金额不正确"),"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userAmount传值与originAmount不一致")
    public void ms_c_createOrder_11(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()==0,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userAgent不传")
    public void ms_c_createOrder_12(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getTradeNo() == null && createResp.getPayToken() == null
                && createResp.getMaitonBaseResponse().getOutterResultCode().equals("UserAgent为空"),"参数错误，下单失败");

    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userAgent传null")
    public void ms_c_createOrder_13(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("UserAgent为空"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userAgent传空字符串“”")
    public void ms_c_createOrder_14(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getTradeNo() == null && createResp.getPayToken() == null
                && createResp.getMaitonBaseResponse().getOutterResultCode().equals("UserAgent为空"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userId不传")
    public void ms_c_createOrder_15(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("userid小于0"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userId传0")
    public void ms_c_createOrder_16(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("userid小于0"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userType不传")
    public void ms_c_createOrder_17(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userType传非0，1的其他值")
    public void ms_c_createOrder_18(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，userType传0，shopType传1")
    public void ms_c_createOrder_19(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，userType传1，shopType传0")
    public void ms_c_createOrder_20(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，shopType不传")
    public void ms_c_createOrder_21(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，shopType传非0，1的其他值")
    public void ms_c_createOrder_22(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("shopType != userType"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，fingerPrint不传,非必传参数")
    public void ms_c_createOrder_23(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，ip不传")
    public void ms_c_createOrder_24(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("IP为空"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "美团侧，mobile不传")
    public void ms_c_createOrder_25(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 && createResp.getMaitonBaseResponse().getOutterResultCode().equals("手机号码为空"),"参数错误，下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，mobile传非手机号1122112211222")
    public void ms_c_createOrder_26(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20200603", des = "点评侧，cityid，lat，lng，noDiscountAmount，不传值")
    public void ms_c_createOrder_27(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20201009", des = "点评侧，带有优惠下单,全单折7折")
    public void ms_c_createOrder_28(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20201009", des = "美团侧，带有优惠下单,满减折扣")
    public void ms_c_createOrder_29(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20201009", des = "点评侧，带有优惠下单,优惠与type不匹配")
    public void ms_c_createOrder_30(JSONObject request, JSONObject expect) throws Exception {
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(dpUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId()>0 && createResp.getPayToken() != null && createResp.getTradeNo()!= null,"下单失败");
    }
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "byq", createTime = "20201009", des = "点评侧，带有优惠下单,优惠过期")
    public void ms_c_createOrder_31(JSONObject request, JSONObject expect) throws Exception {
        String  doubleWriteMode="OLD";
        updateUserId(doubleWriteMode);
        EcomOrderCreateReq ecomOrderCreateReq = JSON.parseObject(request.toString(), EcomOrderCreateReq.class);
        ecomOrderCreateReq.setUserId(Long.valueOf(mtUserId));
        log.info("正常下单请求参数:" + JSON.toJSONString(ecomOrderCreateReq));
        EcomOrderCreateResp  createResp= ecomOrderCreateService.createOrder(ecomOrderCreateReq);
        log.info("正常下单返回结果:" + JSON.toJSONString(createResp));
        Assert.assertTrue(createResp.getOrderId() == 0 ,"买单交易流程错误，下单失败");
    }
}