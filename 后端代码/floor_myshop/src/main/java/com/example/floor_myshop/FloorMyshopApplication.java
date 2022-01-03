package com.example.floor_myshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.floor_myshop.mapper")
public class FloorMyshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FloorMyshopApplication.class, args);
    }

}
