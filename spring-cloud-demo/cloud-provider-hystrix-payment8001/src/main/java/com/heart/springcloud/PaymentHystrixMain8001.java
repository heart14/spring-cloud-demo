package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: PaymentHystrixMain8001
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 10:55
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker//开启Hystrix
public class PaymentHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class, args);
    }
}
