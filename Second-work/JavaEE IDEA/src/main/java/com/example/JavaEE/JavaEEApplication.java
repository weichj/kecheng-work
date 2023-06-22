package com.example.JavaEE;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.example.JavaEE.Mapper")
public class JavaEEApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEEApplication.class, args);
    }

}
