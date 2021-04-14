package com.heart.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: CircleBreakController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:35
 * @Version: v1.0
 */
@RestController
@Slf4j
public class CircleBreakController {

    @Value("${service-url.nacos-payment-service}")
    private String paymentServiceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    //@SentinelResource(value="testFallback") //什么都不配置，程序异常时直接显示Error page，没有兜底处理
    //@SentinelResource(value = "testFallback", fallback = "fallbackMethod") //只配置fallback属性，程序异常时调用指定的fallback方法进行处理
    //@SentinelResource(value = "testBlockHandler", blockHandler = "blockHandlerMethod") //只配置blockHandler属性，程序异常时不会调用blockHandler方法，资源调用违反Sentinel配置的流控规则时才会调用blockHandler方法
    @SentinelResource(value = "testBoth", blockHandler = "blockHandlerMethod",fallback = "fallbackMethod") //同时配置blockHandler和fallback属性，程序异常时调用指定的fallback方法进行处理，资源调用违反Sentinel配置的流控规则时调用blockHandler方法，当资源调用既违反了流控规则，又抛出异常时，优先进行blockhandler处理，也就是BlockException异常优先于RuntimeException
    //@SentinelResource(value = "testBoth", blockHandler = "blockHandlerMethod",fallback = "fallbackMethod",exceptionsToIgnore = NullPointerException.class) //exceptionsToIgnore属性排除
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {

        CommonResult result = restTemplate.getForObject(paymentServiceUrl + "/payment/nacos/" + id, CommonResult.class);
        if (id == 3) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常 :查询入参不对");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,空指针异常 :查询结果为NULL");
        }

        log.info("[CONSUMER] BY NACOS get payment :{}", result);
        return result;
    }

    //fallback方法
    public CommonResult fallbackMethod(@PathVariable("id") Long id, Throwable throwable) {
        Payment payment = new Payment(id, null);
        return new CommonResult(500, "服务异常FALLBACK! " + throwable.getMessage(), payment);
    }

    //blockHandler方法
    public CommonResult blockHandlerMethod(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, null);
        return new CommonResult(600, "服务流控BLOCKHANDLER! " + blockException.getMessage(), payment);
    }

}
