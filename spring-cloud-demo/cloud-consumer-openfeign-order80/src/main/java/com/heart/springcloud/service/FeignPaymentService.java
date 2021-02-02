package com.heart.springcloud.service;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: FeignPaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/2 10:57
 * @Version: v1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//使用OpenFeign，注解值为服务名
public interface FeignPaymentService {

    /*
     * 使用OpenFeign定义接口时
     *      接口方法名要和服务端接口的方法名相同
     *      接口参数列表要和服务端接口的参数列表相同
     *      接口要加注解，请求类型，uri要和服务端接口相同
     * 这样，通过OpenFeign接口注解上标明的服务名，即可调用到服务端该接口
     */

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


    /**
     * OpenFeign超时测试
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    CommonResult paymentTimeout();
}
