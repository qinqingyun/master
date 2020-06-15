package com.meituan.food.extract;

import java.text.ParseException;

public interface ICOETdDataExtract {

    void getCOETdData(String firstDayStr, String secondDayStr)throws ParseException;
}
