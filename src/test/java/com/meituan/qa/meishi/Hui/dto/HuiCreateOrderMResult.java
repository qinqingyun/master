package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;

@Data
public class HuiCreateOrderMResult {

    private String appVersion;
    private String callbackURL;
    private long code;
    private String degradeInfo;
    private boolean degradeStatus;
    private long isNative;
    private String msg;
    private long orderStatus;
    private long payOrderID;
    private String payPageHost;
    private String payToken;
    private String successURL;
    private String token;
    private String tradeNo;
    private long serializedOrderId;
}
