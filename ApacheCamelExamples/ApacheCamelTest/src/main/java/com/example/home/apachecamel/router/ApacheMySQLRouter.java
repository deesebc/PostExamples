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
public class ApacheMySQLRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

    // Producers
    rest().get("mysql/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE)
        .consumes(MediaType.APPLICATION_JSON_VALUE).to("direct:getBookById");


    from("direct:getBookById").routeId("getBookById")
        .setBody(simple("select * from library.BOOK where id = ${header.id}"))
        .to("jdbc:dataSource?outputType=SelectOne");
  }

}
