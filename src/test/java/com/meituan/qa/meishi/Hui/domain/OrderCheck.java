package com.meituan.qa.meishi.Hui.domain;

import com.beust.jcommander.internal.Lists;
import com.dianping.hui.order.response.QueryOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class OrderCheck {

    public void maitonOrder( int flag,QueryOrderResponse maitonQueryOrderResponse){

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
            //Assert.assertEquals(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue(),30);
            List<Integer> requestSet = Lists.newArrayList(30,50);
            Assert.assertTrue(requestSet.contains(maitonQueryOrderResponse.getOrderDTO().getOrderStatus().intValue()),"退款状态校验失败");

        }
    }
}
