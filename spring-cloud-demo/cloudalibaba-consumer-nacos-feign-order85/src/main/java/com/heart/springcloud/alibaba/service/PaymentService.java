package com.heart.springcloud.alibaba.service;

import com.heart.springcloud.alibaba.service.fallback.PaymentFallbackServiceImpl;
import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: PaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/15 10:59
 * @Version: v1.0
 */
@FeignClient(value = "cloud-payment-service-nacos", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentService {

    @GetMapping(value = "/payment/nacos/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") Long id);
}
