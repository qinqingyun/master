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

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/orderNumService.findOrderNumByShopIds",
        type = "pigeon",des="获取门店销量")
@Slf4j
@PigeonAPI(methodName = "/orderNumService.findOrderNumByShopIds")
public class TestFindOrderNumByShopIds {

    private String _APIPATH = "/orderNumService.findOrderNumByShopIds";
    @PigeonAPI(url = "http://service.dianping.com/huiCacheService/orderNumService_1.0.0")
    OrderNumService orderNumService;

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取点评门店销量")
    public void ms_c_findOrderNumByShopIds_01(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            log.info("shopid:"+result.getKey() + " sales:"+result.getValue());
//            System.out.println("key:"+result.getKey() + " vaulue:"+result.getValue());
            Assert.assertTrue(result.getValue() > 0,"有门店销量显示为0");
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "正确获取美团门店销量")
    public void ms_c_findOrderNumByShopIds_02(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
            log.info("shopid:"+result.getKey() + " sales:"+result.getValue());
//            System.out.println("key:"+result.getKey() + " vaulue:"+result.getValue());
            Assert.assertTrue(result.getValue() > 0,"有门店销量显示为0");
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "门店id传入为空")
    public void ms_c_findOrderNumByShopIds_03(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        Assert.assertTrue(response.isEmpty());
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "门店id错误")
    public void ms_c_findOrderNumByShopIds_04(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
//            System.out.println("key:"+result.getKey() + " vaulue:"+result.getValue());
            Assert.assertTrue(result.getValue() == 0 );
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "shopType类型传入错误")
    public void ms_c_findOrderNumByShopIds_05(JSONObject request, JSONObject expect){
        Map<Integer,Integer> response = callService(request);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
//            System.out.println("key:"+result.getKey() + " vaulue:"+result.getValue());
            Assert.assertTrue(result.getValue() == 0 );
        }
    }

    @Test(groups = {"pigeon","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-09",updateTime = "2018-03-09",des = "findOrderNumByShopIds重构方法验证（方法默认为点评侧）")
    public void ms_c_findOrderNumByShopIds_006(JSONObject request, JSONObject expect){
//        RequestsFromDB<Set> requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_findOrderNumByShopIds_006");
        JSONArray jsonArray = request.getJSONArray("shopIds");
        List subList = jsonArray.subList(0,jsonArray.size());
        Set<Integer> shopIds = new HashSet<Integer>(subList);
        Map<Integer,Integer> response = orderNumService.findOrderNumByShopIds(shopIds);
        for(Map.Entry<Integer,Integer> result:response.entrySet()){
//            System.out.println("key:"+result.getKey() + " vaulue:"+result.getValue());
            Assert.assertTrue(result.getValue() > 0 ,"有门店销量显示为0");
        }
    }

    public Map<Integer,Integer> callService(JSONObject request){
        JSONArray jsonArray = request.getJSONArray("shopIds");
        List subList = jsonArray.subList(0,jsonArray.size());
        Set<Integer> shopIds = new HashSet<Integer>(subList);
        int shopType = request.getInteger("shopType");
        return orderNumService.findOrderNumByShopIds(shopIds,shopType);
    }
}
