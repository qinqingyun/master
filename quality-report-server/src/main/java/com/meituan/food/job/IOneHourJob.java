package com.meituan.food.job;

import java.text.ParseException;

public interface IOneHourJob {
    void extractData4Hour() throws ParseException;
    void extractDataTd4Hour() throws ParseException;
}
