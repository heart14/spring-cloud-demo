package com.heart.springcloud.service;

/**
 * @ClassName: HystrixPaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/3 10:57
 * @Version: v1.0
 */
public interface HystrixPaymentService {

    /**
     * 测试接口，模拟正常服务
     *
     * @param id
     * @return
     */
    String paymentInfo_success(Integer id);

    /**
     * 测试接口，模拟程序处理耗时较长，导致接口超时
     *
     * @param id
     * @return
     */
    String paymentInfo_timeout(Integer id);

}
