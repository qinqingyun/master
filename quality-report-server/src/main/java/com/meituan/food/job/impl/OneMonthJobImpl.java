package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.job.IOneMonthJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Component
public class OneMonthJobImpl implements IOneMonthJob {

    @Resource
    public List<IOneMonthDataExtract> dataExtracts;

    private static String firstDay;
    private static String lastDay;

    @Override
    public void sync() {
//        LocalDate day = LocalDate.now().minusMonths(1);
      /// Calendar lastDay=Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1=Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        firstDay = format.format(cal_1.getTime());

        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);
        lastDay = format.format(cale.getTime());


        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Month(firstDay,lastDay)))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);
    }

}
