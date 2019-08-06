package com.meituan.food.job.impl;

import com.meituan.food.extract.IMailDataDaysExtract;
import com.meituan.food.job.IHalfYearMailJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HalfYearMialJobImpl implements IHalfYearMailJob {

    @Resource
    private IMailDataDaysExtract dataDaysExtract;

    private static String startDate;
    private static String endDate;

    @Override
    public void sync() throws TException, MDMThriftException {
        dataDaysExtract.extractEfficiencyData4Days("2019-01-01","2019-06-30");
    }
}
