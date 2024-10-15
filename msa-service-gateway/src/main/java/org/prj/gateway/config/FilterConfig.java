package org.prj.gateway.config;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final Environment env;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/first-service/**")
                            .filters(f -> f.addRequestHeader("first-request", "first-request-header-by-java")
                                           .addResponseHeader("first-response", "first-response-header-by-java")
//                                           .filter(myfilter.apply(new AuthorizationHeaderFilter.Config()))
                            )
                            .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header-by-java")
                                .addResponseHeader("second-response", "second-response-header-by-java"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
