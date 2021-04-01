package com.heart.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: NacosConfigClientMain3377
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/1 10:13
 * @Version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient//注解开启服务发现
public class NacosConfigClientMain3377 {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class, args);
    }
}
