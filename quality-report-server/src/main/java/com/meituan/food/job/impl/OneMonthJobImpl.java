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
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        firstDay = format.format(cal_1.getTime());

        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastDay = format.format(cale.getTime());
        dataExtracts.forEach(dataExtract -> dataExtract.extractData4Month(firstDay, lastDay));


        /*for(int i=1;i<=12;i++) {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            //设置年份
            cal.set(Calendar.YEAR, 2018);
            cal2.set(Calendar.YEAR, 2018);

            //设置月份
            cal.set(Calendar.MONTH, i - 1);
            cal2.set(Calendar.MONTH, i-1);

            //获取某月最小天数
            int firstday = cal.getMinimum(Calendar.DATE);
            int lastday = cal2.getActualMaximum(Calendar.DATE);

            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstday);
            cal2.set(Calendar.DAY_OF_MONTH, lastday);

            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            String fir= sdf.format(cal.getTime());
            String last=sdf2.format(cal2.getTime());

            System.out.println(fir+" "+last);
            dataExtracts.forEach(dataExtract -> dataExtract.extractData4Month(fir, last));

        }*/




//        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
//                .map(dataExtract -> CompletableFuture.runAsync(() -> dataExtract.extractData4Month(firstDay,lastDay)))
//                .collect(Collectors.toList());
//        extractFutures.forEach(CompletableFuture::join);
    }

    public static void main(String[] args) {
        for(int i=1;i<=12;i++) {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            //设置年份
            cal.set(Calendar.YEAR, 2018);
            cal2.set(Calendar.YEAR, 2018);

            //设置月份
            cal.set(Calendar.MONTH, i - 1);
            cal2.set(Calendar.MONTH, i-1);

            //获取某月最小天数
            int firstday = cal.getMinimum(Calendar.DATE);
            int lastday = cal2.getActualMaximum(Calendar.DATE);

            //设置日历中月份的最小天数
            cal.set(Calendar.DAY_OF_MONTH, firstday);
            cal2.set(Calendar.DAY_OF_MONTH, lastday);

            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            String fir= sdf.format(cal.getTime());
            String last=sdf2.format(cal2.getTime());

            System.out.println(fir+" "+last);
        }
    }
}
