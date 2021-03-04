package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: ConfigCenterMain3344
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/4 16:43
 * @Version: v1.0
 */
@SpringBootApplication
@EnableConfigServer//开启配置中心服务端
@EnableEurekaClient
public class ConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
