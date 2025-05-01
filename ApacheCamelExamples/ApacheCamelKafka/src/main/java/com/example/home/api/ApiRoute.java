package com.example.home.api;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class ApiRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    rest().post("/sent/kafka").id("restSentKafka").to("direct:sent-kafka-in-seq");
    rest().post("/read/kafka").id("restReadKafka").to("direct:read-kafka-in-seq");
  }

}
