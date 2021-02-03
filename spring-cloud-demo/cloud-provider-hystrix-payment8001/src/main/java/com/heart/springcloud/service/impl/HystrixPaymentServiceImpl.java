package com.heart.springcloud.service.impl;

import com.heart.springcloud.service.HystrixPaymentService;
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

    @Override
    public String paymentInfo_timeout(Integer id) {
        try {
            //模拟程序处理耗时3秒才完成
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "SERVICE SUCCESS! THREAD :" + Thread.currentThread().getName() + ", ID :" + id;
    }
}
