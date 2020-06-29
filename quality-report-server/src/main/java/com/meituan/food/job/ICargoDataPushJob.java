package com.meituan.food.job;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;

import java.text.ParseException;

public interface ICargoDataPushJob {
    void sync() throws ParseException, MDMThriftException;
}
