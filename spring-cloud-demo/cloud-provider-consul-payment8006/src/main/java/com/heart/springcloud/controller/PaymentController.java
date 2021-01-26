package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/25 16:38
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/consul")
    public CommonResult paymentConsul() {
        return new CommonResult(200, "CONSUL server port [" + serverPort + "] SUCCESS! ", UUID.randomUUID().toString().toUpperCase());
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        log.info("当前CONSUL注册中心所有可提供的服务 :{}", services);

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
