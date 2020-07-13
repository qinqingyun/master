package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueDetail;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.qa.meishi.Hui.cases.base.MaitonApi;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.Hui.domain.*;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.dto.HuiCreateOrderResult;
import com.meituan.qa.meishi.Hui.dto.MappingOrderIds;
import com.meituan.qa.meishi.Hui.dto.UseCard;
import com.meituan.qa.meishi.Hui.dto.cashier.CouponProduct;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.Task;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.thriftApi;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2020-07-12
 * 用例简介:     仅使用商家券0元单
 * 数据源:       poiId：95191712
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团侧 ；买单方案：原价买单；退款方式：直接退款；促销方式：使用商家券+平台券
 **/
@Slf4j
public class TestMTShopPromoAndCouponZeroOrder extends TestDPLogin  {

    private static String CASEID = "ms_c_hui_unicashiercreateorder_mtshop_04";

    //商家端登录后获取
    public static String cashierOverviewPath = "/hui/ajax/cashieroverview";
    public static String orderoverviewPath = "/hui/ajax/orderoverview";
    public static String cashierqueryPath = "/hui/ajax/cashierquery";
    public static String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();

    public static String LOADUNIFIEDCASHIER = "ms_c_mtshoploadUnifiedCashier_04";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;

    @ThriftAPI(desc = "购买域新老订单ID映射服务，包括：通过老ID查找新ID等", appkey = "com.sankuai.mptrade.buy.process", interfaceName = "com.meituan.nibtp.trade.client.buy.service.OrderMappingService")
    OrderMappingService orderMappingService;

    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;

    Integer poiId = 95191712;
    Integer source = 1;//代表了使用买单优惠+优惠券，0元单

    /**
     * coupon 金额5元
     * 提单时的cipher 多个使用 %23隔开
     * o6nT68Q0fiFN1Ov04inIQY0TYURST%2FiDjegrqsoweUHOAHeM0Zmz%2BZBUWVOCSwdrydqiNKTPs%2BuxJ0USCEM28U%2BsKm4%2BTKmgJm4esMqnB5w%3D%23ssKyhaTwI%2FXPrviN9Ha7990NpfKddu0%2FHuWVTxZdtjmJUT1BWPTEDCW7bZTgdej8LiE1ZHgyECyTp0rrbP4yXhYKt4914aYBGeugj9iQAo0e%2BSlShx%2BmRRreaSvwXMv3%235gg7O6x3yCWMV%2BinDIZVFjPoKPlhZTn7NZkR%2F6eftsVP1ZH%2BYDsDKN%2Fcbi787AgP8dwhSSGvwOF0aOqxlthMMQ%3D%3D
     * UnifiedCouponIssueRequest：{"userId":123344,"userType":"MT",operationToken:"26332572ACA5F1D2591E34B4B4AF4271","operator":"dengjia06","couponGroupIdList":[],"unifiedCouponGroupIdList":["549009064"]}
     */
    @Parameters({ "DoubleWriteMode" })
    @Test(groups = "P1")
    @MethodAnotation(author = "buyuqi", createTime = "2019-10-31", updateTime = "2019-10-31", des = "普通下单(原价)")
    public void ms_c_hui_mt_ShopPromoZeroOrder(String doubleWriteMode)  throws Exception {
        if (doubleWriteMode.equals("NEW"))
            LionUtil.setUserWriteList(mtbyqUserId + "_1");
        if (doubleWriteMode.equals("OLD"))
            LionUtil.setUserBlackList(mtbyqUserId + "_1");
        //1.查询可用商家券
        String id = "120000901026380";
        DeskCoupon deskCoupon = checkLoop.getShopCouponCipher(mtbyqToken, mtClient, "ms_c_hui_gethuipromodesk_02", id);
        if (deskCoupon == null) {
            //调用营销接口直接发券
            MaitonHongbaoTResponse maitonHongbaoTResponse = thriftApi.setShopPromo(mtbyqUserId, poiId);
            log.info("商家券发券结果：{}", JSON.toJSONString(maitonHongbaoTResponse));
            Optional<MaitonHongbaoTBean> detailOptional = maitonHongbaoTResponse.data.stream().findFirst();
            id = detailOptional.get().id;
            log.info("商家发券Id:{}" + id);
        }
        //下单前查询优惠
        deskCoupon = checkLoop.getShopCouponCipher(mtbyqToken, mtClient, "ms_c_hui_gethuipromodesk_01", id);
        Assert.assertTrue(deskCoupon != null, "获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        //2.查询可用平台券
        String couponid = "23738010020695727";
        deskCoupon = checkLoop.getCouponCipher(mtbyqToken, mtClient, "ms_c_hui_gethuipromodesk", couponid);
        if (deskCoupon == null) {
            //发平台券券
            UnifiedCouponIssueResponse unifiedCouponIssueResponse = thriftApi.setCouponPromo(mtbyqUserId, 763075395);
            log.info("平台券发券结果：{}", JSON.toJSONString(unifiedCouponIssueResponse));
            BigDecimal couponAmount = BigDecimal.ZERO;
            if (unifiedCouponIssueResponse.getResultCode() == 0) {
                Optional<UnifiedCouponIssueDetail> detailOptional = unifiedCouponIssueResponse.getResult().getResult().stream().findFirst();
                if (detailOptional.isPresent()) {
                    UnifiedCouponIssueDetail issueDetail = detailOptional.get();
                    couponAmount = issueDetail.getDiscountAmount();
                    couponid = issueDetail.getUnifiedCouponId();
                }
            }
            deskCoupon = checkLoop.getCouponCipher(mtbyqToken, mtClient, "ms_c_hui_gethuipromodesk", couponid);
            Assert.assertTrue(deskCoupon != null, "获取平台券失败，可能原因：调用平台券接口超时或者查券失败");
        }
        //3.加载优惠台
        LoadCashier loadCashier = LoadCashier.builder()
                .caseId(LOADUNIFIEDCASHIER)
                .token(mtbyqToken)
                .userAgent(mtClient).build();
        CouponProduct couponProduct = loadCashier.parseCouponOfferId().orElse(null);
        //4.创建订单
        HuiCreateOrderResult createResult = checkLoop.uniCashierCreateOrder(mtbyqToken, mtClient, CASEID, couponProduct, deskCoupon, source);
        String payToken = createResult.getPayToken();
        String serializedId = createResult.getSerializedId();
        Long orderId = createResult.getOrderId();
        String tradeNo = createResult.getTradeNo();
        Assert.assertNotNull(payToken, "下单失败");
        Assert.assertNotNull(tradeNo, "下单失败");

        log.info("创单成功！orderId = " + orderId + " serializedId = " + serializedId + " tradeNo = " + tradeNo
                + " payToken = " + payToken);

    }
}
