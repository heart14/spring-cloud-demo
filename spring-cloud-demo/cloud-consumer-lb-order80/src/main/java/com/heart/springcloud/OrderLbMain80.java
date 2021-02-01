package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: OrderLbMain80
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/1 15:52
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderLbMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderLbMain80.class, args);
    }
}
