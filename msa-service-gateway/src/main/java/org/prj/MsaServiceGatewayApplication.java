package org.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaServiceGatewayApplication.class, args);
    }

}
