package com.yxw.yxw_member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.yxw.yxw_member.mapper")
@ComponentScan(basePackages = {"com.yxw"})
public class YxwMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwMemberApplication.class, args);
    }

}
