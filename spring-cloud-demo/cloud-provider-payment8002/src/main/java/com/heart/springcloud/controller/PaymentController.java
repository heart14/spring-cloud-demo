package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import com.heart.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.createPayment(payment);
        log.info("[PROVIDER] 插入 :{}, {}", result > 0 ? "成功" : "失败", payment);

        if (result > 0) {
            return new CommonResult(200, "Server Port[" + serverPort + "] SUCCESS！");
        } else {
            return new CommonResult(500, "Server Port[" + serverPort + "] FAIL！");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("[PROVIDER] 查询 :{}", id);

        if (paymentById != null) {
            return new CommonResult(200, "Server Port[" + serverPort + "] SUCCESS！", paymentById);
        } else {
            return new CommonResult(500, "Server Port[" + serverPort + "] FAIL！");
        }
    }


    /**
     * 手写一个负载均衡算法之 被调用接口
     */
    @GetMapping(value = "/payment/lb")
    public CommonResult paymentLb() {
        return new CommonResult(200, "[Custom LoadBalance Algorithm] Provided by server port " + serverPort);
    }


    /**
     * 让程序等待3秒才返回响应，测试OpenFeign的超时控制，OpenFeign底层是Ribbon，所以超时控制也是由Ribbon来实现的，Ribbon默认超时时长为1s
     *
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public CommonResult paymentTimeout() {
        long l1 = System.currentTimeMillis();
        log.info("[OpenFeign] 服务调用超时测试，begin timestamp:{}", l1);
        try {
            for (int i = 1; i <= 3; i++) {
                log.info("{} SECOND LATER......", i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage());
        }
        long l2 = System.currentTimeMillis();
        log.info("[OpenFeign] 服务调用超时测试，end timestamp:{}, 耗时 :{}s", l2, (l2 - l1) / 1000);
        return new CommonResult(200, "Server port [8002] :程序耗时 3s");
    }
}
