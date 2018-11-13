package com.meituan.food.crane;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.food.job.IJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class SyncJob {

    @Resource
    private IJob oneDaySyncJob;

    @Crane("sync.job")
    public void syncOneDay() {
        log.error("one day job execute at: {}", new Date());
        oneDaySyncJob.sync();
    }
}
