package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayForEfficiencyDataExtract;
import com.meituan.food.job.IOneDayForEfficiencyJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneDayForEfficiencyJobImpl implements IOneDayForEfficiencyJob {

    @Resource
    private List<IOneDayForEfficiencyDataExtract> dataExtracts;

    @Override
    public void sync() {
        LocalDate day = LocalDate.now().minusDays(92);
        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractEfficiencyData4Day(day)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
        /*for(int i=92;i>61;i--) {
            LocalDate day = LocalDate.now().minusDays(i);
            List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                    .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractEfficiencyData4Day(day)))
                    .collect(Collectors.toList());
            extractFutures.forEach(CompletableFuture::join);
        }*/

    }

    public static void main(String[] args) {
        for(int i=92;i>61;i--) {
            LocalDate day = LocalDate.now().minusDays(i);
            System.out.println(day);
        }
    }
}