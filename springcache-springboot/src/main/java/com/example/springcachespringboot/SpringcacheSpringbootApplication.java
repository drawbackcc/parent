package com.example.springcachespringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.springcachespringboot.dao"})
//@EnableCaching
public class SpringcacheSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcacheSpringbootApplication.class, args);
    }

}
