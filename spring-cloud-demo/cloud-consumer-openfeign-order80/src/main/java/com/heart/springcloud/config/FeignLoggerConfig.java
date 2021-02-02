package com.heart.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FeignLoggerConfig
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/2 17:47
 * @Version: v1.0
 */
@Configuration
public class FeignLoggerConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
