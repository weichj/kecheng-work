package com.example.firstWork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.example.firstWork.Mapper")
public class firstWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(firstWorkApplication.class, args);
    }

}
