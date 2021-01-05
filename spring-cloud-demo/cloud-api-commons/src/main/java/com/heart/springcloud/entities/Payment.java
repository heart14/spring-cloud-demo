package com.heart.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Payment
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/5 11:18
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = -7207117924980273263L;

    private Long id;

    private String serial;
}
