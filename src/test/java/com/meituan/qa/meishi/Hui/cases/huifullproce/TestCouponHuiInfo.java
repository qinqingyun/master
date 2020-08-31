package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.dianping.hui.open.request.MShopHuiCouponRequest;
import com.dianping.hui.open.response.MShopHuiCouponResponse;
import com.dianping.hui.open.search.dto.HuiCouponDTO;
import com.dianping.hui.open.search.request.HuiCouponRequest;
import com.dianping.hui.open.search.service.HuiSearchService;
import com.dianping.hui.open.service.HuiMCouponService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by buyuqi on 2019/11/18.
 */

@ClassAnnotation(author = "byq", depart = "C", des = "企业买单，从列表／搜索 到POI，买单信息展示。买单包括：原价买单、满减、折扣")
@Slf4j

public class TestCouponHuiInfo {
    @PigeonAPI(url = "http://service.dianping.com/huiOpenSearchService/huiSearchService_1.0.0")
    private HuiSearchService huiSearchService;

    @PigeonAPI(url = "http://service.dianping.com/hui/openService/huiMCouponService_1.0.0")
    private HuiMCouponService huiMCouponService;


    @Test(groups = {"P2"})
    @MethodAnotation(author = "byq", createTime = "2019-11-19",  des = "企业买单 搜索／列表->poi详情，买单原价")
    public void ms_c_couponHuiInfo_01()throws TException {
        /**1.搜索／列表买单信息校验**/
        HuiCouponRequest huiCouponRequest = new HuiCouponRequest();
        Map creditMap = new HashMap();
        creditMap.put("uuid","1");
        huiCouponRequest.setClientVersion("v33");
        List<Integer> shopIdList = Arrays.asList(2517868);
        huiCouponRequest.setShopIds(shopIdList);
        huiCouponRequest.setCreditContext(creditMap);
        Map<Integer, List<HuiCouponDTO>> respList = huiSearchService.loadFirmHuiCouponsByShops(huiCouponRequest);
        System.out.println(respList.get(shopIdList.get(0)));
        String couponDesc = respList.get(shopIdList.get(0)).get(0).getCouponDiscount().getCouponDesc();
        System.out.println(couponDesc);

        /**2.详情买单信息校验**/
        MShopHuiCouponRequest mShopHuiCouponRequest  = new MShopHuiCouponRequest();
        mShopHuiCouponRequest.setShopId(112274452);
        mShopHuiCouponRequest.setUserId((long)0);
        mShopHuiCouponRequest.setCityId(1);
        mShopHuiCouponRequest.setCouponBizType(400);

        MShopHuiCouponResponse respDetail = huiMCouponService.loadMShopEnterpriseHuiCoupons(mShopHuiCouponRequest);
        System.out.println(respDetail);
    }

}
