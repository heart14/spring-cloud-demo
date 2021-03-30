package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: StreamRabbitMQMain8803
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/30 10:31
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQMain8803 {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQMain8803.class, args);
    }
}
