package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: OrderOpenFeignMain80
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/2 10:55
 * @Version: v1.0
 */
@SpringBootApplication
@EnableFeignClients//开启OpenFeign
public class OrderOpenFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain80.class, args);
    }
}
