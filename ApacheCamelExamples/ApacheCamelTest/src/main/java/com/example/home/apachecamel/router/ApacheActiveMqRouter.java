package com.example.home.apachecamel.router;

import javax.jms.ConnectionFactory;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApacheActiveMqRouter extends RouteBuilder {

  @Value("${activemq.broker-url}")
  private String brokerUrl;

  @Bean
  public ActiveMQConnectionFactory jmsConnectionFactory() {
    ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory();
    connFactory.setBrokerURL(brokerUrl);
    return connFactory;
  }

  @Bean
  public ActiveMQComponent customPoolamq() {
    PooledConnectionFactory poolConnFactory = new PooledConnectionFactory();
    poolConnFactory.setConnectionFactory(jmsConnectionFactory());
    JmsConfiguration jmsConf = new JmsConfiguration(poolConnFactory);
    jmsConf.setClientId("customPoolamqCI");
    jmsConf.setDurableSubscriptionName("customPoolamqDSN");
    jmsConf.setSubscriptionName("customPoolamqSN");
    ActiveMQComponent activemq = new ActiveMQComponent();
    activemq.setConfiguration(jmsConf);
    return activemq;
  }

  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

    // Producers
    rest().post("activemq/public").produces(MediaType.APPLICATION_JSON_VALUE)
        .consumes(MediaType.APPLICATION_JSON_VALUE).to("direct:SendToPublic");

    from("direct:SendToPublic").routeId("SendToPublic").log("Public message incoming - ${body}")
        .to("customPoolamq:queue:publicQueue?exchangePattern=InOnly")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));

    // Consumers
    from("customPoolamq:queue:publicQueue").log("Reading public message - ${body}").end();

  }

}
