package com.heart.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ConfigClientController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/9 15:15
 * @Version: v1.0
 */
@RestController
@Slf4j
public class ConfigClientController {

    @Value("${config.info}")//这里的config.info是根据配置文件中实际的key值来写的
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public String getConfigInfo() {
        log.info("Spring cloud config info fetch...");
        return configInfo;
    }
}
