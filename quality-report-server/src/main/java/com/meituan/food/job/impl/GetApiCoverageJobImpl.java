package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.IGetCoverageExtract;
import com.meituan.food.job.IGetApiCoverageJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class GetApiCoverageJobImpl implements IGetApiCoverageJob {

    @Resource
    private IGetCoverageExtract coverageExtract;

    @Override
    public void sync() {
        LocalDate day=LocalDate.now().minusDays(1);
        String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        coverageExtract.getCoverage();
    }
}
