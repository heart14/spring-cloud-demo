package com.heart.springcloud.alibaba.service;

import com.heart.springcloud.entities.Payment;

/**
 * @ClassName: PaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:09
 * @Version: v1.0
 */
public interface PaymentService {

    /**
     * 创建payment
     *
     * @param payment
     * @return
     */
    int createPayment(Payment payment);

    /**
     * 查询payment
     *
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
