package com.meituan.food.extract;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;

import java.text.ParseException;

public interface ICargoDataPushExtract {
    void pushData() throws ParseException, MDMThriftException;
}
