package com.meituan.food.job;

import java.text.ParseException;

public interface IOneMonthJob {
    void sync() throws ParseException;
}
