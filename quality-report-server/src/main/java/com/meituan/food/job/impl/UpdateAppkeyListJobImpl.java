package com.meituan.food.job.impl;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.job.IUpdateAppkeyListJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UpdateAppkeyListJobImpl implements IUpdateAppkeyListJob {

    @Resource
    private IGetAppkeyList getAppkeyList;

    @Override
    public void sync() {
        getAppkeyList.getAppkeyList();
    }
}
