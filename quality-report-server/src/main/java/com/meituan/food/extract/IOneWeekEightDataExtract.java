package com.meituan.food.extract;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

public interface IOneWeekEightDataExtract {
    void extractData4Week(LocalDate firstDay, LocalDate lastDay) throws UnsupportedEncodingException;

}
