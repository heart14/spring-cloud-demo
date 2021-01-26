package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderConsulController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/26 15:52
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderConsulController {

    public static final String SERVICE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public CommonResult payment() {
        log.info("[CONSUMER] 通过CONSUL调用PROVIDER");
        return restTemplate.getForObject(SERVICE_URL + "/payment/consul", CommonResult.class);
    }

}
