package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ribbon.RibbonConfiguration;
import org.apache.camel.component.ribbon.cloud.RibbonServiceLoadBalancer;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.ping.ActuatorHealthPing;

@Component
public class BookRibbonRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

//	rest().get("book").route().serviceCall().name("app-camel-mock-client/api/book?bridgeEndpoint=true")
//		.ribbonLoadBalancer().end(); // OK

	// .removeHeader(Exchange.HTTP_URI) -> avoid to include bridgeEndpoint
//	rest().get("book").route().removeHeader(Exchange.HTTP_URI).serviceCall().name("app-camel-mock-client/api/book")
//		.ribbonLoadBalancer().end(); // OK

	RibbonConfiguration configuration = new RibbonConfiguration();
	configuration.setPing(new ActuatorHealthPing());
	RibbonServiceLoadBalancer loadBalancer = new RibbonServiceLoadBalancer(configuration);

	rest().get("book").route().removeHeader(Exchange.HTTP_URI).serviceCall().name("app-camel-mock-client/api/book")
		.loadBalancer(loadBalancer).end();
    }

}
