package com.example.home.ApacheCamelZipkin;

import org.apache.camel.zipkin.starter.CamelZipkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CamelZipkin
public class ApacheCamelZipKinApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ApacheCamelZipKinApplication.class, args);
	}

}
