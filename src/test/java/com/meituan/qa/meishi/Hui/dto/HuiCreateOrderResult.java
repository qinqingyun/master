package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;

@Data
public class HuiCreateOrderResult  {
    private String payToken;
    private String tradeNo;
    private Long orderId;
    private String serializedId;
}
