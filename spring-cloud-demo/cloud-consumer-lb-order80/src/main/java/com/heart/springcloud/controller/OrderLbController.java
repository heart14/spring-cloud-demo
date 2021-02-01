package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.loadbalance.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @ClassName: OrderLbController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/1 15:53
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderLbController {

    public static final String SERVICE_NAME = "CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/payment/lb")
    public CommonResult getPaymentLb() {

        ServiceInstance instance = loadBalancer.getInstance(SERVICE_NAME);

        if (instance == null) {
            return new CommonResult(500, "NO REACHABLE SERVICE INSTANCE!");
        }

        URI uri = instance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", CommonResult.class);
    }
}
