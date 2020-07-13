package com.meituan.food.extract;

import java.text.ParseException;
import java.util.Date;

public interface IAllDDCoePushEctract {
    void pushData(Date firstDate, Date secondDate,String firstDayStr,String secondDayStr) throws ParseException ;
}
