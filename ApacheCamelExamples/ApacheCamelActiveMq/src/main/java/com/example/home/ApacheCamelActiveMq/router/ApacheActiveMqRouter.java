package com.example.home.ApacheCamelActiveMq.router;

import javax.jms.ConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApacheActiveMqRouter extends RouteBuilder {

  @Bean
  private ConnectionFactory activeMQConnectionFactory() {
    return new ActiveMQConnectionFactory();
  }



  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

    // Producers
    rest().post("activemq/public").produces(MediaType.APPLICATION_JSON_VALUE)
        .consumes(MediaType.APPLICATION_JSON_VALUE).route().routeId("postPublicQueue")
        .log("Public message incoming - ${body}")
        .to("activemq:queue:publicQueue?exchangePattern=InOnly")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    rest().post("activemq/private").produces(MediaType.APPLICATION_JSON_VALUE)
        .consumes(MediaType.APPLICATION_JSON_VALUE).route().routeId("postPrivateQueue")
        .log("Private message incoming - ${body}")
        .to("activemq:queue:privateQueue?connectionFactory=activeMQConnectionFactory&exchangePattern=InOnly&username=privateUser&password={{activemq.privateQueue.password}}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    rest().post("activemq/notAllowed").produces(MediaType.APPLICATION_JSON_VALUE)
        .consumes(MediaType.APPLICATION_JSON_VALUE).route().routeId("notAllowedRoute")
        .log("Message incoming - ${body}").to("activemq:queue:privateQueue?exchangePattern=InOnly")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    // Consumers
    from("activemq:queue:publicQueue").log("Reading public message incoming - ${body}").end();

    from(
        "activemq:queue:privateQueue?connectionFactory=activeMQConnectionFactory&exchangePattern=InOnly&username=privateUser&password={{activemq.privateQueue.password}}")
            .log("Reading private message incoming - ${body}").end();


  }


}
