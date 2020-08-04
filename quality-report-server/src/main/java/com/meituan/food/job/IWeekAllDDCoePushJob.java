package com.meituan.food.job;

import java.text.ParseException;

public interface IWeekAllDDCoePushJob {
    void sync() throws ParseException;

    void syncForMonth() throws ParseException;
}
