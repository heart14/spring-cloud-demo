package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 12:08
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_PROVIDER_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("[CONSUMER] 调用PROVIDER插入 :{}", payment);
        return restTemplate.postForObject(PAYMENT_PROVIDER_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        log.info("[CONSUMER] 调用PROVIDER查询 :{}", id);
        return restTemplate.getForObject(PAYMENT_PROVIDER_URL + "/payment/get/" + id, CommonResult.class);
    }


}
