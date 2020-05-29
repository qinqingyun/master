package com.meituan.qa.meishi.Hui.cases.huiopenbusinessweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.openbusiness.mtpush.dto.MtPushDTO;
import com.dianping.hui.openbusiness.mtpush.req.MtSchemePushRequest;
import com.dianping.hui.openbusiness.mtpush.resp.MtSchemePushResponse;
import com.dianping.hui.openbusiness.mtpush.service.MtSchemePushServiceSync;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "wqf",depart = "C",apiName = "/MtSchemePushServiceSync.pushMtSchemeSync",
        type = "pigeon",des="供应链创建买单方案调用接口")
@Slf4j
public class TestPushMtSchemeSync {
    private String _APIPATH = "/EcomBScanCService.queryBScanCOrder";

	@ThriftAPI(appkey = "com.dianping.hui.openbusiness.mtpush.service.MtSchemePushServiceSync")
    private MtSchemePushServiceSync mtSchemePushServiceSync;

    MtSchemePushRequest req = new MtSchemePushRequest();

//    @Test(groups = {"P3", "pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "wqf", createTime = "2019-2-15", updateTime = "2019-2-15", des = "正常数据存储")
//    public void ms_c_queryBScanCOrder_01() {
//        MtSchemePushRequest mtSchemePushRequest = new MtSchemePushRequest();
//        String s = "{\"id\":1617733,\"name\":\"福永居烤鸭满100减10\",\"status\":1,\"beginTime\":1494567352,\"endTime\":1581740595,\"discount\":{\"type\":1,\"ratio\":null,\"full\":100,\"cut\":10,\"limit\":0,\"gift\":null,\"ticketValidPeriod\":null,\"ticketIssueThreshold\":null},\"rangeType\":0,\"noUseWeekday\":null,\"useTime\":[{\"startTime\":\"00:00\",\"endTime\":\"24:00\"}],\"noUseDate\":[],\"holidayAvailable\":1,\"scopeDesc\":\"酒水不可用、不与店内其他活动同享\",\"rate\":1,\"poiIds\":\"24353253\",\"poiList\":[{\"dpPoiId\":24353253,\"mtPoiId\":65358944}],\"sourceDataType\":0,\"dpSolutionId\":null,\"dpCouponOfferId\":null,\"dpShopIds\":[],\"contractId\":\"3bcd9259-d2f1-4113-a116-7617f1c67c67\",\"bizAcctId\":26413128,\"bdId\":1095410,\"businessUnit\":0,\"autoDelay\":1,\"mailPoiId\":null,\"mallPoi\":null,\"isShow\":2,\"dpShowEnable\":1,\"mainContractId\":null,\"isMultiPoiPay\":0,\"bankInfoId\":\"3d798220-9203-11e7-9ca6-00225479c578\",\"payPlanId\":\"1b8b8739-a920-414f-a787-c16bb688a0bf\",\"bankInfoPois\":{}}";
//        MtPushDTO mtPushDTO = JSON.parseObject(s, MtPushDTO.class);
//        mtSchemePushRequest.setMtPushDTO(mtPushDTO);
//        MtSchemePushResponse mtSchemePushResponse = mtSchemePushServiceSync.pushMtSchemeSync(mtSchemePushRequest);
//        log.info(JSONObject.toJSONString(mtSchemePushResponse));
//        String code =String.valueOf(200);
//        Assert.assertTrue(code.equals(mtSchemePushResponse.getResultCode()));
//    }

