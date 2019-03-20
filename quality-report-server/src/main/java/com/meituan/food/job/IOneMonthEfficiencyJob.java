package com.meituan.food.job;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;

public interface IOneMonthEfficiencyJob {

    void sync() throws MDMThriftException, TException;
}
