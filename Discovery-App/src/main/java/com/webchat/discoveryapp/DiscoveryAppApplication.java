package com.webchat.discoveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryAppApplication.class, args);
    }

}
