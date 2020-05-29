package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;

@Data
public class DeskCoupon {
    private String id;
    private long type;
    private String cipher;
    private double amount;
    private double orderpricelimit;
    private long discountType;
    private long[] disablepromo;
    private long promotionproductid;
}
