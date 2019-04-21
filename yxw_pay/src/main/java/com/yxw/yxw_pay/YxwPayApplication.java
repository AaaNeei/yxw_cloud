package com.yxw.yxw_pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(value = "com.yxw.yxw_pay.mapper")
@ComponentScan(basePackages = {"com.yxw"})
public class YxwPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwPayApplication.class, args);
    }

}
