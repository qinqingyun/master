package com.meituan.qa.meishi.Hui.dto.cashier;

import lombok.Data;

@Data
public class CouponProduct {
    private String unifiedID;
    private long productType;
    private String title;
    private String validPeriod;
    private String desc;
    private String couponValue;
    private long ticketID;
    private int couponID;
    private String originAmount;
    private String realAmount;
    private long dealID;
    private long dealGroupID;
    private long ownedDealCount;
    private String couponRule;
    private String discountConditionDo;
    private String unavailableDesc;
    private String subTitle;
    private String iconURL;
}
