package com.example.home.ApacheCamelRestExample;

import org.apache.camel.opentracing.starter.CamelOpenTracing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CamelOpenTracing
public class ApacheCamelRestExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApacheCamelRestExampleApplication.class, args);
    }

}
