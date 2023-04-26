package com.example.home.apachecamel;

import javax.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApacheCamelTestApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ApacheCamelTestApplication.class, args);
  }

}
