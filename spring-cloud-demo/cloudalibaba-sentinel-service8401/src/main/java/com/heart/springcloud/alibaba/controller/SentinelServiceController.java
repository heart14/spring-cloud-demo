package com.heart.springcloud.alibaba.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    /**
     * 测试服务降级 RT策略
     *
     * @return
     */
    @GetMapping(value = "/sentinel/degrade/rt")
    public CommonResult sentinelDegradeRt() {
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("One second later...");
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("[SENTINEL] service degrade RT...");

        return new CommonResult(200, "[SENTINEL] service degrade test RT");
    }

    /**
     * 测试服务降级 异常比例策略
     *
     * @return
     */
    @GetMapping(value = "/sentinel/degrade/exception")
    public CommonResult sentinelDegradeException() {

        int a = 10 / 0;
        log.info("[SENTINEL] service degrade RT...");

        return new CommonResult(200, "[SENTINEL] service degrade test RT");
    }

    /*
     * @SentinelResource注解里value值是资源名，前面进行sentinel降级限流等配置时，有一个资源名字段，当时取的是接口uri
     * 即/sentinel/hotkey
     * 而使用@SentinelResource注解配置了value值后，进行sentinel降级限流等配置时，资源名也可使用这个value值
     * 这个value值也是需要保证唯一的
     *
     * blockHandler指定的方法是在接口调用违反了sentinel配置的规则时进行兜底处理的方法
     */

    /**
     * 测试热点key规则
     */
    @GetMapping(value = "/sentinel/hotkey")
    @SentinelResource(value = "sentinel/hotkey", blockHandler = "hotkeyBlockHandler",fallback = "hotkeyFallback")
    public CommonResult sentinelHotKey(@RequestParam(value = "p1", required = false) String p1,
                                       @RequestParam(value = "p2", required = false) String p2) {
        log.info("[SENTINEL] hot key test, p1 = {}, p2 = {}", p1, p2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("p1", p1);
        jsonObject.put("p2", p2);

        /*
         * @SentinelResource注解，处理的是Sentinel控制台配置的违规情况，由blockHandler进行降级处理
         * 但是当程序抛出运行时异常时，@SentinelResource注解无法对这种程序异常进行fallback降级处理
         * 程序抛出运行时异常RuntimeException时，页面不会打印hotkeyBlockHandler内容，而是打印错误堆栈信息日志
         *
         * 如果要想为程序运行时异常进行兜底处理，则要进行配置fallback方法，
         * 配置方法等待后续TODO
         */
        //int a = 10 / 0;

        return new CommonResult(200, "[SENTINEL] CUSTOM HOT KEY METHOD SUCCESS!", jsonObject);
    }

    public CommonResult hotkeyBlockHandler(String p1, String p2, BlockException exception) {
        log.error("[SENTINEL] CUSTOM HOT KEY BLOCK HANDLER METHOD./(ㄒoㄒ)/~~");
        return new CommonResult(500, "[SENTINEL] CUSTOM HOT KEY BLOCK HANDLER METHOD./(ㄒoㄒ)/~~");
    }

}
