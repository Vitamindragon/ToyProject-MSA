package org.prj.gateway.config;


import lombok.RequiredArgsConstructor;
import org.prj.gateway.filter.CustomFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.core.env.Environment;


//@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    private final Environment env;
    private final CustomFilter customerFilter;

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/msa/service/user/**")
                        .filters(f -> f.rewritePath("/msa/service/user/(?<segment>.*)", "/${segment}")
                                .filter(customerFilter.apply(new CustomFilter.Config()))
                        )
                        .uri("lb://MSA-SERVICE-USER"))
                .route("catalog-service", r -> r.path("/msa/service/catalog/**")
                        .filters(f -> f.rewritePath("/msa/service/catalog/(?<segment>.*)", "/${segment}")
                                .filter(customerFilter.apply(new CustomFilter.Config()))
                        )
                        .uri("lb://MSA-SERVICE-CATALOG"))
                .route("order-service", r -> r.path("/msa/service/order/**")
                        .filters(f -> f.rewritePath("/msa/service/order/(?<segment>.*)", "/${segment}")
                                .filter(customerFilter.apply(new CustomFilter.Config()))
                        )
                        .uri("lb://MSA-SERVICE-ORDER"))
                .build();
    }

}
