package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ribbon.RibbonConfiguration;
import org.apache.camel.component.ribbon.cloud.RibbonServiceLoadBalancer;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.ActuatorHealthPing;

@Component
public class BookRibbonRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);
	RibbonConfiguration configuration = new RibbonConfiguration();
	configuration.addProperty("ServerListRefreshInterval", "1000");
//	configuration.addProperty("ConnectTimeout", "10000");
//	configuration.addProperty("MaxAutoRetries", "2");

	configuration.addProperty("MaxAutoRetries", "1");
	configuration.addProperty("MaxAutoRetriesNextServer", "2");
	configuration.addProperty("OkToRetryOnAllOperations", "true");
	configuration.addProperty("ReadTimeout", "2000");
	configuration.addProperty("ConnectTimeout", "1000");
	configuration.addProperty("ServerListRefreshInterval", "2");
	configuration.addProperty("retryableStatusCodes", "400");
	configuration.addProperty("MaxAutoRetries", "500,502");
	configuration.addProperty("MaxAutoRetries", "2");

//	   MaxAutoRetries: 1
//	   MaxAutoRetriesNextServer: 2
//	   OkToRetryOnAllOperations: true
//	   ReadTimeout: 1000
//	   ConnectTimeout: 250
//	   ServerListRefreshInterval: 1000
//	   retryableStatusCodes: 500,404,502
//	   ServerListRefreshInterval: 2000

//	configuration.setPing(new PingUrl(false, "/actuator/health", "UP")); // https://github.com/Netflix/ribbon/blob/master/ribbon-httpclient/src/main/java/com/netflix/loadbalancer/PingUrl.java
	configuration.setPing(new ActuatorHealthPing());
	configuration.setClientName("LAZO");
	// sample-client.ribbon.MaxAutoRetries=1

	RibbonServiceLoadBalancer loadBalancer = new RibbonServiceLoadBalancer(configuration);

//.removeHeaders("CamelHttp*")
//	rest().get("book").route().serviceCall().name("app-camel-mock-client/book?bridgeEndpoint=true") -> ok to remove camelhttp to avoid bridgeendpoint

//	rest().get("book").route().removeHeader(Exchange.HTTP_URI).serviceCall().name("foo/api/book")
//		.ribbonLoadBalancer().end(); // OK CONF BY DEFAULT

	rest().get("book").route().removeHeader(Exchange.HTTP_URI).serviceCall().name("foo/api/book")
		.loadBalancer(loadBalancer).end();

//	rest().get("book").route().removeHeader(Exchange.HTTP_URI).serviceCall().loadBalancer(loadBalancer)
//		.name("foo/book");
//		.ribbonLoadBalancer().end().log("Body: ${body.[1].author}").end(); //FAIL
//		.ribbonLoadBalancer().end().convertBodyTo(List.class).unmarshal().json(); //FAIL
    }

//	from("rest:get:book:/").serviceCall().name("discovery-client/books?bridgeEndpoint=true").ribbonLoadBalancer()
////    .consulServiceDiscovery()
//	.end().unmarshal().json();

//rest("/serviceCall").get().produces(MediaType.APPLICATION_JSON_VALUE).route()
//	.serviceCall("discovery-client/books?bridgeEndpoint=true")
//	// Is it becouse http endpoint produces a Stream as the body and once the stream is read, it is no
//	// longer available.
//	.convertBodyTo(String.class).log("Body: ${body}").unmarshal().json();

//rest().get("book").route().log("--entrando--").serviceCall()
//	.name("app-camel-mock-client/book?bridgeEndpoint=true").ribbonLoadBalancer()
////	.consulServiceDiscovery().end();
//	.end().log("--saliendo--").unmarshal().json();

// SEMI OK
//rest().get("book").route().log("--entrando--").serviceCall()
//	.name("app-camel-mock-client/book?bridgeEndpoint=true").ribbonLoadBalancer()
////.consulServiceDiscovery().end();
//	.end().log("--saliendo--: ${body}").unmarshal().json();

//rest().get("book").route().log("--entrando--").serviceCall()
//	.name("app-camel-mock-client/book?bridgeEndpoint=true").ribbonLoadBalancer()
////.consulServiceDiscovery().end();
//	.end().log(">> - ${body}").convertBodyTo(String.class).marshal().json(JsonLibrary.Jackson)
//	.log("${body}");

}
