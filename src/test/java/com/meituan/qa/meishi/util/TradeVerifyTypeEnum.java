package com.meituan.qa.meishi.util;

import com.meituan.servicecatalog.api.annotations.TypeDoc;
import lombok.Getter;

/**
 * Created by liyuhua on 03/12/2019.
 */
@Getter
@TypeDoc(description = "新交易平台校验类型枚举")
public enum TradeVerifyTypeEnum {
    VERIFY_CREATE(1,"校验创建订单成功"),
    VERIFY_PAY(2,"校验支付成功"),
    VERIFY_DELIVER(3,"校验订单成单成功"),
    VERIFY_CHECK(4,"校验订单消费成功"),
    VERIFY_REFUND(5,"校验订单成单前退款成功"),
    VERIFY_CHECKED_REFUND(6,"校验订单已消费退款成功"),
    VERIFY_UNCHECKED_REFUND(7,"校验订单未消费退款成功"),
    VERIFY_REVOKE(8,"校验订单撤销消费成功"),
    UNKNOWN(-1,"未知");

    private final int status;
    private final String statusText;

    TradeVerifyTypeEnum(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    public static TradeVerifyTypeEnum getOrderStatusEnum(int status){
        for(TradeVerifyTypeEnum statusEnum : TradeVerifyTypeEnum.values()){
            if(statusEnum.getStatus() == status){
                return statusEnum;
            }
        }
        return UNKNOWN;
    }
}
