package com.example.springbootadminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootAdminEurekaServerApp {

	public static void main(final String[] args) {
		SpringApplication.run(SpringBootAdminEurekaServerApp.class, args);
	}

}
