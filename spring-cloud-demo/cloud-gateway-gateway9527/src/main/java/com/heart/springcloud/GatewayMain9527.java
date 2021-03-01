package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: GatewayMain9527
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/1 17:24
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient//网关也是一个微服务，也要注册到Eureka或者其它注册中心
public class GatewayMain9527 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
