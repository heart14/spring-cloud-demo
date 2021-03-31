package com.heart.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: ApplicationContextConfig
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/31 15:52
 * @Version: v1.0
 */
@Configuration
public class ApplicationContextConfig {

    /*
     * 通过查看maven依赖可以看到  spring-cloud-starter-alibaba-nacos-discovery这个依赖里面包含了netflix的ribbon
     * 也就是说 Nacos支持负载均衡也是通过ribbon来实现的
     * 既然是通过ribbon
     * 所以要编写这个引入RestTemplate的配置类
     */

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
