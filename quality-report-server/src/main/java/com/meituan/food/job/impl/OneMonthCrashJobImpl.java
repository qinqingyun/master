package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneWeekCrashExtract;
import com.meituan.food.job.IOneMonthCrashJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneMonthCrashJobImpl implements IOneMonthCrashJob {

    @Resource
    public List<IOneWeekCrashExtract> oneWeekCrashExtracts;

    @Override
    public void sync() {
        LocalDate firstDate = LocalDate.now().minusDays(28);
        LocalDate lastDate = LocalDate.now().minusDays(1);

        List<CompletableFuture<Void>> crashExtractFutures = oneWeekCrashExtracts.stream()
                .map(crashDataExtract -> CompletableFuture.runAsync(() -> crashDataExtract.extractData4Week(firstDate,lastDate)))
                .collect(Collectors.toList());
        crashExtractFutures.forEach(CompletableFuture::join);
    }
}
