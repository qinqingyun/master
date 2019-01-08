package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneWeekEightDataExtract;
import com.meituan.food.job.IImportantProjectReviewJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ImportantProjectReviewJob implements IImportantProjectReviewJob {

    @Resource
    private List<IOneWeekEightDataExtract> iOneWeekEightDataExtracts;
    @Override
    public void sync() {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        LocalDate lastDay=LocalDate.now().minusDays(1);

        List<CompletableFuture<Void>> extractFutures = iOneWeekEightDataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> {
                    try {
                        dataExtract.extractData4Week(firstDay,lastDay);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }
}
