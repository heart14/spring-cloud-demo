package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: OrderMain85
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/15 10:58
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMain85 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain85.class, args);
    }
}
