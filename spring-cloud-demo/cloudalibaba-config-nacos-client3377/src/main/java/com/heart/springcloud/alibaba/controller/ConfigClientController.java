package com.heart.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ConfigClientController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/1 10:14
 * @Version: v1.0
 */
@RestController
@Slf4j
@RefreshScope//注解开启自动刷新配置
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/nacos/config/info")
    public String getConfigInfo() {
        log.info("[NACOS CONFIG] Spring application profile - config.info = {}", configInfo);
        return configInfo;
    }
}
