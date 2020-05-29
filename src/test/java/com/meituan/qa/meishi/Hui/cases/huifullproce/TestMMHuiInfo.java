package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.dianping.discountcenter.dto.BizCouponConfig;
import com.dianping.discountcenter.request.QueryBizCouponRequest;
import com.dianping.discountcenter.service.BizCouponAcquireService;
import com.dianping.hui.open.search.dto.MHuiCouponDTO;
import com.dianping.hui.open.search.request.MHuiCouponRequest;
import com.dianping.hui.open.search.service.HuiSearchService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by buyuqi on 2019/11/18.
 */

@ClassAnnotation(author = "byq", depart = "C", des = "M站 从列表／搜索 到POI，买单信息展示。买单包括：原价买单、满减、折扣")
@Slf4j

public class TestMMHuiInfo {
    @PigeonAPI(url = "http://service.dianping.com/huiOpenSearchService/huiSearchService_1.0.0")
    private HuiSearchService huiSearchService;

    @PigeonAPI(url = "http://service.dianping.com/discountCenterService/bizCouponAcquire4MMStationService_1.0.0")
    private BizCouponAcquireService bizCouponAcquireService;


    @Test(groups = {"P2"})
    @MethodAnotation(author = "byq", createTime = "2019-11-19",  des = "M站 搜索／列表->poi详情，原价买单")
    public void ms_c_MMHuiInfo_01()throws TException {
        /**1.搜索／列表买单信息校验**/
        MHuiCouponRequest mHuiCouponRequest = new MHuiCouponRequest();
        Long userId = (long)0;
        mHuiCouponRequest.setUserId(userId);
        List<Integer> shopIdList = Arrays.asList(17706326);
        mHuiCouponRequest.setShopIds(shopIdList);
        Map<Integer, List<MHuiCouponDTO>> respList = huiSearchService.loadMSearchHuiCouponsByShops(mHuiCouponRequest);
        String couponDesc = respList.get(shopIdList.get(0)).get(0).getCouponDesc();
        log.info(couponDesc);
        Assert.assertEquals(couponDesc,"支持手机原价买单");

        /**2.详情买单信息校验**/
        QueryBizCouponRequest queryBizCouponReq  = new QueryBizCouponRequest();
        queryBizCouponReq.setShopId(17706326);
        queryBizCouponReq.setUserId(0);
        queryBizCouponReq.setDpId("0");
        List<String> respDetail = bizCouponAcquireService.findAllBizCouponConfigs(queryBizCouponReq).stream().map(BizCouponConfig::getTitle).collect(Collectors.toList());
        log.info(respDetail.toString());
        Assert.assertTrue(respDetail.toString().contains("原价买单方案"));
    }

}
