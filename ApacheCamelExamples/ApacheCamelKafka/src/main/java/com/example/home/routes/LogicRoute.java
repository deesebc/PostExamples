package com.example.home.routes;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class LogicRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:sent-kafka-in-seq").id("sent-kafka-in-seq")
      .log("init sent-kafka-in-seq")
      .log("body: ${body}")
      .to("kafka:my-topic")
      .setBody().simple("${null}")
      .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    from("direct:read-kafka-in-seq").id("read-kafka-in-seq")
            .log("init read-kafka-in-seq")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));
  }

}
