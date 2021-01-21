package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderZkController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/21 16:27
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderZkController {

    //通过服务名进行远程调用
    //注意这里的大小写要和zookeeper服务器上的节点一致！
    public static final String SERVICE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public CommonResult payment() {
        log.info("[CONSUMER] 通过ZOOKEEPER调用PROVIDER");
        return restTemplate.getForObject(SERVICE_URL + "/payment/zk", CommonResult.class);
    }
}
