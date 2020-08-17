package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.IOneWeekDataExtract;
import com.meituan.food.extract.impl.TdCoeExtracter;
import com.meituan.food.job.IOneWeekJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.time.*;
import java.util.*;

@Component
public class OneWeekJobImpl implements IOneWeekJob {

    @Resource
    public List<IOneWeekDataExtract> oneWeekDataExtracts;

    @Resource  TdCoeExtracter  tdCoeExtracter;

    @Override
    public void sync() {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastDay=LocalDate.now();
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        //专项周报任务
       List<CompletableFuture<Void>> extractFutures = oneWeekDataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Week(firstDay,lastDay)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }

    public  void syncTd() throws ParseException {
        LocalDate firstDay=LocalDate.now().minusDays(8);
        LocalDate secondDay=LocalDate.now().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDate_first = firstDay.atStartOfDay(zoneId);
        Date first = Date.from(zonedDate_first.toInstant());
        ZonedDateTime zonedDate_second = secondDay.atStartOfDay(zoneId);
        Date second = Date.from(zonedDate_second.toInstant());
        tdCoeExtracter.extractData4Week(first,second);



    }


}
