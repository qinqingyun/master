package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneWeekDataExtract;
import com.meituan.food.job.IOneWeekJob;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class OneWeekJobImpl implements IOneWeekJob {

    @Resource
    public List<IOneWeekDataExtract> oneWeekDataExtracts;

    @Override
    public void sync() {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        LocalDate lastDay=LocalDate.now();

        List<CompletableFuture<Void>> extractFutures = oneWeekDataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Week(firstDay,lastDay)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }
}
