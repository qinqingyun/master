package com.meituan.food.job;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;

public interface IOneQMailJob {
    void sync() throws TException, MDMThriftException;
}
