package com.heart.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: CustomBlockHandler
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 10:44
 * @Version: v1.0
 */
@Slf4j
/*
 * 注意 这里不用加@Component注解
 */
public class CustomBlockHandler {

    /**
     * 注意 这里的方法必须是static的  否则报错
     */
    public static CommonResult customBlockHandlerMethod1(BlockException exception) {
        log.info("[SENTINEL] @SentinelResource注解 自定义流控处理方法1");
        return new CommonResult(500, "自定义流控处理方法1！", null);
    }

    public static CommonResult customBlockHandlerMethod2(BlockException exception) {
        log.info("[SENTINEL] @SentinelResource注解 自定义流控处理方法2");
        return new CommonResult(500, "自定义流控处理方法2！", null);
    }
}
