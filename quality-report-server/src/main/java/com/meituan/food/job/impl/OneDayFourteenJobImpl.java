package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayFourteenExtract;
import com.meituan.food.job.IOneDayFourteenJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OneDayFourteenJobImpl implements IOneDayFourteenJob {

    @Resource
    private IOneDayFourteenExtract oneDayFourteenExtract;

    @Override
    public void sync() throws MDMThriftException {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        LocalDate lastDay=LocalDate.now().minusDays(1);

        oneDayFourteenExtract.extractData4Day(firstDay,lastDay);
    }
}
