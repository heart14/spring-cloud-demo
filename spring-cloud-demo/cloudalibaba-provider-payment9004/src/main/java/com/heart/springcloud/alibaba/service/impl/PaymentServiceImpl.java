package com.heart.springcloud.alibaba.service.impl;

import com.heart.springcloud.alibaba.dao.PaymentDao;
import com.heart.springcloud.alibaba.service.PaymentService;
import com.heart.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentServiceImpl
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:19
 * @Version: v1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int createPayment(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
