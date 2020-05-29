package com.meituan.qa.meishi.Hui.cases.huifullproce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.dianping.mopayprocess.refundflow.response.DirectRefundResponse;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.meituan.qa.meishi.Hui.domain.DifferentRecord;
import com.meituan.qa.meishi.Hui.domain.HuiRefund;
import com.meituan.qa.meishi.Hui.domain.OrderCheck;
import com.meituan.qa.meishi.Hui.domain.OrderDetail;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author:      buyuqi
 * Modified:    buyuqi
 * Date:        2020-01-10
 * 用例简介:     买单使用原价买单方案
 * 数据源:       shopId：65731456
 * 主要流程:     下单 -> 支付 -> 详情 -> 退款
 * 备注:        平台：美团小程序 ；买单方案：原价买单；退款方式：直接退款
 **/

@ClassAnnotation(author = "byq", depart = "C", des = "test环境app原价流程")
@Slf4j
public class TestmtWeixinOriginalScence extends TestDPLogin {
    private String tradeNo;
    private String payToken;
    private String orderId;
    private String serializedId;
    List<String> dpResult = new ArrayList();
    String cashierOverviewPath = "/hui/ajax/cashieroverview";
    String orderoverviewPath = "/hui/ajax/orderoverview";
    String cashierqueryPath = "/hui/ajax/cashierquery";
    String orderqueryPath = "/hui/ajax/orderquery";
    String platformPath="/platformPath";
    OrderCheck orderCheck=new OrderCheck();
    String  doubleWriteMode="NEW";

    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    DataCompareAssistService dataCompareAssistService;
    @ThriftAPI(appkey = "com.sankuai.mptrade.datacomparetool",localAppkey = "com.sankuai.meishi.qa.capicase")
    InvokeTaskServiceI invokeTaskServiceI;


    @Test(groups = "P1")
    @MethodAnotation(author = "byq", createTime = "2020-01-10", des = "普通下单(原价)")
    public void ms_c_mtWeixinoriginalScenes() throws Exception {
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(mtUserId+"_1");
        }

        DifferentRecord differentRecord = new DifferentRecord(dataCompareAssistService,invokeTaskServiceI);
        //1、输入金额创建订单（下单支付）
        Map<String, String> wxaOrderCreateResult = CreateOrderUtil.wxaMtCreateOrder(mtToken,"ms_c_wxOriginCreateOrder_01", 0, null,null);
        orderId = wxaOrderCreateResult.get("orderId");
        log.info("创单支付成功！orderId = " + orderId );

        //买单支付后校验
        QueryOrderResponse OrderResponse=checkLoop.getMaitonOrder(2,orderId);
        orderCheck.maitonOrder(2,OrderResponse);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        //订单详情页
        OrderDetail orderdetail= OrderDetail.builder().token(mtToken).caseId("ms_c_wxaDetail_001").orderId(orderId).build();
        orderdetail.dpWxOrderDetail();

        //直接退款
        HuiRefund huiRefund = HuiRefund.builder().refundFlowService(refundFlowService).orderId(Long.valueOf(orderId)).operator("qa-autocase").build();
        DirectRefundResponse response = huiRefund.superRefund();
        log.info("获取退款结果:{}", JSON.toJSONString(response));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(response));
        //Assert.assertEquals(jsonObject.getString("success"),"true");
        Assert.assertEquals(jsonObject.getString("errCode"),"0","发起退款失败");

        //买单侧退款校验
        QueryOrderResponse refundOrderResponse=checkLoop.getMaitonOrder(3,orderId);
        orderCheck.maitonOrder(3,refundOrderResponse);
    }

}
