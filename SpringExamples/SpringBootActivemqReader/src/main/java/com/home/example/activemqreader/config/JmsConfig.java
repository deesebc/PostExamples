package com.home.example.activemqreader.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfig {

  @Value("${activemq.broker-url}")
  private String brokerUrl;

  @Value("${activemq.user}")
  private String user;

  @Value("${activemq.password}")
  private String password;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
	  ActiveMQConnectionFactory factory = null;
    if ("".equals(user)) {
    	factory =  new ActiveMQConnectionFactory(brokerUrl);
    }
    factory =  new ActiveMQConnectionFactory(user, password, brokerUrl);
    RedeliveryPolicy policy = new RedeliveryPolicy();
    policy.setMaximumRedeliveries(-1);
    policy.setInitialRedeliveryDelay(0);
    policy.setRedeliveryDelay(3000);
    factory.setRedeliveryPolicy(policy);
    return factory;
  }

}
