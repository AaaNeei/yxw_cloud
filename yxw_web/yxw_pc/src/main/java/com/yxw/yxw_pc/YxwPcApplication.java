package com.yxw.yxw_pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//开启feign
@EnableFeignClients
@ComponentScan(basePackages = {"com.yxw"})

public class YxwPcApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwPcApplication.class, args);
    }

}
