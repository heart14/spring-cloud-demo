package com.heart.springcloud.service;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.service.impl.FeignHystrixPaymentFallbackServiceImpl;
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
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = FeignHystrixPaymentFallbackServiceImpl.class)//通配fallback，在这个注解上指明fallback实现类
public interface FeignHystrixPaymentService {

    @GetMapping(value = "/payment/hystrix/success/{id}")
    CommonResult paymentInfo_success(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    CommonResult paymentInfo_timeout(@PathVariable("id") Integer id);
}
