package com.meituan.food.job.impl;

import com.meituan.food.extract.IGetLineCoverageExtract;
import com.meituan.food.job.IGetLineCoverageJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GetLineCoverageJobImpl implements IGetLineCoverageJob {

    @Resource
    IGetLineCoverageExtract iGetLineCoverageExtract;

    @Override
    public void sync() {
        iGetLineCoverageExtract.getLineCoverage();
    }
}
