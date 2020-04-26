package com.xs.dmall.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:dubbo-consumer.xml")
@SpringBootApplication(scanBasePackages = {"com.xs.dmall.dubbo"})
public class DmallDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallDubboConsumerApplication.class, args);
    }

}
