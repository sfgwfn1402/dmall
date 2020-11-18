package com.xs.dmall.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Yang Hao
 * @date 2020-09-27 11:22
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源的自动装配,使用自己的数据源
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderMainApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp.class);
    }
}

