package com.example.home.routes;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class LogicRoute extends RouteBuilder {

  @ConfigProperty(name = "log.message")
  String message;

  @Override
  public void configure() throws Exception {
    from("direct:sent-kafka-in-seq").id("sent-kafka-in-seq")
      .log("init sent-kafka-in-seq")
      .log("body: ${body}. Message: "+message)
      .to("kafka:my-topic?brokers={{kafka.brokers}}")
      .setBody().simple("${null}")
      .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    from("kafka:my-topic?brokers={{kafka.brokers}}")
            .id("read-kafka-in-seq")
            .log("init read-kafka-in-seq my-topic")
            .log("body: ${body}")
            .stop();
  }

}
