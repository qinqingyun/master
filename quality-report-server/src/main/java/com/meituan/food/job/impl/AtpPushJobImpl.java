package com.meituan.food.job.impl;

import com.meituan.food.extract.IAtpPushExtract;
import com.meituan.food.job.IAtpPushJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AtpPushJobImpl implements IAtpPushJob {

    @Resource
    private IAtpPushExtract atpPushExtract;

    @Override
    public void push() {
        atpPushExtract.pushAtp();
    }
}