    @Test(groups = {"P3", "pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "wqf", createTime = "2019-2-15", updateTime = "2019-2-15", des = "支付商户号")
    public void ms_c_queryBScanCOrder_02(JSONObject request, JSONObject expect) {
        MtSchemePushRequest mtSchemePushRequest = new MtSchemePushRequest();
        String s = "{\"id\":1617733,\"name\":\"大大烤鸭满100减20\",\"status\":1,\"beginTime\":1494567352,\"endTime\":1581740595,\"discount\":{\"type\":1,\"ratio\":null,\"full\":100,\"cut\":10,\"limit\":0,\"gift\":null,\"ticketValidPeriod\":null,\"ticketIssueThreshold\":null},\"rangeType\":0,\"noUseWeekday\":null,\"useTime\":[{\"startTime\":\"00:00\",\"endTime\":\"24:00\"}],\"noUseDate\":[],\"holidayAvailable\":1,\"scopeDesc\":\"酒水不可用、不与店内其他活动同享\",\"rate\":1," +
                "\"poiIds\":\"57474787\",\"poiList\":[{\"dpPoiId\":57474787,\"mtPoiId\":78867677}],\"sourceDataType\":0,\"dpSolutionId\":null,\"dpCouponOfferId\":null,\"dpShopIds\":[],\"contractId\":\"3bcd9259-d2f1-4113-a116-7617f1c67c67\",\"bizAcctId\":23110098,\"bdId\":1095410,\"businessUnit\":0,\"autoDelay\":1,\"mailPoiId\":null,\"mallPoi\":null,\"isShow\":2,\"dpShowEnable\":1,\"mainContractId\":null,\"isMultiPoiPay\":0,\"bankInfoId\":\"3d798220-9203-11e7-9ca6-00225479c578\"," +
                "\"payPlanId\":\"1b8b8739-a920-414f-a787-c16bb688a0bf\",\"bankInfoPois\":{},\"partnerId\":33300,\"payMerchantNo\":44400,\"payMerchantName\":\"买单测试支付商户号\"}";
        MtPushDTO mtPushDTO = JSON.parseObject(s, MtPushDTO.class);
        mtSchemePushRequest.setMtPushDTO(mtPushDTO);
        Tracer.setCell("gray-release-dc-maidan");
        MtSchemePushResponse mtSchemePushResponse = mtSchemePushServiceSync.pushMtSchemeSync(mtSchemePushRequest);
        log.info("接口返回："+ JSONObject.toJSONString(mtSchemePushResponse));
        String code =String.valueOf(200);
        Assert.assertTrue(code.equals(mtSchemePushResponse.getResultCode()));
    }

//    @Test(groups = {"P3", "pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "wqf", createTime = "2019-2-15", updateTime = "2019-2-15", des = "支付商户号只有ID，没有名称")
//    public void ms_c_queryBScanCOrder_03() {
//        MtSchemePushRequest mtSchemePushRequest = new MtSchemePushRequest();
//        String ss = "{\"id\":1617733,\"name\":\"大大烤鸭满100减10\",\"status\":1,\"beginTime\":1494567352,\"endTime\":1581740595,\"discount\":{\"type\":1,\"ratio\":null,\"full\":100,\"cut\":10,\"limit\":0,\"gift\":null,\"ticketValidPeriod\":null,\"ticketIssueThreshold\":null},\"rangeType\":0,\"noUseWeekday\":null,\"useTime\":[{\"startTime\":\"00:00\",\"endTime\":\"24:00\"}],\"noUseDate\":[],\"holidayAvailable\":1,\"scopeDesc\":\"酒水不可用、不与店内其他活动同享\",\"rate\":1," +
//                "\"poiIds\":\"16008298\",\"poiList\":[{\"dpPoiId\":16008298,\"mtPoiId\":4644630}],\"sourceDataType\":0,\"dpSolutionId\":null,\"dpCouponOfferId\":null,\"dpShopIds\":[],\"contractId\":\"3bcd9259-d2f1-4113-a116-7617f1c67c67\",\"bizAcctId\":26413128,\"bdId\":1095410,\"businessUnit\":0,\"autoDelay\":1,\"mailPoiId\":null,\"mallPoi\":null,\"isShow\":2,\"dpShowEnable\":1,\"mainContractId\":null,\"isMultiPoiPay\":0,\"bankInfoId\":\"3d798220-9203-11e7-9ca6-00225479c578\"," +
//                "\"payPlanId\":\"1b8b8739-a920-414f-a787-c16bb688a0bf\",\"bankInfoPois\":{},\"partnerId\":11100,\"payMerchantNo\":33300,\"payMerchantName\":\"\"}";
//        MtPushDTO mtPushDTO = JSON.parseObject(ss, MtPushDTO.class);
//        mtSchemePushRequest.setMtPushDTO(mtPushDTO);
//        MtSchemePushResponse mtSchemePushResponse = mtSchemePushServiceSync.pushMtSchemeSync(mtSchemePushRequest);
//        log.info(JSONObject.toJSONString(mtSchemePushResponse));
//        String code =String.valueOf(200);
//        Assert.assertTrue(code.equals(mtSchemePushResponse.getResultCode()));
//    }
//
//    @Test(groups = {"P3", "pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "wqf", createTime = "2019-2-15", updateTime = "2019-2-15", des = "支付商户号没有ID，只有名称")
//    public void ms_c_queryBScanCOrder_04() {
//        MtSchemePushRequest mtSchemePushRequest = new MtSchemePushRequest();
//        String sss = "{\"id\":1617733,\"name\":\"大大烤鸭满100减10\",\"status\":1,\"beginTime\":1494567352,\"endTime\":1581740595,\"discount\":{\"type\":1,\"ratio\":null,\"full\":100,\"cut\":10,\"limit\":0,\"gift\":null,\"ticketValidPeriod\":null,\"ticketIssueThreshold\":null},\"rangeType\":0,\"noUseWeekday\":null,\"useTime\":[{\"startTime\":\"00:00\",\"endTime\":\"24:00\"}],\"noUseDate\":[],\"holidayAvailable\":1,\"scopeDesc\":\"酒水不可用、不与店内其他活动同享\",\"rate\":1," +
//                "\"poiIds\":\"16008298\",\"poiList\":[{\"dpPoiId\":16008298,\"mtPoiId\":4644630}],\"sourceDataType\":0,\"dpSolutionId\":null,\"dpCouponOfferId\":null,\"dpShopIds\":[],\"contractId\":\"3bcd9259-d2f1-4113-a116-7617f1c67c67\",\"bizAcctId\":26413128,\"bdId\":1095410,\"businessUnit\":0,\"autoDelay\":1,\"mailPoiId\":null,\"mallPoi\":null,\"isShow\":2,\"dpShowEnable\":1,\"mainContractId\":null,\"isMultiPoiPay\":0,\"bankInfoId\":\"3d798220-9203-11e7-9ca6-00225479c578\"," +
//                "\"payPlanId\":\"1b8b8739-a920-414f-a787-c16bb688a0bf\",\"bankInfoPois\":{},\"partnerId\":11000,\"payMerchantNo\":0,\"payMerchantName\":\"测试二级商户名称\"}";
//        MtPushDTO mtPushDTO = JSON.parseObject(sss, MtPushDTO.class);
//        mtSchemePushRequest.setMtPushDTO(mtPushDTO);
//        MtSchemePushResponse mtSchemePushResponse = mtSchemePushServiceSync.pushMtSchemeSync(mtSchemePushRequest);
//        log.info(JSONObject.toJSONString(mtSchemePushResponse));
//        String code =String.valueOf(200);
//        Assert.assertTrue(code.equals(mtSchemePushResponse.getResultCode()));
//    }
}