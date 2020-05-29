package com.meituan.qa.meishi.Hui.cases.huiactivityservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.open.search.dto.HuiCouponAndOrderNumDTO;
import com.dianping.hui.open.search.request.HuiCouponAndOrderNumRequest;
import com.dianping.hui.open.search.service.HuiSearchService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/huiSearchService.loadHuiCouponsAndOrderNumByShops",
        type = "pigeon",des="获取点评团购优惠列表买单半年销量https://wiki.sankuai.com/pages/viewpage.action?pageId=1391860231")
@Slf4j
@PigeonAPI(methodName = "/huiSearchService.loadHuiCouponsAndOrderNumByShops")
public class TestLoadHuiCouponsAndOrderNumByShops {

    private String _APIPATH = "/huiSearchService.loadHuiCouponsAndOrderNumByShops";
	@PigeonAPI(url = "http://service.dianping.com/huiOpenSearchService/huiSearchService_1.0.0")
    private HuiSearchService huiSearchService;

    @Test(groups = {"P3","pigeon"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "获取门店半年销量")
    public void ms_c_loadHuiCouponsAndOrderNumByShops_01(JSONObject request, JSONObject expect){
        Map<Integer, HuiCouponAndOrderNumDTO> response = callService(request);

        HuiCouponAndOrderNumDTO huiCouponAndOrderNumDTO = null;
        for(Map.Entry<Integer,HuiCouponAndOrderNumDTO> result:response.entrySet()){
            huiCouponAndOrderNumDTO = result.getValue();
            System.out.println("shopid:"+result.getKey() + " halfYearSales:"+huiCouponAndOrderNumDTO.getHalfYearSales());
            Assert.assertTrue(huiCouponAndOrderNumDTO.getHalfYearSales() > 0,"有门店缓存销量显示为0");
        }
    }

    //此case只做测试验证，因为线上跑case若有人在门店买单超过1单就会报错，而且不跑此case无影响
	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-03-15",updateTime = "2018-03-15",des = "半年销量为0")
    public void ms_c_loadHuiCouponsAndOrderNumByShops_02(JSONObject request, JSONObject expect){
        Map<Integer, HuiCouponAndOrderNumDTO> response = callService(request);

        HuiCouponAndOrderNumDTO huiCouponAndOrderNumDTO = null;
        for(Map.Entry<Integer,HuiCouponAndOrderNumDTO> result:response.entrySet()){
            huiCouponAndOrderNumDTO = result.getValue();
//            System.out.println("key:"+result.getKey() + " vaulue:"+huiCouponAndOrderNumDTO.getHalfYearSales());
            Assert.assertTrue(huiCouponAndOrderNumDTO.getHalfYearSales() == 0);
        }
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class) //暂时不支持故线上取消
    @MethodAnotation(author = "liukang",createTime = "2018-07-16",updateTime = "2018-07-16",des = "新买单半年销量（获取销量时会过滤掉原价买单，所以不会展示相应的销量）")
    public void ms_c_loadHuiCouponsAndOrderNumByShops_03(JSONObject request, JSONObject expect){
        Map<Integer, HuiCouponAndOrderNumDTO> response = callService(request);

        HuiCouponAndOrderNumDTO huiCouponAndOrderNumDTO = null;
        for(Map.Entry<Integer,HuiCouponAndOrderNumDTO> result:response.entrySet()){
            huiCouponAndOrderNumDTO = result.getValue();
            System.out.println("shopid:"+result.getKey() + " halfYearSales:"+huiCouponAndOrderNumDTO.getHalfYearSales());
            Assert.assertTrue(huiCouponAndOrderNumDTO.getHalfYearSales() > 0,"有门店缓存销量显示为0");
        }
    }

    private Map<Integer, HuiCouponAndOrderNumDTO> callService(JSONObject request){

		HuiCouponAndOrderNumRequest huiCouponAndOrderNumRequest = JSON.parseObject(request.getJSONObject("huiCouponAndOrderNumRequest").toString(),HuiCouponAndOrderNumRequest.class);
        return huiSearchService.loadHuiCouponsAndOrderNumByShops(huiCouponAndOrderNumRequest);
    }




}
