package com.meituan.food.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:mailsdk.xml")
public class MailConfig {

    @Value("${mail.appkey}")
    private String mailAppkey;

    @Value("${mail.secret}")
    private String mailSecret;

    @Bean(name = "localAppKey")
    public String localAppKey() {
        return mailAppkey;
    }

    @Bean(name = "mailServiceSecret")
    public String mailServiceSecret() {
        return mailSecret;
    }
}
