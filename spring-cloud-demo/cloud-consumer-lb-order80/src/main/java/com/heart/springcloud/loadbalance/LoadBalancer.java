package com.heart.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @ClassName: LoadBalancer
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/1 15:55
 * @Version: v1.0
 */
public interface LoadBalancer {

    ServiceInstance getInstance(String serviceName);
}
