package com.meituan.food.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class UrlUtils {

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UrlUtils#encode error.", e);
        }
    }

    private UrlUtils() {
    }
}
