package com.heart.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: ApplicationContextConfig
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 15:07
 * @Version: v1.0
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//使用@LoadBalanced注解，赋予restTemplate负载均衡的能力，才能在Eureka客户端里直接通过微服务名称去调用实例
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
