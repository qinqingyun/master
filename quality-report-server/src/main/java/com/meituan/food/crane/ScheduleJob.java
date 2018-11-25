package com.meituan.food.crane;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.food.job.IOneDayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class ScheduleJob {

    @Resource
    private IOneDayJob oneDayJob;

    @Crane("one.day.sync.job")
    public void syncOneDay() {
        log.info("one day job execute at: {}", new Date());
        oneDayJob.sync();
    }

    @Crane("one.month.sync.job")
    public void syncOneMonth() {
        log.info("one month job execute at: {}", new Date());
        oneDayJob.sync();
    }
}
