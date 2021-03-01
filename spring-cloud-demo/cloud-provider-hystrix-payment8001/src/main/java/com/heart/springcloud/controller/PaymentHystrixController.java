package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentHystrixController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 11:04
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private HystrixPaymentService hystrixPaymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/success/{id}")
    public CommonResult paymentInfo_success(@PathVariable("id") Integer id) {
        long l1 = System.currentTimeMillis();
        String success = hystrixPaymentService.paymentInfo_success(id);
        log.info("[PROVIDER]{}", success);
        long l2 = System.currentTimeMillis();
        return new CommonResult(200, success + ", SERVER PORT :" + serverPort + ", 耗时 :" + (l2 - l1) + "毫秒");
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public CommonResult paymentInfo_timeout(@PathVariable("id") Integer id) {
        long l1 = System.currentTimeMillis();
        String timeout = hystrixPaymentService.paymentInfo_timeout(id);
        log.info("[PROVIDER]{}", timeout);
        long l2 = System.currentTimeMillis();
        return new CommonResult(200, timeout + ", SERVER PORT :" + serverPort + ", 耗时 :" + (l2 - l1) + "毫秒");
    }

    /*    ↓↓↓↓  服务熔断  ↓↓↓↓*/
    @GetMapping(value = "/payment/circuit/{id}")
    public CommonResult paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String circuitBreaker = hystrixPaymentService.paymentCircuitBreaker(id);
        log.info("[PROVIDER]{}", circuitBreaker);
        return new CommonResult(200, circuitBreaker + ", SERVER PORT :" + serverPort);
    }
}
