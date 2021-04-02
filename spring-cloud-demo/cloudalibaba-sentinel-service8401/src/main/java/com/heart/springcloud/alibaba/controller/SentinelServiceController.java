package com.heart.springcloud.alibaba.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SentinelServiceController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/2 17:06
 * @Version: v1.0
 */
@RestController
@Slf4j
public class SentinelServiceController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 初始化服务监控测试接口 hello 和 world
     *
     * @return
     */
    @GetMapping(value = "/sentinel/hello")
    public CommonResult sentinelServiceHello() {
        log.info("[SENTINEL] service hello...");
        return new CommonResult(200, "[SENTINEL] service monitor running success on server port " + serverPort + " ,sentinelServiceHello");
    }

    @GetMapping(value = "/sentinel/world")
    public CommonResult sentinelServiceWorld() {
        log.info("[SENTINEL] service world...");
        return new CommonResult(200, "[SENTINEL] service monitor running success on server port " + serverPort + " ,sentinelServiceWorld");
    }
}
