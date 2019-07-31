package com.meituan.food.extract;

import java.time.LocalDate;

public interface IOneDayDutyDataExtract {

    void extractData4Day(LocalDate day);

    void pushToAdmin(LocalDate today);

}
