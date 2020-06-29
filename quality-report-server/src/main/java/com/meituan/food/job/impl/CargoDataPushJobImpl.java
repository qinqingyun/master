package com.meituan.food.job.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.job.ICargoDataPushJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Component
public class CargoDataPushJobImpl implements ICargoDataPushJob {

    @Resource
    private List<ICargoDataPushExtract> cargoDataPushExtract;
    @Override
    public void sync() throws ParseException, MDMThriftException {
        for (ICargoDataPushExtract iCargoDataPushExtract : cargoDataPushExtract) {
            iCargoDataPushExtract.pushData();
        }
    }
}
