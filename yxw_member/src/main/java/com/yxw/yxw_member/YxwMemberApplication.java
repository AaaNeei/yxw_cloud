package com.yxw.yxw_member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.yxw.yxw_member.mapper")
public class YxwMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwMemberApplication.class, args);
    }

}
