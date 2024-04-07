package com.eventMangement.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

//@Configuration
//public class ApiGatewayConfiguration {
//    @Bean
//    public RouteLocator getewayRouter(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("order-service", r -> r.path("/order/**")
//                        .filters(f -> f.tokenRelay("myregistrationid"))
//                        .uri("http://localhost:9000"))
//                .build();
//    }
//}
