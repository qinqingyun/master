package com.meituan.food.job.impl;

import com.meituan.food.extract.IWeekBugDataExtract;
import com.meituan.food.job.IWeekBugJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class WeekBugJobImpl implements IWeekBugJob {

    @Resource
    public List<IWeekBugDataExtract> weekBugDataExtracts;

    @Override
    public void sync() {
       /* LocalDate firstDate = LocalDate.now().minusDays(4);
        LocalDate lastDate = LocalDate.now().minusDays(4);

        List<CompletableFuture<Void>> extractFutures = weekBugDataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Week(firstDate,lastDate)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);*/

        for (int i = 12; i > 0; i--) {
            LocalDate firstDate = LocalDate.now().minusDays(i);
            LocalDate lastDate = LocalDate.now().minusDays(i);

            List<CompletableFuture<Void>> extractFutures = weekBugDataExtracts.stream()
                    .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Week(firstDate, lastDate)))
                    .collect(Collectors.toList());
            extractFutures.forEach(CompletableFuture::join);
        }
    }
}
