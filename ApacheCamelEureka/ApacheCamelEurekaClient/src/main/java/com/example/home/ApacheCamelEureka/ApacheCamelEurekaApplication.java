package com.example.home.ApacheCamelEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ApacheCamelEurekaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApacheCamelEurekaApplication.class, args);
    }



}
