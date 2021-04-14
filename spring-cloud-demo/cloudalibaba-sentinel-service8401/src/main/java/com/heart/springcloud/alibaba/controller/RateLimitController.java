package com.heart.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.heart.springcloud.alibaba.handler.CustomBlockHandler;
import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RateLimitController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 10:10
 * @Version: v1.0
 */
@RestController
@Slf4j
public class RateLimitController {

    /**
     * 测试按资源名称限流
     */
    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "exceptionHandler")
    public CommonResult limitByResource() {
        log.info("[SENTINEL] @SentinelResource注解 按资源名称限流测试");
        return new CommonResult(200, "按资源名称限流测试OK！", new Payment(605L, "Ref:rain"));
    }

    /**
     * 测试按资源名称限流 blockhandler方法
     */
    public CommonResult exceptionHandler(BlockException exception) {
        log.info("[SENTINEL] @SentinelResource注解 按资源名称限流测试 {} 服务不可用", exception.getClass().getCanonicalName());
        return new CommonResult(500, "按资源名称限流测试 " + exception.getClass().getCanonicalName() + " 服务不可用！", null);
    }

    /**
     * 测试按url限流
     */
    @GetMapping(value = "/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult limitByUrl() {
        log.info("[SENTINEL] @SentinelResource注解 按资源Url限流测试");
        return new CommonResult(200, "按资源Url限流测试OK！", new Payment(605L, "Ref:rain"));
    }

    /**
     * 自定义流控处理方法
     */
    /*
     * 通过@SentinelResource注解 value指定资源名
     * blockHandlerClass属性指定自定义流控处理要使用哪个类
     * blockHandler属性指定自定义流控处理要使用哪个方法
     */
    @GetMapping(value = "/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandlerClass = CustomBlockHandler.class, blockHandler = "customBlockHandlerMethod1")
    public CommonResult customBlockHandler() {
        log.info("[SENTINEL] @SentinelResource注解 自定义流控处理方法测试");
        return new CommonResult(200, "自定义流控处理方法测试OK！", new Payment(605L, "Ref:rain"));
    }

}
