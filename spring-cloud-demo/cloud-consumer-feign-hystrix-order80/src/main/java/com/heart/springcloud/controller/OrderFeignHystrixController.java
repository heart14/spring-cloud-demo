package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.service.FeignHystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderFeignHystrixController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 15:00
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderFeignHystrixController {

    @Resource
    private FeignHystrixPaymentService feignHystrixPaymentService;

    @GetMapping(value = "/consumer/payment/hystrix/success/{id}")
    public CommonResult paymentInfo_success(@PathVariable("id") Integer id) {
        log.info("[CONSUMER] 通过FEIGN HYSTRIX调用PROVIDER => id :{}", id);
        CommonResult commonResult = feignHystrixPaymentService.paymentInfo_success(id);
        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", commonResult);
        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public CommonResult paymentInfo_timeout(@PathVariable("id") Integer id) {
        log.info("[CONSUMER] 通过FEIGN HYSTRIX调用PROVIDER => id :{}", id);
        CommonResult commonResult = feignHystrixPaymentService.paymentInfo_timeout(id);
        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", commonResult);
        return commonResult;
    }
}
