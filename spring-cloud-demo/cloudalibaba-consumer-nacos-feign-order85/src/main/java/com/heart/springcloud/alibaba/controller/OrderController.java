package com.heart.springcloud.alibaba.controller;

import com.heart.springcloud.alibaba.service.PaymentService;
import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/15 11:02
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/feign/nacos/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("[CONSUMER] 通过FEIGN 从NACOS注册中心调用PROVIDER => id :{}", id);
        CommonResult<Payment> result = paymentService.getPayment(id);
        log.info("[CONSUMER] 通过FEIGN 从NACOS注册中心调用PROVIDER <= result :{}", result);
        return result;
    }
}
