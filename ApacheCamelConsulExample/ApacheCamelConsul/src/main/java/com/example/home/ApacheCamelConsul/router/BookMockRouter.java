package com.example.home.ApacheCamelConsul.router;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cloud.ServiceDefinition;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.cloud.ServiceRegistrationRoutePolicy;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelConsul.pojo.Book;

@Component
public class BookMockRouter extends RouteBuilder {

    @Autowired
    CamelContext context;

    @Override
    public void configure() throws Exception {

	// dzone way

	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

	JacksonDataFormat formatBook = new JacksonDataFormat(Book.class);

	from("servlet:http://0.0.0.0:9092/api/book").routeId("ws-foo-1")
		.routeProperty(ServiceDefinition.SERVICE_META_ID, "ws-foo-2")
		.routeProperty(ServiceDefinition.SERVICE_META_NAME, "ws-mockClient")
		.routePolicy(new ServiceRegistrationRoutePolicy()).log("Route ${routeId} has been invoked");

	rest("/book").get("").description("Find account by id").outType(Book.class).route()
		.serviceCall("consul:mockClient/book").unmarshal(formatBook).endRest();

// ribbon way
//	rest().get("book").route().log("Route ${routeId} has been invoked 1").removeHeader(Exchange.HTTP_URI)
//		.serviceCall().name("mockClient/book").log("Route ${routeId} has been invoked 2");

    }

}
