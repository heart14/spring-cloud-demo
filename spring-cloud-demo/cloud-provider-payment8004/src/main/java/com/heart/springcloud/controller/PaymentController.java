package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/19 16:42
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/zk")
    public CommonResult paymentZk() {
        return new CommonResult(200, "ZOOKEEPER server port [" + serverPort + "] SUCCESS! ", UUID.randomUUID().toString().toUpperCase());
    }

}
