package com.yxw.yxw_message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YxwMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwMessageApplication.class, args);
    }

}
