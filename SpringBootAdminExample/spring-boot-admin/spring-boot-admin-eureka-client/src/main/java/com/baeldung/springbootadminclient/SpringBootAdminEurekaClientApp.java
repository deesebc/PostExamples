package com.baeldung.springbootadminclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootAdminEurekaClientApp {

	public static void main(final String[] args) {
		SpringApplication.run(SpringBootAdminEurekaClientApp.class, args);
	}
}
