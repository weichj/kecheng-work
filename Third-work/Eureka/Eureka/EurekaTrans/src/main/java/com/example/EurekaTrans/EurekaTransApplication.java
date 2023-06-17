package com.example.EurekaTrans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.example.EurekaTrans.Mapper")
public class EurekaTransApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTransApplication.class, args);
    }

}
