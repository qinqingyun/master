package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.IOneWeekDataExtract;
import com.meituan.food.job.IOneWeekJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneWeekJobImpl implements IOneWeekJob {

    @Resource
    public List<IOneWeekDataExtract> oneWeekDataExtracts;

    @Override
    public void sync() {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastDay=LocalDate.now();
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        //专项周报任务
      /*  List<CompletableFuture<Void>> extractFutures = oneWeekDataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Week(firstDay,lastDay)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);*/
    }
}
