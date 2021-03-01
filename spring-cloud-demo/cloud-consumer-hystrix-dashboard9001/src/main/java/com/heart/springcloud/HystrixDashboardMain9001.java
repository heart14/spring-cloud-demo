package com.heart.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ClassName: HystrixDashboardMain9001
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/1 14:16
 * @Version: v1.0
 */
@SpringBootApplication
@EnableHystrixDashboard//开启Hystrix Dashboard
public class HystrixDashboardMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
