package com.meituan.food.job;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;

public interface IOneDayFourteenJob {

    void sync() throws MDMThriftException;

}
