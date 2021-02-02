package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import com.heart.springcloud.service.FeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderOpenFeignController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/2 11:03
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderOpenFeignController {

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER => id :{}", id);
        CommonResult<Payment> result = feignPaymentService.getPaymentById(id);
        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", result);
        return result;
    }
}
