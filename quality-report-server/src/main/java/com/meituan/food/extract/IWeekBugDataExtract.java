package com.meituan.food.extract;

import java.time.LocalDate;

public interface IWeekBugDataExtract {

    void extractData4Week(LocalDate firstDay, LocalDate lastDay);
}
