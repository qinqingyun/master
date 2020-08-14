package com.meituan.food.job.impl;

import com.meituan.food.extract.IAtpPushExtract;
import com.meituan.food.job.IAtpPushJob;

import javax.annotation.Resource;

public class AtpPushJobImpl implements IAtpPushJob {

    @Resource
    private IAtpPushExtract atpPushExtract;

    @Override
    public void push() {
        atpPushExtract.pushAtp();
    }
}
