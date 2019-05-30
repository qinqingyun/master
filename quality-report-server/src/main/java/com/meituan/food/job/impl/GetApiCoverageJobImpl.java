package com.meituan.food.job.impl;

import com.meituan.food.extract.IGetCoverageExtract;
import com.meituan.food.job.IGetApiCoverageJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GetApiCoverageJobImpl implements IGetApiCoverageJob {

    @Resource
    private IGetCoverageExtract coverageExtract;

    @Override
    public void sync() {
        coverageExtract.getCoverage();
    }
}
