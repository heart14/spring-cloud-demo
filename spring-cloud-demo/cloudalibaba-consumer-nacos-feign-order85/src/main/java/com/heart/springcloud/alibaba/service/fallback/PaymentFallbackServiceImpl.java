package com.heart.springcloud.alibaba.service.fallback;

import com.heart.springcloud.alibaba.service.PaymentService;
import com.heart.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PaymentFallbackServiceImpl
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/15 11:01
 * @Version: v1.0
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentService {

    @Override
    public CommonResult getPayment(Long id) {
        return new CommonResult(500, "order85 服务调用失败，触发降级熔断 PaymentFallbackServiceImpl.getPayment(Long id)");
    }
}
