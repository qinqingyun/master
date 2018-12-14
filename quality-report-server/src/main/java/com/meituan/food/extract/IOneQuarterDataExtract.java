package com.meituan.food.extract;

import java.time.LocalDate;

public interface IOneQuarterDataExtract {

    void extractData4Quarter(LocalDate firstDay, LocalDate lastDay);

}
