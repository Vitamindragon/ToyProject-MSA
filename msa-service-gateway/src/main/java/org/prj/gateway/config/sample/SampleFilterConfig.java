package org.prj.gateway.config.sample;

import org.springframework.core.env.Environment;

//@Configuration
public class SampleFilterConfig {
    Environment env;

    public SampleFilterConfig(Environment env) {
        this.env = env;
    }

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, AuthorizationHeaderFilter myfilter) {
//
//        return builder.routes()
//                .route(r -> r.path("/first-service/**")
//                            .filters(f -> f.addRequestHeader("first-request", "first-request-header-by-java")
//                                           .addResponseHeader("first-response", "first-response-header-by-java")
////                                           .filter(myfilter.apply(new AuthorizationHeaderFilter.Config()))
//                            )
//                            .uri("http://localhost:8081"))
//                .route(r -> r.path("/second-service/**")
//                        .filters(f -> f.addRequestHeader("second-request", "second-request-header-by-java")
//                                .addResponseHeader("second-response", "second-response-header-by-java"))
//                        .uri("http://localhost:8082"))
//                .build();
//    }

}