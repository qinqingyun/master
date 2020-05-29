

package com.meituan.qa.meishi.Hui.dto.cashier;

import lombok.Data;

import java.util.List;

@Data
public class MaitonCashier {
    private String payCodeURL;
    private boolean showPayCode;
    private String warningDesc;
    private String feedbackURL;
    private long mainProductCode;
    private String enableNoDiscountInput;
    private String noDiscountInputURL;
    private String jsFunction;
    private long thirdPartyOrderType;
    private String thirdPartyOrderID;
    private long bizOrderType;
    private String bizOrderID;
    private String couponDescURL;
    private List<CouponProduct> couponProducts;
    private String couponDescTitle;
    private String mobileNo;
    private String productCodeString;
    private long isNewUser;
    private String[] jsRuleArray;
    private String depositID;
    private String bookRecordID;
    private long supportDPDiscount;
    private String shopName;
    private boolean hasAvailableDiscount;
    private String noDiscountDescList;
    private String bankDiscountDesc;
    private String deductAmount;
    private String totalAmountTips;
    private OpbVipInfo opbVipInfo;
    private String huiCouponInfo;

}

