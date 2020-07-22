package com.meituan.qa.meishi.Hui.entity.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by buyuqi on 01/07/2020.
 */
@Getter
@Setter
public class OrderModel {
    String payToken;
    String tradeNo;
    String orderId;
    String serializedId;
    String payAmount;
    String refundAmount;
    public OrderModel(){}
}
