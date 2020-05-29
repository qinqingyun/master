package com.meituan.qa.meishi.Hui.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class CheckOrderType {
    private final static int INDEX = 54;
    private final static long FACTOR = (long) Math.pow(2, INDEX + 1) + (long) Math.pow(2, INDEX);
    private final static BigDecimal comparedOrderId = new BigDecimal("4000000000000000000");  // 新系统订单号为4开头，长度19位
   // private static ConfigUtil configUtil = new ConfigUtil();

    public static Boolean isNewOrder(String orderId) throws Exception {
        return new BigDecimal(orderId).compareTo(comparedOrderId) > 0;
    }

    public static Boolean isTradeOrder(Long orderId) throws Exception {
        if (isNewOrder(orderId.toString())) {
            return false;
        }
        return ((orderId & FACTOR) >> INDEX) == 2L;
    }

    public static Boolean isWechatOrder(String utmMedium) throws Exception {
        return StringUtils.equalsIgnoreCase(utmMedium, "WEIXINPROGRAM");
    }


}
