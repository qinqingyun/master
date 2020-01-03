package com.meituan.food.extract;

import java.text.ParseException;

public interface ICOEDataExtract {
    void getCOEData(String firstDateStr,String secondDateStr) throws ParseException;
}
