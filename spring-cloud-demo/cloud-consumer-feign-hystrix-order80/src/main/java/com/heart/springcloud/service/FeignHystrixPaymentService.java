package com.heart.springcloud.service;

import com.heart.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: FeignHystrixPaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 14:58
 * @Version: v1.0
 */
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE")
public interface FeignHystrixPaymentService {

    @GetMapping(value = "/payment/hystrix/success/{id}")
    CommonResult paymentInfo_success(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    CommonResult paymentInfo_timeout(@PathVariable("id") Integer id);
}
