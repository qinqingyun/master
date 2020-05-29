package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;

@Data
public class Gift {
    private String id;
    private String title;
    private double amount;
    private double orderpricelimit;
    private String cipher;
    private long type;
    private Object[] disablepromo;
    private long gifttype;
    private String productID;
    private long promotionProductID;
}
