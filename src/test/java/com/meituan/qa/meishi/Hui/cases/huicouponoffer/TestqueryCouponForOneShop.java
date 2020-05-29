package com.meituan.qa.meishi.Hui.cases.huicouponoffer;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.activity.service.HuiCouponQueryService;
import com.dianping.hui.couponoffer.dto.CouponOfferBO;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/RefundCheckService.canRefund",
        type = "pigeon",des="退款校验商家流水，是否可以进行退款")
@Slf4j
@PigeonAPI(methodName = "/HuiCouponQueryService/queryCouponForOneShop")
public class TestqueryCouponForOneShop {
    @PigeonAPI(url = "http://service.dianping.com/huiActivityService/huiCouponQueryService_1.0.0")
    private HuiCouponQueryService huiCouponQueryService;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-05-10",updateTime = "2018-05-10",des = "到餐订单可正常退款")
    public void ms_c_queryCouponForOneShop_01(JSONObject request, JSONObject expect){
        int  ship=request.getInteger("shopId");
        List<CouponOfferBO> result = huiCouponQueryService.queryCouponForOneShop(request.getInteger("shopId"));
        log.info("result：" + result);

    }

}

