package com.heart.springcloud.service.impl;

import com.heart.springcloud.dao.PaymentDao;
import com.heart.springcloud.entities.Payment;
import com.heart.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentServiceImpl
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 11:28
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
