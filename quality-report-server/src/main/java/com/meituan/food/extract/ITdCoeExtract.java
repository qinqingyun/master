package com.meituan.food.extract;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public interface ITdCoeExtract {

    void extractData4Week(Date firstDate, Date secondDate) throws ParseException;
}
