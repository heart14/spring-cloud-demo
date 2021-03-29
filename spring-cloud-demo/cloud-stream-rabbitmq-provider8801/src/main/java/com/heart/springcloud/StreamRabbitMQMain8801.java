package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: StreamRabbitMQMain8801
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/29 16:49
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQMain8801.class, args);
    }
}
