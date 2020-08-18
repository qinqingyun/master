package com.meituan.food.job;

import java.text.ParseException;

public interface IOneWeekJob {

    void sync();
    void syncTd() throws ParseException;
}
