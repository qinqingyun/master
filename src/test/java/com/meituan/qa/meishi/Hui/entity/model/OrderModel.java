package com.meituan.qa.meishi.Hui.entity.model;

import lombok.Getter;
import lombok.Setter;

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
    String payAmount;     //支付金额，单位分
    String refundAmount;  //退款金额，单位分
    String schemeId;      //下单使用的方案ID
    public OrderModel(){}
}
