package com.example.home.ApacheCamelActiveMq.router;

import javax.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookApacheMqRouter extends RouteBuilder {



  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

    rest().post("activemq/book").produces(MediaType.APPLICATION_JSON_VALUE).route()
        .routeId("postActiveMqBookRoute").log("Message incoming - ${body}")
        // .to("activemq:queue:myqueue?transport.jms.ConnectionFactoryJNDIName=QueueConnectionFactory&java.naming.provider.url=failover:(tcp://localhost:8161)&java.naming.factory.initial=org.apache.activemq.jndi.ActiveMQInitialContextFactory")
        // .to("activemq:queue:myqueue?deliveryDelay=1")
        .to("activemq:queue:myqueue?exchangePattern=InOnly")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202));


  }


}
