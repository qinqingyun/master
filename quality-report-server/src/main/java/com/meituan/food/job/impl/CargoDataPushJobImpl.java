package com.meituan.food.job.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.job.ICargoDataPushJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CargoDataPushJobImpl implements ICargoDataPushJob {

    @Resource
    private ICargoDataPushExtract cargoDataPushExtract;
    @Override
    public void sync() {
        cargoDataPushExtract.pushData();
    }
}
