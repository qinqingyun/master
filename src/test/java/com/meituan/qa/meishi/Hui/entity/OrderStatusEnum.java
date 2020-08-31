package com.meituan.qa.meishi.Hui.entity;

import com.meituan.servicecatalog.api.annotations.TypeDoc;
import lombok.Getter;

/**
 * Created by buyuqi on 29/05/2020.
 */
@Getter
@TypeDoc(description = "订单状态枚举")
public enum OrderStatusEnum {

    下单成功(1,"下单成功"),
    支付成功(2,"支付成功"),
    退款成功(3,"退款成功"),
    UNKNOWN(-1,"未知");


    private final int statusName;
    private final String text;

    OrderStatusEnum(int source, String text) {
        this.statusName = source;
        this.text = text;
    }

    public static OrderStatusEnum getOrderSourceEnum(int source){
        for(OrderStatusEnum statusEnum : OrderStatusEnum.values()){
            if(statusEnum.getStatusName() == source){
                return statusEnum;
            }
        }
        return UNKNOWN;
    }
}
