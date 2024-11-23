package com.bits.pilani.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .uri("lb://user-service"))
                .route("order-service", r -> r.path("/order/**")
                        .uri("lb://order-service"))
                .route("restaurant-service", r -> r.path("/restaurant/**")
                        .uri("lb://restaurant-service"))
                .route("delivery-service", r -> r.path("/delivery/**")
                        .uri("lb://delivery-service"))
                .build();
    }
}
