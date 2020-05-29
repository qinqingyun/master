package com.meituan.qa.meishi.Hui.cases.huicouponoffer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.discountcenter.request.BizCouponJudgeRequest;
import com.dianping.discountcenter.response.BizCouponJudgeResponse;
import com.dianping.discountcenter.service.BizCouponJudgeServcie;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;


@ClassAnnotation(author = "liukang",depart = "C",apiName = "/BizCouponJudgeService.judgeCouponByShopAndUser",
        type = "http",des="下单时查询是否为智能支付优惠")
@Slf4j
@ThriftAPI(methodName = "/BizCouponJudgeService.judgeCouponByShopAndUser")
public class TestJudgeCouponByShopAndUser {
    private String _APIPATH = "/BizCouponJudgeService.judgeCouponByShopAndUser";
	@ThriftAPI(appkey = "com.dianping.discountcenter.service.BizCouponJudgeServcie")
    private BizCouponJudgeServcie bizCouponJudgeServcie;

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-07",updateTime = "2018-05-07",des = "门店只有智能支付原价买单方案")
    public void ms_c_bizCouponJudgeServcie_001(JSONObject request, JSONObject expect){
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_bizCouponJudgeServcie_001");
		BizCouponJudgeRequest bizCouponJudgeRequest = JSON.parseObject(request.getJSONObject("bizCouponJudgeRequest").toString(),BizCouponJudgeRequest.class);
        BizCouponJudgeResponse response = bizCouponJudgeServcie.judgeCouponByShopAndUser(bizCouponJudgeRequest);
        log.info(response.toString());
        Assert.assertTrue(response.getType() == 2);
        Assert.assertTrue(response.getSchemeId() == bizCouponJudgeRequest.getShopId());
        Assert.assertTrue(response.getCouponOfferId() != 0);

    }

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-07",updateTime = "2018-05-07",des = "门店有智能支付原价买单方案又存在买单优惠方案")
    public void ms_c_bizCouponJudgeServcie_02(JSONObject request, JSONObject expect){
        BizCouponJudgeResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getType() == 1);
        Assert.assertTrue(response.getSchemeId() == 0);
        Assert.assertTrue(response.getCouponOfferId() == 0);

    }

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-07",updateTime = "2018-05-07",des = "门店只有买单优惠方案")
    public void ms_c_bizCouponJudgeServcie_03(JSONObject request, JSONObject expect){
        BizCouponJudgeResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getType() == 1);
        Assert.assertTrue(response.getSchemeId() == 0);
        Assert.assertTrue(response.getCouponOfferId() == 0);
    }

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-07",updateTime = "2018-05-07",des = "门店没有优惠")
    public void ms_c_bizCouponJudgeServcie_04(JSONObject request, JSONObject expect){
        BizCouponJudgeResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getType() == 0);
        Assert.assertTrue(response.getSchemeId() == 0);
        Assert.assertTrue(response.getCouponOfferId() == 0);
    }

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-07",updateTime = "2018-05-07",des = "shopid小于等于0")
    public void ms_c_bizCouponJudgeServcie_05(JSONObject request, JSONObject expect){
        BizCouponJudgeResponse response = callService(request);
        log.info(response.toString());
        Assert.assertTrue(response.getType() == 0);
        Assert.assertTrue(response.getSchemeId() == 0);
        Assert.assertTrue(response.getCouponOfferId() == 0);
    }

    private BizCouponJudgeResponse callService(JSONObject request){
		BizCouponJudgeRequest bizCouponJudgeRequest = JSON.parseObject(request.getJSONObject("bizCouponJudgeRequest").toString(),BizCouponJudgeRequest.class);
        BizCouponJudgeResponse bizCouponJudgeResponse = bizCouponJudgeServcie.judgeCouponByShopAndUser(bizCouponJudgeRequest);
        return bizCouponJudgeResponse;
    }

}
