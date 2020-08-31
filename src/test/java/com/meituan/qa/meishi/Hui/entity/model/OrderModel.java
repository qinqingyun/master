package com.meituan.qa.meishi.Hui.entity.model;

import lombok.Getter;
import lombok.Setter;
import org.stringtemplate.v4.ST;

/**
 * Created by buyuqi on 01/07/2020.
 */
@Getter
@Setter
public class OrderModel {
    String payToken;  //支付payToken
    String tradeNo;   //支付tradeNo
    String orderId;   //订单Id，新为主为48订单，老为主为20订单
    String serializedId;  //订单序列码
    String originAmount;  //订单原价
    String payAmount;     //支付金额，退款金额 单位分
    String schemeId;      //下单使用的方案ID
    String platformAmount;//下单使用的平台优惠
    String discountAmount; //下单使用的买单优惠(买单方案优惠)
    String merchantAmount;//下单使用的商家优惠（除买单方案外，例如商家券）
    String promoAmount;   //下单总共使用的优惠金额
    String mtShopId;  //美团门店id
    public OrderModel(){}
}
