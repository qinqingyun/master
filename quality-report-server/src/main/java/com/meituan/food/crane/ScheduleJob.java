package com.meituan.food.crane;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.food.job.IOneDayJob;
import com.meituan.food.job.IOneMonthJob;
import com.meituan.food.job.IOneQuarterJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class ScheduleJob {

    @Resource
    private IOneDayJob oneDayJob;

    @Resource
    private IOneMonthJob oneMonthJob;

    @Resource
    private IOneQuarterJob oneQuarterJob;

    @Crane("one.day.sync.job")
    public void syncOneDay() {
        log.info("one day job execute at: {}", new Date());
        oneDayJob.sync();
    }

    @Crane("one.month.sync.job")
    public void syncOneMonth() {
        log.info("one month job execute at: {}", new Date());
        oneMonthJob.sync();
    }

    @Crane("one.quarter.sync.job")
    public void syncOneQuarter(){
        log.info("one month job execute at: {}", new Date());
        oneQuarterJob.sync();
    }
}
