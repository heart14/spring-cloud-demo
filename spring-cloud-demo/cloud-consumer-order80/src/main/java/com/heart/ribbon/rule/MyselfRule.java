package com.heart.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyselfRule
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/1/27 16:35
 * @Version: v1.0
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        //定义ribbon负载均衡策略
        return new RandomRule();
    }
}
