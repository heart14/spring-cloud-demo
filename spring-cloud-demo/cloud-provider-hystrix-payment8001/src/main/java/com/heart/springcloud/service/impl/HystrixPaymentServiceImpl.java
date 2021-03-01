package com.heart.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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
     * 在服务提供者端进行服务降级处理
     *
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
//    @Override
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    public String paymentInfo_timeout(Integer id) {
//        try {
//            //模拟程序处理耗时5秒才完成
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            log.error("{}",e.getMessage());
//        }
//
//        //模拟程序运行报错
//        //int a = 10/0;
//        return "SERVICE SUCCESS!(*^_^*) THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
//    }


    /**
     * 服务降级fallback方法
     *
     * @param id
     * @return
     */
    public String paymentInfo_timeoutHandler(Integer id) {
        return "SERVICE FAIL!(T_T) THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }



    /*
     * 下面这部分用于测试在服务消费者端进行服务降级处理，保证提供者端能够正常响应而不是触发降级
     *
     * @HystrixCommand注解为接口加入Hystrix服务降级处理
     *      fallbackMethod指定当进行服务降级时，要调用的方法
     *      commandProperties指定 超过5000毫秒没有做出响应，则进行服务降级 而代码里设置线程只睡眠了3秒  所以能正常响应  而不是触发降级
     *
     */

    /**
     * 模拟程序处理超时
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            //模拟程序处理耗时3秒才完成
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage());
        }
        return "SERVICE SUCCESS!(*^_^*) THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }


    /*      ↑↑↑↑  服务降级  ↑↑↑↑*/

    /*      ↓↓↓↓  服务熔断  ↓↓↓↓*/

    /*
        commandProperties所有可选属性在抽象类HystrixCommandProperties里(line73)列出了明细，并进行了初始化默认值，
        如果在注解中进行了配置，则取配置的值，如果没有，则取抽象类中的默认值
     */

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否启用断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口时长，单位毫秒，在这个时间内，10次请求里60%请求失败，则开启熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后开启熔断
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******** id不能为负数！********");
        }
        String serialNumber = IdUtil.simpleUUID();
        return "SERVICE SUCCESS!(*^_^*) THREAD :" + Thread.currentThread().getName() + ", serialNumber :" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "SERVICE FAIL!(T_T) THREAD :" + Thread.currentThread().getName() + ",paymentCircuitBreaker服务暂不可用，请稍后再试! ID :" + id;
    }

}
