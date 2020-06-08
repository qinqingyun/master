package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.activity.demote.request.PromoInfoRequest;
import com.dianping.hui.activity.demote.response.PromoInfoResponse;
import com.dianping.hui.activity.demote.response.QueryPoiPromosResponse;
import com.dianping.hui.activity.demote.service.PayPromoService;
import com.dianping.hui.activity.demote.service.QueryPoiInfosService;
import com.dianping.hui.activity.demote.thriftdto.PayData;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by buyuqi on 2019/11/18.
 */

@ClassAnnotation(author = "byq", depart = "C", des = "从列表／搜索 到POI，买单信息展示。买单包括：原价买单、满减、折扣")
@Slf4j

public class TestMTHuiInfo {
    @ThriftAPI(appkey = "hui-activity-demote-web",localAppkey = "com.sankuai.meishi.qa.capicase")
    QueryPoiInfosService queryPoiInfosService;

    @ThriftAPI(appkey = "hui-activity-demote-web",localAppkey = "com.sankuai.meishi.qa.capicase")
    PayPromoService payPromoService;

    @Test(groups = {"P2"})
    @MethodAnotation(author = "byq", createTime = "2019-11-18",  des = "美团列表->poi详情，7折买单&&限制时间")
    public void ms_c_MTHuiInfo_01()throws TException{
        //1。美团列表具有买单标签
        String _APIPATH_LIST = "/queryPoiInfosService.queryPoiPromos";
        String caseIdList = "ms_c_originalScenesInfo_01";

        JSONObject obj = DBDataProvider.getRequest(_APIPATH_LIST,caseIdList);
        Integer poiId = obj.getInteger("poiId");
        List<Integer> requests = Arrays.asList(poiId);
        QueryPoiPromosResponse response = queryPoiInfosService.queryPoiPromos(requests);
        Assert.assertEquals(response.getImgurl(),"https://p1.meituan.net/codeman/a43001c065622a9a9a5ef24ba9190c901262.png");

        //2。美团详情页，7折买单信息
        String _APIPATH_DETAIL = "/payPromoService.getPayInfo";
        String caseIdDetail = "ms_c_originalScenesInfo_01";

        JSONObject obj_detail = DBDataProvider.getRequest(_APIPATH_DETAIL,caseIdDetail);
        PromoInfoRequest promoInfoRequest = new PromoInfoRequest();
        promoInfoRequest.setShopId(obj_detail.getInteger("shopId"));
        promoInfoRequest.setCityId(obj_detail.getInteger("cityId"));
        promoInfoRequest.setUserId(obj_detail.getInteger("userId"));
        promoInfoRequest.setAppVersion(obj_detail.getString("appVersion"));
        promoInfoRequest.setPlatform(obj_detail.getInteger("platform"));
        PromoInfoResponse res = payPromoService.getPayInfo(promoInfoRequest);
        try {
            List<String> discountList = res.getPayInfo().getData().stream().map(PayData::getDiscount).collect(Collectors.toList());
            String title = res.getPayInfo().getData().get(0).getTitle();
            long endTime = res.getPayInfo().getData().get(0).getEndTime();
            log.info("title:{}" + title + ";endTime:{}" + endTime);
            Assert.assertTrue(title.equals("7折"));
        }catch (Exception e){
            System.out.println("无买单信息");
        }
    }

    @Test
    @MethodAnotation(author = "byq", createTime = "2019-11-18",  des = "美团列表->poi详情，满减买单10-5")
    public void ms_c_MTHuiInfo_02()throws TException{
        //1。美团列表具有买单标签
        String _APIPATH_LIST = "/queryPoiInfosService.queryPoiPromos";
        String caseIdList = "ms_c_MTHuiInfo_02";

        JSONObject obj = DBDataProvider.getRequest(_APIPATH_LIST,caseIdList);
        Integer poiId = obj.getInteger("poiId");
        List<Integer> requests = Arrays.asList(poiId);
        QueryPoiPromosResponse response = queryPoiInfosService.queryPoiPromos(requests);
        log.info(response.getImgurl());
        Assert.assertEquals(response.getImgurl(),"https://p1.meituan.net/codeman/a43001c065622a9a9a5ef24ba9190c901262.png");

        //2。美团详情页，满减买单信息
        String _APIPATH_DETAIL = "/payPromoService.getPayInfo";
        String caseIdDetail = "ms_c_MTHuiInfo_02";

        JSONObject obj_detail = DBDataProvider.getRequest(_APIPATH_DETAIL,caseIdDetail);
        PromoInfoRequest dealStockReqInfo = JSON.parseObject(obj_detail.toString(), PromoInfoRequest.class);
        PromoInfoResponse res = payPromoService.getPayInfo(dealStockReqInfo);
        try {
            List<String> discountList = res.getPayInfo().getData().stream().map(PayData::getDiscount).collect(Collectors.toList());
            Assert.assertTrue(discountList.contains("10-5"));
        }catch (Exception e){
            System.out.println("无买单信息");
        }
    }

    @Test
    @MethodAnotation(author = "byq", createTime = "2019-11-18",  des = "美团列表->poi详情，买单原价")
    public void ms_c_MTHuiInfo_03()throws TException{
        //1。美团列表具有买单标签
        String _APIPATH_LIST = "/queryPoiInfosService.queryPoiPromos";
        String caseIdList = "ms_c_originalScenesInfo_03";

        JSONObject obj = DBDataProvider.getRequest(_APIPATH_LIST,caseIdList);
        Integer poiId = obj.getInteger("poiId");
        List<Integer> requests = Arrays.asList(poiId);
        QueryPoiPromosResponse response = queryPoiInfosService.queryPoiPromos(requests);
        Assert.assertEquals(response.getImgurl(),"https://p1.meituan.net/codeman/a43001c065622a9a9a5ef24ba9190c901262.png");

        //2。美团详情页，原价买单信息
        String _APIPATH_DETAIL = "/payPromoService.getPayInfo";
        String caseIdDetail = "ms_c_originalScenesInfo_03";

        JSONObject obj_detail = DBDataProvider.getRequest(_APIPATH_DETAIL,caseIdDetail);
        PromoInfoRequest promoInfoRequest = new PromoInfoRequest();
        promoInfoRequest.setShopId(obj_detail.getInteger("shopId"));
        promoInfoRequest.setCityId(obj_detail.getInteger("cityId"));
        promoInfoRequest.setUserId(obj_detail.getInteger("userId"));
        promoInfoRequest.setAppVersion(obj_detail.getString("appVersion"));
        promoInfoRequest.setPlatform(obj_detail.getInteger("platform"));
        PromoInfoResponse res = payPromoService.getPayInfo(promoInfoRequest);
        try {
            String title = res.getPayInfo().getTitle();
            log.info("买单信息："+title);
            Assert.assertTrue(title.equals("在线买单"));
        }catch (Exception e){
            log.info("无买单信息");
        }
    }
}
