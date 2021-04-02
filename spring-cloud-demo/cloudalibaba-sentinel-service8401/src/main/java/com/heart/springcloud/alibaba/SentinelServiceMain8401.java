package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: SentinelServiceMain8401
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/2 17:06
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServiceMain8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain8401.class, args);
    }
}
