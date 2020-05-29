package com.meituan.qa.meishi.Hui.cases.huicacheservice;


import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.common.bean.ShopEntity;
import com.dianping.mopay.service.HuiShopCervice;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/HuiShopCervice/getDpShop")
public class TestGetDpShop {
    @PigeonAPI(url = "http://service.dianping.com/huiCacheService/huiShopCervice")
    private HuiShopCervice huiShopCervice;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单可正常退款")
    public void ms_c__getDpShop_01(JSONObject request, JSONObject expect){
        ShopEntity result = huiShopCervice.getDpShop(request.getInteger("shopId"));
        log.info("result：" + result);

    }


}
