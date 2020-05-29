package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.order.request.EcomQueryBScanCSchemeReq;
import com.dianping.hui.openbusiness.order.response.EcomQueryBScanCSchemeResp;
import com.dianping.hui.openbusiness.order.service.EcomBScanCService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;


@ClassAnnotation(author = "liukang",depart = "C",apiName = "/EcomBScanCService.queryBScanCScheme",
        type = "pigeon",des="获取B扫C方案")
@Slf4j
@ThriftAPI(methodName = "/EcomBScanCService.queryBScanCScheme")
public class TestQueryBScanCScheme {
    private String _APIPATH = "/EcomBScanCService.queryBScanCScheme";

	@ThriftAPI(appkey = "com.dianping.hui.openbusiness.order.service.EcomBScanCService")
    private EcomBScanCService ecomBScanCService;

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "团购券正常查询方案")
    public void ms_c_queryBScanCScheme_01(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_01","293924779951");
        log.info("response:" + JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getSchemeIdList().size() > 0);
        Assert.assertTrue(response.getStatus() == 1);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "商家券正常查询方案")
    public void ms_c_queryBScanCScheme_02(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_02","120000872974061");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 1);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "未生成过mopaytoken")
    public void ms_c_queryBScanCScheme_03(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_03","314977267572");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("未收到C端B扫C信息".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "商家未开通B扫C")
    public void ms_c_queryBScanCScheme_04(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_04","316806892016");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("未查到相应的买单方案".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }
    //此条未验证
    @Test()
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "商家开通B扫C后下掉活动")
    public void ms_c_queryBScanCScheme_05(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_05","284012167793");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue(!response.getAuthString().isEmpty());
        Assert.assertTrue(response.getStatus() == 0);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "类型传递错误")
    public void ms_c_queryBScanCScheme_06(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_06","284012167793");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("参数错误".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "code传递为空")
    public void ms_c_queryBScanCScheme_07(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_07",null);
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("参数错误".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }

    @Test(groups = {"P3","pigeon"})
    @MethodAnotation(author = "liukang",createTime = "2018-10-09",updateTime = "2018-10-09",des = "code传递错误不存在值")
    public void ms_c_queryBScanCScheme_08(){
        EcomQueryBScanCSchemeResp response = callService("ms_c_queryBScanCScheme_08","123");
        log.info(JSONObject.toJSONString(response));
        Assert.assertTrue("未收到C端B扫C信息".equals(response.getDesc()));
        Assert.assertTrue(response.getStatus() == 0);
    }

    private EcomQueryBScanCSchemeResp callService(String caseId,String code) {
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch(Exception e){
            log.error(e.getMessage());
        }
		EcomQueryBScanCSchemeReq req = JSON.parseObject(request.getJSONObject("ecomQueryBScanCSchemeReq").toString(),EcomQueryBScanCSchemeReq.class);
        req.setCode(code);
        log.info(JSONObject.toJSONString(req));
        return ecomBScanCService.queryBScanCScheme(req);

    }
}
