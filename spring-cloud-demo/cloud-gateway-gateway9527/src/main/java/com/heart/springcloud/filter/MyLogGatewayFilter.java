package com.heart.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName: MyLogGatewayFilter
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/4 10:51
 * @Version: v1.0
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 定义过滤器处理逻辑
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info(">>>>>> Custom Spring Cloud Gateway Global Filter[0] START!");

        log.info("Request Id :{}", exchange.getRequest().getId());
        log.info("Request RemoteAddress :{}", exchange.getRequest().getRemoteAddress());
        log.info("Request URI :{}", exchange.getRequest().getURI());
        log.info("Request Path :{}", exchange.getRequest().getPath());
        log.info("Request Method :{}", exchange.getRequest().getMethod());
        log.info("Request MethodValue :{}", exchange.getRequest().getMethodValue());
        log.info("Request QueryParams :{}", exchange.getRequest().getQueryParams());
        log.info("Request Headers :{}", exchange.getRequest().getHeaders());
        log.info("Request Body :{}", exchange.getRequest().getBody());
        log.info("Request Cookies :{}", exchange.getRequest().getCookies());
        log.info("Request SslInfo :{}", exchange.getRequest().getSslInfo());

        log.info("<<<<<< Custom Spring Cloud Gateway Global Filter[0] COMPLETE!");
        return chain.filter(exchange);
    }

    /**
     * 定义过滤器在过滤链中的执行顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
