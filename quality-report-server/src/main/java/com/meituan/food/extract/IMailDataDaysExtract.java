package com.meituan.food.extract;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;

public interface IMailDataDaysExtract {

    void extractEfficiencyData4Days(String startDate, String endDate) throws MDMThriftException, TException;
}
