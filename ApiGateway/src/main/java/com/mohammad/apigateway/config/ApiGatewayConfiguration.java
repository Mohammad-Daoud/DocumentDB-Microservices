package com.mohammad.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoute (RouteLocatorBuilder builder){
        return builder.routes()
                .route(routeFun -> routeFun.path("/master/**")
                        .uri("lb://master"))
                .route(routeFun -> routeFun.path("/read/**")
                        .uri("lb://read"))
                .build();
    }
}

