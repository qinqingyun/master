package com.meituan.qa.meishi.Hui.cases.huicacheservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.cache.service.OrderNumService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/orderNumService.multiGetHalfYearSales",
        type = "pigeon",des="获取半年销量")
@Slf4j
@PigeonAPI(methodName = "/orderNumService.multiGetHalfYearSales")
public class TestMultiGetHalfYearSales {

    private String _APIPATH = "/orderNumService.multiGetHalfYearSales";
    @PigeonAPI(url =  "http://service.dianping.com/huiCacheService/orderNumService_1.0.0")
    OrderNumService orderNumService;

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取点评半年销量")
    public void ms_c_multiGetHalfYearSales_01(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            log.info("shopid:"+ result.getKey() + " sales:"+ result.getValue());
            Assert.assertTrue(result.getValue() > 0,"有门店缓存销量显示为0");
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取美团门店半年销量")
    public void ms_c_multiGetHalfYearSales_02(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            log.info("shopid:"+ result.getKey() + " sales:"+ result.getValue());
            Assert.assertTrue(result.getValue() > 0,"有门店缓存销量显示为0");
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "传入门店为空")
    public void ms_c_multiGetHalfYearSales_03(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        Assert.assertTrue(response.isEmpty());

    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "传入门店错误")
    public void ms_c_multiGetHalfYearSales_04(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            Assert.assertTrue(result.getValue() == 0);
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "传入门店类型错误")
    public void ms_c_multiGetHalfYearSales_05(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            Assert.assertTrue(result.getValue() == 0);
        }
    }

    public Map<Integer,Integer> callService(JSONObject request){
        JSONArray jsonArray = request.getJSONArray("shopIds");
        List list = jsonArray.subList(0,jsonArray.size());
        Set<Integer> shopIds = new HashSet<Integer>(list);
        int shopType = request.getInteger("shopType");
        return orderNumService.multiGetHalfYearSales(shopIds,shopType);
    }

}
