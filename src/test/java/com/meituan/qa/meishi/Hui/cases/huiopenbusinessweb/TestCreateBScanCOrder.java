package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.order.request.EcomCreateBScanCOrderReq;
import com.dianping.hui.openbusiness.order.response.EcomCreateBScanCOrderResp;
import com.dianping.hui.openbusiness.order.service.EcomBScanCService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/EcomBScanCService.createBScanCOrder",
        type = "pigeon",des="B扫C，B端创单")
@Slf4j
public class TestCreateBScanCOrder {
    private String _APIPATH = "/EcomBScanCService.createBScanCOrder";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@ThriftAPI(appkey = "com.dianping.hui.openbusiness.order.service.EcomBScanCService")
    private EcomBScanCService ecomBScanCService;

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "美团团购券创单")
    public void ms_c_createBScanCOrder_01(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_01","65DC525DC1A8190D666B5A2C3C8162BE","134582463817","134582463817");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 10);
    }

    //美团商家券stype为1（商家券）
    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-10",updateTime = "2018-10-10",des = "美团商家券创单")
    public void ms_c_createBScanCOrder_02(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_02","BB42298E761224FB7563F589623D11EC","120000888025093");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 10);
    }
    //点评商家券stype为100（商家新券）
    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-10",updateTime = "2018-10-10",des = "点评商家券创单")
    public void ms_c_createBScanCOrder_02_1(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_02_1","B59125EA5AABCD1FB3C983F797C80B8C","120000888025093");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 10);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-10",updateTime = "2018-10-10",des = "点评团购券创单")
    public void ms_c_createBScanCOrder_03(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_03","86D603F29E3BBD28BB3D7A7DED7C74F9","172768967953","172768967953","028785267700");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 10);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "AuthString传递不存在值")
    public void ms_c_createBScanCOrder_04(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_04","21231AA16ECA52EF6B734B2ED19","284012167793","284012167793");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("未获取到B扫C相关信息".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "使用过的AuthString再次使用")
    public void ms_c_createBScanCOrder_05(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_05","2760954AEEAA16ECA52EF6B734B2ED19","284012167793","284012167793");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("module process before pay error".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 20);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-11",updateTime = "2018-10-11",des = "方案不是biztype为500的方案")
    public void ms_c_createBScanCOrder_06(){
        EcomCreateBScanCOrderResp response = callService("ms_c_createBScanCOrder_06","B96AE9659B480B420205A8D57C2ADCCE","316806892016","316806892016");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 10);
    }

    private EcomCreateBScanCOrderResp callService(String caseId, String mopayToken,String code,String... receipts) {
//        RequestsFromDB requests = new RequestsFromDB(_APIPATH,caseId,"");
        EcomCreateBScanCOrderReq req = null;
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        req = JSON.parseObject(request.toString(), EcomCreateBScanCOrderReq.class);
        req.setAuthString(mopayToken);
        req.setCode(code);
        req.setReceipts(Arrays.asList(receipts));
        log.info("request：" + JSONObject.toJSONString(req));
        return ecomBScanCService.createBScanCOrder(req);

    }
}
