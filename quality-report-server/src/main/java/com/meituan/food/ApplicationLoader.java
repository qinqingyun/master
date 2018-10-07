package com.meituan.food;

import com.cip.crane.client.spring.annotation.CraneConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.meituan.mdp.boot.starter.MdpContextUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@CraneConfiguration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ApplicationLoader {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationLoader.class);
        application.setAdditionalProfiles(MdpContextUtils.getHostEnvStr());
        application.run(args);
    }
}


