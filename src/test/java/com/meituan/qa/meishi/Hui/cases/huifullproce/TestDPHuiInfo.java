package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.dianping.discountcenter.dto.BizCouponConfig;
import com.dianping.discountcenter.request.QueryBizCouponRequest;
import com.dianping.discountcenter.service.BizCouponAcquireService;
import com.dianping.hui.open.search.dto.HuiCouponDTO;
import com.dianping.hui.open.search.request.HuiCouponRequest;
import com.dianping.hui.open.search.service.HuiSearchService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by buyuqi on 2019/11/18.
 */

@ClassAnnotation(author = "byq", depart = "C", des = "从列表／搜索 到POI，买单信息展示。买单包括：原价买单、满减、折扣")
@Slf4j

public class TestDPHuiInfo {
    @PigeonAPI(url = "http://service.dianping.com/huiOpenSearchService/huiSearchService_1.0.0")
    private HuiSearchService huiSearchService;

    @PigeonAPI(url = "http://service.dianping.com/discountCenterService/bizCouponAcquire4AppService_1.0.0")
    private BizCouponAcquireService bizCouponAcquireService;

    @Test(groups = {"P2"})
    @MethodAnotation(author = "byq", createTime = "2019-11-19",  des = "点评搜索／列表->poi详情，7折买单&&限制时间")
    public void ms_c_DPHuiInfo_01()throws TException {
        /**1.搜索／列表买单信息校验**/
        HuiCouponRequest huiCouponReq = new HuiCouponRequest();
        Map creditMap = new HashMap();
        creditMap.put("uuid","1");
        huiCouponReq.setClientVersion("v33");
        List<Integer> shopIdList = Arrays.asList(24799161);
        huiCouponReq.setShopIds(shopIdList);
        huiCouponReq.setCreditContext(creditMap);
        Map<Integer, List<HuiCouponDTO>> req = huiSearchService.loadHuiCouponsByShops(huiCouponReq);
        String couponDesc = req.get(shopIdList.get(0)).get(0).getCouponDiscount().getCouponDesc();
        log.info(couponDesc);
        Assert.assertTrue(couponDesc.equals("7折"));

        /**2.详情买单信息校验**/
        QueryBizCouponRequest queryBizCouponReq  = new QueryBizCouponRequest();
        queryBizCouponReq.setShopId(24799161);
        queryBizCouponReq.setUserId(0);
        queryBizCouponReq.setDpId("0");
        List<String> resp = bizCouponAcquireService.findAllBizCouponConfigs(queryBizCouponReq).stream().map(BizCouponConfig::getTitle).collect(Collectors.toList());
        log.info(resp.toString());
        Assert.assertTrue(resp.toString().contains("全单折"));
    }

    @Test
    @MethodAnotation(author = "byq", createTime = "2019-11-19",  des = "点评搜索／列表->poi详情，满减买单10-5")
    public void ms_c_DPHuiInfo_02()throws TException {
        /**1.搜索／列表买单信息校验**/
        HuiCouponRequest huiCouponReq = new HuiCouponRequest();
        Map creditMap = new HashMap();
        creditMap.put("uuid","1");
        huiCouponReq.setClientVersion("v33");
        List<Integer> shopIdList = Arrays.asList(66526423);
        huiCouponReq.setShopIds(shopIdList);
        huiCouponReq.setCreditContext(creditMap);
        Map<Integer, List<HuiCouponDTO>> req = huiSearchService.loadHuiCouponsByShops(huiCouponReq);
        log.info("列表页买单信息：{}", JSON.toJSONString(req));
        String couponDesc = req.get(shopIdList.get(0)).get(0).getCouponDiscount().getCouponDesc();
        log.info("买单优惠信息：{}",couponDesc);
        Assert.assertTrue(couponDesc.contains("每满10减5元"));

        /**2.详情买单信息校验**/
        QueryBizCouponRequest queryBizCouponReq  = new QueryBizCouponRequest();
        queryBizCouponReq.setShopId(66526423);
        queryBizCouponReq.setUserId(0);
        queryBizCouponReq.setDpId("0");
        List<String> resp = bizCouponAcquireService.findAllBizCouponConfigs(queryBizCouponReq).stream().map(BizCouponConfig::getTitle).collect(Collectors.toList());
        log.info(resp.toString());
        Assert.assertTrue(resp.toString().contains("[满减自动化]"));
    }

    @Test
    @MethodAnotation(author = "byq", createTime = "2019-11-19",  des = "点评搜索／列表->poi详情，全单折买单")
    public void ms_c_DPHuiInfo_03()throws TException {
        /**1.搜索／列表买单信息校验
         * 结果没有买单相关信息**/
        HuiCouponRequest huiCouponReq = new HuiCouponRequest();
        Map creditMap = new HashMap();
        creditMap.put("uuid","1");
        huiCouponReq.setClientVersion("v33");
        List<Integer> shopIdList = Arrays.asList(24799161);
        huiCouponReq.setShopIds(shopIdList);
        huiCouponReq.setCreditContext(creditMap);
        Map<Integer, List<HuiCouponDTO>> req = huiSearchService.loadHuiCouponsByShops(huiCouponReq);
        String couponDesc = req.get(shopIdList.get(0)).get(0).getCouponDiscount().getCouponDesc();
        log.info(couponDesc);
        Assert.assertTrue(couponDesc.contains("7折"));

        /**2.详情买单信息校验**/
        QueryBizCouponRequest queryBizCouponReq  = new QueryBizCouponRequest();
        queryBizCouponReq.setShopId(24799161);
        queryBizCouponReq.setUserId(0);
        queryBizCouponReq.setDpId("0");
        List<String> resp = bizCouponAcquireService.findAllBizCouponConfigs(queryBizCouponReq).stream().map(BizCouponConfig::getTitle).collect(Collectors.toList());
        log.info(resp.toString());
        Assert.assertTrue(resp.toString().contains("[自动化全单折，勿动]"));
    }

}
