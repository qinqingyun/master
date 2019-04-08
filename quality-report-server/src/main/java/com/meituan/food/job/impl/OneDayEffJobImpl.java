package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayDataExtract;
import com.meituan.food.extract.IOneDayEffDataEx;
import com.meituan.food.job.IOneDayEffJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneDayEffJobImpl implements IOneDayEffJob {

    @Resource
    private List<IOneDayEffDataEx> dataExtracts;

    @Override
    public void sync() {
        LocalDate day = LocalDate.now().minusDays(2);
        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4EffDay(day)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }

}
