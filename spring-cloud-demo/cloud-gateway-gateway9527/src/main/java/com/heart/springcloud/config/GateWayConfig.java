package com.heart.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: GateWayConfig
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/2 10:24
 * @Version: v1.0
 */
@Configuration
public class GateWayConfig {

    /**
     * 硬编码方式配置一个路由规则
     * 当访问http://localhost:9527/guonei时，会自动转发到http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        /*
            id 可以随便编写，保证唯一即可
            r.path 当前访问路径uri
            r.uri 路由转发目标路径
        */
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("route_baidu_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        routes.route("route_baidu_guoji", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
