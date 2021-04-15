package com.heart.springcloud.alibaba.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RulePersistenceController
 * @Description: Sentinel流控规则持久化测试接口
 * @Author: jayhe
 * @Date: 2021/4/15 16:38
 * @Version: v1.0
 */
@RestController
@Slf4j
public class RulePersistenceController {

    @GetMapping(value = "/sentinel/rule/persistence")
    public CommonResult persistenceTest() {
        log.info("[SENTINEL] RULE PERSISTENCE TEST ....");
        return new CommonResult(200, "[SENTINEL] RULE PERSISTENCE TEST OK!");
    }
}
