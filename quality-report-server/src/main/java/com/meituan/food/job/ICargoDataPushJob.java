package com.meituan.food.job;

import java.text.ParseException;

public interface ICargoDataPushJob {
    void sync() throws ParseException;
}
