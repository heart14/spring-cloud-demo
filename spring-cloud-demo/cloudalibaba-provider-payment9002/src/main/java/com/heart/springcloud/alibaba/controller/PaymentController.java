package com.heart.springcloud.alibaba.controller;

import com.heart.springcloud.alibaba.service.PaymentService;
import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/31 14:56
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/nacos/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("[PROVIDER] BY NACOS get payment :{}", paymentById);
        return new CommonResult(200, "[PROVIDER] BY NACOS, ServerPort :" + serverPort, paymentById);
    }

}
