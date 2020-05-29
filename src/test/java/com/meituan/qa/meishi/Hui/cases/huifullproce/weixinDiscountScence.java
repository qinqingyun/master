package com.meituan.qa.meishi.Hui.cases.huifullproce;

import com.dianping.hui.business.ecom.service.RefundOrderBaseService;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class weixinDiscountScence extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    private String bisid = CommonLoginUtil.merchantAPPLogin();//商家端登录后获取
    List<String> orderCreateResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";

    @PigeonAPI(url = "http://service.dianping.com/hui/huiBusinessService/refundOrderBaseService_1.0.0")
    private RefundOrderBaseService refundOrderBaseService;

    @Test(groups = "P3")
    @MethodAnotation(author = "qqy", createTime = "2019-09-06", updateTime = "2019-09-06", des =
            "微信小程序下单")
    public void ms_c_WeixinDiscountScenes_01() {


        int couponOfferId = CreateOrderUtil.loadUnifiedCashier(mtToken, mtClient, "ms_c_4Verify_loadUnifiedCashier_01");
//        Map<String, String> wxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(mtToken, "ms_c_wxacCreateOrder_101", couponOfferId, null);


//        Map<String, String> dpwxaOrderCreateResult = CreateOrderUtil.wxaCreateOrder(dpToken, "ms_c_wxacCreateOrder_001", couponOfferId, null);


//        Map<String, String> dpwxaOrderCreateResult_01 = CreateOrderUtil.wxaCreateOrder(dpToken, "ms_c_wxacCreateOrder_018", 0, "+xqrtEinhODeoGXFchLsqXC3QFH9y5cHj+Mp25v5S298EJ6l4A84wwDVJZmu01DZ");


    }
}
