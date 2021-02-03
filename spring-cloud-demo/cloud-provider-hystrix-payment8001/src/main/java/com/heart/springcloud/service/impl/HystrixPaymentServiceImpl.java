package com.heart.springcloud.service.impl;

import com.heart.springcloud.service.HystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: HystrixPaymentServiceImpl
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 10:57
 * @Version: v1.0
 */
@Service
@Slf4j
public class HystrixPaymentServiceImpl implements HystrixPaymentService {


    @Override
    public String paymentInfo_success(Integer id) {
        return "SERVICE SUCCESS! THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }


    /*
     * @HystrixCommand注解为接口加入Hystrix服务降级处理
     *      fallbackMethod指定当进行服务降级时，要调用的方法
     *      commandProperties指定 超过3000毫秒没有做出响应，则进行服务降级
     *
     * 通过int a = 10 / 0;这行代码测试，程序运行异常时同样可以触发降级
     */

    /**
     * 模拟程序处理超时
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            //模拟程序处理耗时5秒才完成
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("{}",e.getMessage());
        }

        //模拟程序运行报错
        //int a = 10/0;
        return "SERVICE SUCCESS!(*^_^*) THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }

    public String paymentInfo_timeoutHandler(Integer id) {
        return "SERVICE FAIL!(T_T) THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }
}
