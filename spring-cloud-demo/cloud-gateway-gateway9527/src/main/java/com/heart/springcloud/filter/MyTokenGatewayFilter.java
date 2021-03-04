package com.heart.springcloud.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName: MyTokenGatewayFilter
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/4 11:00
 * @Version: v1.0
 */
@Component
@Slf4j
public class MyTokenGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(">>>>>> Custom Spring Cloud Gateway Global Filter[1] START!");

        //从请求中获取token 实际token不一定放在请求参数中，可能在Header中
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        if (StrUtil.isEmpty(token)) {
            log.error("LOGIN CHECK FAIL! TOKEN IS MISSING!");
            log.info("<<<<<< Custom Spring Cloud Gateway Global Filter[1] COMPLETE!");
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        log.info("LOGIN CHECK SUCCESS! TOKEN :{}", token);
        log.info("<<<<<< Custom Spring Cloud Gateway Global Filter[1] COMPLETE!");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
