package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class BookRibbonRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

	rest().get("book").route().serviceCall().name("app-camel-mock-client/book?bridgeEndpoint=true")
		.ribbonLoadBalancer().end(); // OK
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
