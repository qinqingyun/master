package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.extract.IOneQuarterDataExtract;
import com.meituan.food.job.IOneQuarterJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneQuarterJobImpl implements IOneQuarterJob {

    @Resource
    public List<IOneQuarterDataExtract> dataExtracts;

    private static String firstDay;
    private static String lastDay;

    @Override
    public void sync() {

        LocalDate day = LocalDate.now();
        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Quarter(day,day)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }
}
