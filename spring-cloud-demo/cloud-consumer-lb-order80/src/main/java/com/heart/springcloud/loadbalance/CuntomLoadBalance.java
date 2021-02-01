package com.heart.springcloud.loadbalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CuntomLoadBalance
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/2/1 15:56
 * @Version: v1.0
 */
@Component
@Slf4j
public class CuntomLoadBalance implements LoadBalancer {

    @Resource
    private DiscoveryClient discoveryClient;

    //接口请求次数，从0开始，服务重启后归零
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //每次请求接口，获取下一次计数器的值
    public final int getAndIncrement() {

        int inital;
        int current;

        do {
            //获取本次请求接口时 当前计数器的起始值
            inital = this.atomicInteger.get();
            //计数器值符合条件，则把计数器的起始值+1作为当前计数器的值  为什么+1  因为atomicInteger初始化时从0开始的
            current = inital >= Integer.MAX_VALUE ? 0 : inital + 1;

        } while (
            //这一步 自旋 没看懂
                !this.atomicInteger.compareAndSet(inital, current)
        );

        log.info("[Custom LoadBalance Algorithm] 进行第 {} 次请求", current);


        return current;
    }


    @Override
    public ServiceInstance getInstance(String serviceName) {

        //根据服务名，获取服务实例集合
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if (serviceInstances == null || serviceInstances.size() == 0) {
            log.info("[Custom LoadBalance Algorithm] No Reachable Service Instance!");
            return null;
        }

        //获取本次负载均衡要调用的服务下标值
        int index = getAndIncrement() % serviceInstances.size();

        //从服务集合中取出要调用的目标服务
        ServiceInstance instance = serviceInstances.get(index);

        log.info("[Custom LoadBalance Algorithm] Current Service Instance :{}", instance.getInstanceId());

        return instance;
    }
}
