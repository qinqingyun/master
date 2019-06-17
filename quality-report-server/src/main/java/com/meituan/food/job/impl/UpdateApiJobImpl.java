package com.meituan.food.job.impl;

import com.meituan.food.extract.IUpdateApiExtract;
import com.meituan.food.job.IUpdateApiJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UpdateApiJobImpl implements IUpdateApiJob {

    @Resource
    private IUpdateApiExtract updateApiExtract;

    @Override
    public void sync() {
        updateApiExtract.updateApi();
    }
}
