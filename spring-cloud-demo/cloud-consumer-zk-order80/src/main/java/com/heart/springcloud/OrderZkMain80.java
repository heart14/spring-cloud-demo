package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: OrderZkMain80
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/21 16:26
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderZkMain80.class, args);
    }
}
