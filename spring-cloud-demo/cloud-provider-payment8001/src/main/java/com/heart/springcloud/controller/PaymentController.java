package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import com.heart.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        log.info("当前Eureka注册中心所有可提供的服务 :{}", services);

        if (services != null) {
            for (String service : services) {
                List<ServiceInstance> instances = discoveryClient.getInstances(service);
                for (ServiceInstance instance : instances) {
                    log.info("[{}]服务的实例信息 :Host :{}, Port :{}, Uri :{}, InstanceId :{}, ServiceId :{}, Scheme :{}, Metadata :{}", service, instance.getHost(),instance.getPort(),instance.getUri(),instance.getInstanceId(),instance.getServiceId(),instance.getScheme(),instance.getMetadata());
                }
            }
        }

        return new CommonResult(200, "Server Port[" + serverPort + "] service discovery", discoveryClient);
    }

}
