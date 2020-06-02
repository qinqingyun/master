package com.meituan.qa.meishi.Hui.entity;

import com.meituan.servicecatalog.api.annotations.TypeDoc;
import lombok.Getter;

/**
 * Created by buyuqi on 29/05/2020.
 */
@Getter
@TypeDoc(description = "订单来源枚举")
public enum OrderSourceEnum {

    DPApp(1,"点评App"),
    MTApp(2,"美团app"),
    MTH5(3,"美团H5"),
    DPM(4,"点评M站"),
    DPWx(5,"点评微信小程序"),
    MTWx(6,"美团微信小程序"),
    UNKNOWN(-1,"未知");

    private final int sourceName;
    private final String text;

    OrderSourceEnum(int source, String text) {
        this.sourceName = source;
        this.text = text;
    }

    public static OrderSourceEnum getOrderSourceEnum(int source){
        for(OrderSourceEnum statusEnum : OrderSourceEnum.values()){
            if(statusEnum.getSourceName() == source){
                return statusEnum;
            }
        }
        return UNKNOWN;
    }
}
