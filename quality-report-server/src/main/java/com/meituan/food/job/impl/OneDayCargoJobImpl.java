package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayCargoExtract;
import com.meituan.food.job.IOneDayCargoJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneDayCargoJobImpl implements IOneDayCargoJob {

    @Resource
    private IOneDayCargoExtract oneDayCargoExtract;

    @Override
    public void sync() {
        LocalDate day=LocalDate.now().plusDays(1);

        oneDayCargoExtract.updateCargoAva(day);

    }


}
