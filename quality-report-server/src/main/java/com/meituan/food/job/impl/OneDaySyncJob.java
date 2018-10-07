package com.meituan.food.job.impl;

import com.meituan.food.extract.IDataExtract;
import com.meituan.food.job.IJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneDaySyncJob implements IJob {

    @Resource
    private IDataExtract dataExtract;

    @Override
    public void sync() throws Exception {
        LocalDate day = LocalDate.now().minusDays(1);
        dataExtract.extractData4Day(day);
    }
}
