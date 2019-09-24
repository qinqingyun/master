package com.meituan.food.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
;

public final class YunTuBa {

    public static HttpHeaders getAuthHeader(String path, String method) {
        HttpHeaders  headers = new HttpHeaders();
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = df.format(new Date()) + " GMT";
        String encryptStr = String.format("%s %s\n%s", method, path, date);
        String sign = hmacSHA1("yuntu&daodian123", encryptStr);
        headers.add("Date", date);
        headers.add("Authorization", String.format("MWS %s:%s", "yuntu_daodian", sign));
        return headers;

    }

    public static String hmacSHA1(String key, String data){
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes("utf-8"));
            return Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage()); }
    }
}
