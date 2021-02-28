package com.heart.springcloud.service.impl;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.service.FeignHystrixPaymentService;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FeignHystrixPaymentFallbackServiceImpl
 * @Description: fallback实现类，对所实现的接口类里的每一个方法配置fallback方法
 * @Author: lwf14
 * @Date: 2021/2/28 16:00
 * @Version: v1.0
 */
@Component
public class FeignHystrixPaymentFallbackServiceImpl implements FeignHystrixPaymentService {

    @Override
    public CommonResult paymentInfo_success(Integer id) {
        return new CommonResult(500, "[CONSUMER] 服务调用失败，进入FeignHystrixPaymentFallbackServiceImpl.paymentInfo_success(). ID :" + id);
    }

    @Override
    public CommonResult paymentInfo_timeout(Integer id) {
        return new CommonResult(500, "[CONSUMER] 服务调用失败，进入FeignHystrixPaymentFallbackServiceImpl.paymentInfo_timeout(). ID :" + id);
    }
}
