package com.meituan.qa.meishi.Hui.cases.huicacheservice;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.cache.service.OrderNumService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/orderNumService.findOrderNumByShopId",
        type = "pigeon",des="获取单门店销量")
@Slf4j
@PigeonAPI(methodName = "/orderNumService.findOrderNumByShopId")
public class TestFindOrderNumByShopId {
    private String _APIPATH = "/orderNumService.findOrderNumByShopId";
    @PigeonAPI(url = "http://service.dianping.com/huiCacheService/orderNumService_1.0.0")
    OrderNumService orderNumService;

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取点评单门店销量")
    public void ms_c_findOrderNumByShopId_01(JSONObject request, JSONObject expect){
        int response = callService(request);
        log.info(String.valueOf(response));
        Assert.assertTrue(response > 0 ,"得到的门店销量为0");
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取美团单门店销量")
    public void ms_c_findOrderNumByShopId_02(JSONObject request, JSONObject expect){
        int response = callService(request);
        log.info(String.valueOf(response));
        Assert.assertTrue(response > 0 ,"得到的门店销量为0");
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "门店id错误")
    public void ms_c_findOrderNumByShopId_03(JSONObject request, JSONObject expect){
        int response = callService(request);
        Assert.assertTrue(response == 0 );
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "门店类型错误")
    public void ms_c_findOrderNumByShopId_04(JSONObject request, JSONObject expect){
        int response = callService(request);
        Assert.assertTrue(response == 0 );
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "重构方法的验证（默认为点评侧）")
    public void ms_c_findOrderNumByShopId_005(JSONObject request, JSONObject expect){
//        RequestsFromDB<Set> requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_findOrderNumByShopId_005");
        int shopId = request.getInteger("shopId");
        int response = orderNumService.findOrderNumByShopId(shopId);
        log.info(String.valueOf(response));
        Assert.assertTrue(response > 0 );
    }

    public int callService(JSONObject request){
        int shopId = request.getInteger("shopId");
        int shopType = request.getInteger("shopType");
        return orderNumService.findOrderNumByShopId(shopId,shopType);
    }
}
