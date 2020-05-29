package com.meituan.qa.meishi.Hui.util;

import java.util.concurrent.TimeUnit;

public class Task {
    public static void sleep(long secondTime) {
        try {
            TimeUnit.SECONDS.sleep(secondTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
