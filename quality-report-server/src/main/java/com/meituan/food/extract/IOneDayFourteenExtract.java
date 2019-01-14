package com.meituan.food.extract;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;

import java.time.LocalDate;

public interface IOneDayFourteenExtract {

    void extractData4Day(LocalDate firstDay, LocalDate lastDay) throws MDMThriftException;

}
