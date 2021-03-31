package com.heart.springcloud.alibaba.controller;

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
 * @ClassName: OrderNacosController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/31 15:56
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderNacosController {

    @Value("${service-url.nacos-payment-service}")
    private String paymentServiceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult forObject = restTemplate.getForObject(paymentServiceUrl + "/payment/nacos/" + id, CommonResult.class);
        log.info("[CONSUMER] BY NACOS get payment :{}", forObject);
        return forObject;
    }

}
