package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: StreamRabbitMQMain8802
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/30 10:04
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQMain8802.class, args);
    }
}
