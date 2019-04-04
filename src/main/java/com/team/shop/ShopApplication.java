package com.team.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//这样才能找到mapper文件
@MapperScan(value = "com.team.shop.mapper")
public class ShopApplication {
    public static void main(String[] args) {
    SpringApplication.run(ShopApplication.class, args);
    }
}
