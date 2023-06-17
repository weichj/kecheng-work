package com.example.EurekaUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.example.EurekaUser.Mapper")
public class EurekaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaUserApplication.class, args);
    }

}
