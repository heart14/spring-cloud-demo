package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: PaymentMain9004
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:17
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9004.class, args);
    }
}
