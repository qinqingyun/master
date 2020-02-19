package com.meituan.food.config;

import com.dianping.dpsf.spring.ProxyBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PigeonConfig {

    @Bean(initMethod = "init")
    public ProxyBeanFactory holidayService(){
        ProxyBeanFactory holidayService =new ProxyBeanFactory();
        holidayService.setServiceName("com.sankuai.meituan.attendance.api.HolidayService");
        holidayService.setIface("com.sankuai.meituan.attendance.api.HolidayService");
        holidayService.setSerialize("hessian");
        holidayService.setCallMethod("sync");
        holidayService.setTimeout(30000);
        return holidayService;
    }
}
