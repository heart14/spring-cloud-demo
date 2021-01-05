package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import com.heart.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 11:33
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.createPayment(payment);
        log.info("[PROVIDER] 插入 :{}, {}", result > 0 ? "成功" : "失败", payment);

        if (result > 0) {
            return new CommonResult(200, "SUCCESS！");
        } else {
            return new CommonResult(500, "FAIL！");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("[PROVIDER] 查询 :{}", id);

        if (paymentById != null) {
            return new CommonResult(200, "SUCCESS！", paymentById);
        } else {
            return new CommonResult(500, "FAIL！");
        }
    }

}
