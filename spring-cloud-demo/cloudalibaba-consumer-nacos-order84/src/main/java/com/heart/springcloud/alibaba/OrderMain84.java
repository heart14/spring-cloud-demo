package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: OrderMain84
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:34
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }
}
