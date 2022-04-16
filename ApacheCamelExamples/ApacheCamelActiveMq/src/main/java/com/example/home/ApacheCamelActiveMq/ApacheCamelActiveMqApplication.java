package com.example.home.ApacheCamelActiveMq;

import javax.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApacheCamelActiveMqApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ApacheCamelActiveMqApplication.class, args);
  }

}
