package com.meituan.qa.meishi.Hui.cases.huiactivitydemoteweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.mt.poi.service.thrift.api.*;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@Deprecated
@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/querypoiinfos/getpayinfo",
        type = "thrift",des="查询闪惠优惠以及买单入口")
@Slf4j
@ThriftAPI(methodName = "/hui/querypoiinfos/getpayinfo")
public class TestGetPayInfo {

    private String _APIPATH = "/hui/querypoiinfos/getpayinfo";
//    private String _CASEFOLDER = "Hui/huiactivitydemoteweb/getpayinfo";

	@ThriftAPI(appkey = "hui-activity-demote-web")
    PayPromoService.Iface payPromoService;

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-01-30",updateTime = "2018-01-30",des = "获取门店优惠信息")
    public void ms_c_getPayInfo_001(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        PayData payData = promoInfoResDo.getPayInfo().getData().get(0);
        List<PayData> payDatas = promoInfoResDo.getPayInfo().getData();
        Iterator<PayData> iterator = payDatas.iterator();
        while (iterator.hasNext()){
            log.info(iterator.next().toString());
        }
        int sale = payData.getSales();
        int halfYearSales = payData.getHalfYearSales();
        log.info("sales: "+ sale +";halfYearSales: " + halfYearSales);
        Assert.assertTrue(sale > 10,"目前销量为："+ sale);
        Assert.assertTrue(halfYearSales > 0 ,"获得的半年销量为" + halfYearSales);


    }

    @Test(groups = {"all"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-01-30",updateTime = "2018-01-30",des = "门店没有优惠信息")
    public void ms_c_getPayInfo_002(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        Assert.assertEquals(promoInfoResDo.getCode(),3001);
        Assert.assertTrue("shop has no coupon".equals(promoInfoResDo.getErrMsg()));

    }

    @Test(groups = {"all"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-01-30",updateTime = "2018-01-30",des = "shopid错误")
    public void ms_c_getPayInfo_003(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        Assert.assertEquals(promoInfoResDo.getCode(),3001);
        Assert.assertTrue("shop has no coupon".equals(promoInfoResDo.getErrMsg()));

    }
    //此case只做测试验证，因为线上跑case若有人在门店买单超过1单就会报错，而且不跑此case无影响
	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "半年销量为0")
    public void ms_c_getPayInfo_004(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        PayData payData = promoInfoResDo.getPayInfo().getData().get(0);
        int halfYearSales = payData.getHalfYearSales();
        Assert.assertTrue(halfYearSales == 0,"获得的半年销量为：" +halfYearSales);
    }
//以下为poi领券测试
	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "只有商家券")
    public void ms_c_getPayInfo_005(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        List<PoiCampaign> poiCampaigns = promoInfoResDo.getPayInfo().getPoiCampaign();

        Iterator<PoiCampaign> iterator = poiCampaigns.iterator();
        while (iterator.hasNext()){
            log.info(iterator.next().toString());
        }
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "只有会员券")
    public void ms_c_getPayInfo_006(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        List<PoiCampaign> poiCampaigns = promoInfoResDo.getPayInfo().getPoiCampaign();

        Iterator<PoiCampaign> iterator = poiCampaigns.iterator();
        while (iterator.hasNext()){
            log.info(iterator.next().toString());
        }
    }

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "既有会员券又有商家券")
    public void ms_c_getPayInfo_007(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        log.info(promoInfoResDo.toString());
        List<PoiCampaign> poiCampaigns = promoInfoResDo.getPayInfo().getPoiCampaign();

        Iterator<PoiCampaign> iterator = poiCampaigns.iterator();
        while (iterator.hasNext()){
            log.info(iterator.next().toString());
        }
    }

    @Test(groups = {"all"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-07-16",updateTime = "2018-07-16",des = "新买单半年销量")
    public void ms_c_getPayInfo_008(JSONObject request, JSONObject expect) {
        PromoInfoResDo promoInfoResDo = callService(request);
        PayData payData = promoInfoResDo.getPayInfo().getData().get(0);
        List<PayData> payDatas = promoInfoResDo.getPayInfo().getData();
        Iterator<PayData> iterator = payDatas.iterator();
        while (iterator.hasNext()){
            log.info(iterator.next().toString());
        }
        int halfYearSales = payData.getHalfYearSales();
        log.info("halfYearSales: " + halfYearSales);
        Assert.assertTrue(halfYearSales > 0 ,"获得的半年销量为" + halfYearSales);
    }


    private PromoInfoResDo callService(JSONObject request){
        //        PromoInfoReqDo promoInfoReqDo =new PromoInfoReqDo();
//        try {
//            requests.setThriftRequestArgs(promoInfoReqDo);
//            requests.setThriftRequest();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		PromoInfoReqDo promoInfoReqDo = JSON.parseObject(request.getJSONObject("promoInfoReqDo").toString(),PromoInfoReqDo.class);
        PromoInfoResDo promoInfoResDo = null;
        try {
            promoInfoResDo = payPromoService.getPayInfo(promoInfoReqDo);
        } catch (TException e) {
            e.printStackTrace();
        }
        return promoInfoResDo;
    }
}
