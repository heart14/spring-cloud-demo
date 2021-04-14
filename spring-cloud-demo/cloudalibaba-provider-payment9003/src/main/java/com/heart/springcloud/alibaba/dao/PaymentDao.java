package com.heart.springcloud.alibaba.dao;

import com.heart.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PaymentMapper
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/4/14 16:09
 * @Version: v1.0
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
