package org.prj.gateway.config;


import lombok.RequiredArgsConstructor;
import org.prj.gateway.filter.CustomerFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    private final Environment env;
    private final CustomerFilter customerFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/msa/service/user/**")
                        .filters(f -> f.rewritePath("/msa/service/user/(?<segment>.*)", "/${segment}")
                                .filter(customerFilter.apply(new CustomerFilter.Config()))
                        )
                        .uri("lb://MSA-SERVICE-USER"))
                .route("catalog-service", r -> r.path("/msa/service/catalog/**")
                        .filters(f -> f.rewritePath("/msa/service/catalog/(?<segment>.*)", "/${segment}")
                                .filter(customerFilter.apply(new CustomerFilter.Config()))
                        )
                        .uri("lb://MSA-SERVICE-CATALOG"))
                .build();
    }

}


