package com.meituan.food.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author ryanshi
 * @version 2018/8/23
 */
public final class BaUtils {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * 公司 ba 加密算法
     *
     * @param date 时间格式"EEE, dd MMM yyyy HH:mm:ss z"
     */
    public static String getAuthorization(String uri, String method, String date, String clientId, String secret) {
        String stringToSign = method + " " + uri + "\n" + date;
        String signature = getSignature(stringToSign, secret);
        return "MWS" + " " + clientId + ":" + signature;
    }

    public static String getAuthDate(Date date) {
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(date);
    }

    public static String getSignature(String data, String secret) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            BASE64Encoder base64 = new BASE64Encoder();
            result = base64.encode(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public BaUtils() {
    }
}
