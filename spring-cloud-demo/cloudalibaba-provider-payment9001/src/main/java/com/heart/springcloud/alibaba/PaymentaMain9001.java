package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: PaymentaMain9001
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/31 14:11
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentaMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentaMain9001.class, args);
    }
}
