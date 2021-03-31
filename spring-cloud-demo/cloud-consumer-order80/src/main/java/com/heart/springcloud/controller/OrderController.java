package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 12:08
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    //之前没有通过Eureka注册中心调用微服务时，需要指定所调用的服务ip:port
    //public static final String PAYMENT_PROVIDER_URL = "http://localhost:8001";

    //通过Eureka注册中心调用微服务时，不关注具体IP，只需要知道我要调用的微服务名称即可
    public static final String PAYMENT_PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("[CONSUMER] 调用PROVIDER插入 :{}", payment);
        return restTemplate.postForObject(PAYMENT_PROVIDER_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        log.info("[CONSUMER] 调用PROVIDER查询 :{}", id);
        return restTemplate.getForObject(PAYMENT_PROVIDER_URL + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping(value = "/consumer/payment/get/entity/{id}")
    public CommonResult getPaymentEntityById(@PathVariable Long id) {
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_PROVIDER_URL + "/payment/get/" + id, CommonResult.class);
        log.info("RestTemplate.getForEntity()方法的返回值 :{}", responseEntity);
        return responseEntity.getBody();
    }

    //微服务调用 测试zipkin sleuth
    @GetMapping(value = "/consumer/payment/zipkin")
    public CommonResult getPaymentZipkin() {
        CommonResult result = restTemplate.getForObject(PAYMENT_PROVIDER_URL + "/payment/zipkin", CommonResult.class);
        log.info("[CONSUMER] 调用PROVIDER链路跟踪 :{}", result);
        return result;
    }
}
