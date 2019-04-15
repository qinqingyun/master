package com.meituan.food.extract;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

public interface IOneWeekCrashExtract {
    void extractData4Week(LocalDate firstDay, LocalDate lastDay);

}
