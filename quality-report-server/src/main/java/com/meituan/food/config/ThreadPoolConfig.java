package com.meituan.food.config;

import com.meituan.mdp.boot.starter.threadpool.ExecutorServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService appApiDetailExtractPool() {
        return ExecutorServices.forThreadPoolExecutor(
                "app-api-detail-extract-pool",
                60,
                TimeUnit.SECONDS,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
