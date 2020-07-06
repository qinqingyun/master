package com.meituan.qa.meishi.Hui.util;
import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.toolchain.mario.util.MtraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import java.util.List;
/**
 * Created by buyuqi on 05/06/2020.
 */
@Slf4j
public class CheckOrderUtil extends TestBase {

    //调用老的交易平台接口校验订单状态是否是支付成功
    public static void checkOldOrderSystem(int flag,QueryOrderResponse maitonQueryOrderResponse){
        if (IS_CHECK_OLD_ORDER_SYSTEM) {
            log.info("*************开始校验老交易系统*************");
            MtraceUtil.generatTrace("老交易系统校验");
                Assert.assertTrue(maitonQueryOrderResponse != null,"maitonQueryOrderResponse为空");
                if (flag==1){
                    log.info("下单状态校验");
                    Assert.assertEquals(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue(),0,"下单状态校验失败");
                }

                if (flag==2){
                    log.info("支付状态校验");
                    Assert.assertEquals(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue(),10,"支付状态校验失败");
                }
                if(flag==3){
                    log.info("退款状态校验");
                    List<Integer> requestSet = Lists.newArrayList(30,50);
                    Assert.assertTrue(requestSet.contains(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue()),"退款状态校验失败");

                }
            log.info("*******************老交易系统校验通过****************");
        }
    }


    //调用新交易平台接口校验订单状态是否是支付成功
//    public static void checkNewOrderSystem(OrderModel orderModel, TradeVerifyTypeEnum verifyType) throws Exception{
//        log.info("****************开始校验新交易平台****************");
//        if (IS_CHECK_NEW_ORDER_SYSTEM) {
//            MtraceUtil.generatTrace("校验新交易平台");
//            PlatformCheckInfo.PlatformCheckInfo(Long.valueOf(orderModel.getOrderId()),verifyType, orderModel.getExpect());
//        }
//        log.info("****************新交易平台校验成功****************");
//    }





}
