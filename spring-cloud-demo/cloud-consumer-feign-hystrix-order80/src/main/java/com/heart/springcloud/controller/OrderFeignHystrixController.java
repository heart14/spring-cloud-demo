package com.heart.springcloud.controller;

import com.heart.springcloud.entities.CommonResult;
import com.heart.springcloud.service.FeignHystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderFeignHystrixController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 15:00
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderFeignHystrixController {

    @Resource
    private FeignHystrixPaymentService feignHystrixPaymentService;

    @GetMapping(value = "/consumer/payment/hystrix/success/{id}")
    public CommonResult paymentInfo_success(@PathVariable("id") Integer id) {
        log.info("[CONSUMER] 通过FEIGN HYSTRIX调用PROVIDER => id :{}", id);
        CommonResult commonResult = feignHystrixPaymentService.paymentInfo_success(id);
        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", commonResult);
        return commonResult;
    }

//    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    public CommonResult paymentInfo_timeout(@PathVariable("id") Integer id) {
//        log.info("[CONSUMER] 通过FEIGN HYSTRIX调用PROVIDER => id :{}", id);
//        CommonResult commonResult = feignHystrixPaymentService.paymentInfo_timeout(id);
//        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", commonResult);
//        return commonResult;
//    }


    /**
     * 在服务消费者端进行服务降级处理
     *      指定超过2秒没有响应的时候触发降级
     *      而此时服务提供者端配置的是 程序睡眠3秒，5秒没有响应触发提供者端的服务降级
     *      所以通过这个接口测试的话，服务提供者端能够正常提供服务，而在消费者端由于Hystrix的配置，会触发服务降级
     */

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5500")
    })
    public CommonResult paymentInfo_timeout(@PathVariable("id") Integer id) {
        log.info("[CONSUMER] 通过FEIGN HYSTRIX调用PROVIDER => id :{}", id);

        //调用远程服务自身超时时会进入fallback
        CommonResult commonResult = feignHystrixPaymentService.paymentInfo_timeout(id);

        //同样 自身程序异常也会进入fallback
        //int a = 10 / 0;

        log.info("[CONSUMER] 通过OPEN FEIGN调用PROVIDER <= result :{}", commonResult);
        return commonResult;
    }

    public CommonResult paymentInfo_timeoutHandler(@PathVariable("id") Integer id) {
        log.error("[CONSUMER] 服务调用超时，进入FALLBACK METHOD( paymentInfo_timeoutHandler )");
        return new CommonResult(500, "[CONSUMER] 服务调用失败，进入FALLBACK METHOD(paymentInfo_timeoutHandler). ID :" + id);
    }
}
