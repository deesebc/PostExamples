package com.example.home.ApacheCamelMock.router;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cloud.ServiceDefinition;
import org.apache.camel.impl.cloud.ServiceRegistrationRoutePolicy;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelMock.pojo.Book;

@Component
public class BookMockRouter extends RouteBuilder {

    private static Map<Integer, Book> books = new HashMap<>();

    static {
	books.put(1, new Book(1, "Dune", "Frank Herbert"));
	books.put(2, new Book(2, "The stars my destination", "Alfred Bester"));
	books.put(3, new Book(3, "Ender's game", "Orson S. Card"));
	books.put(4, new Book(4, "Configure Ribbon", "Server 2"));
    }

//    @Autowired
//    private CamelContext context;

    @Override
    public void configure() throws Exception {
	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

//	from("servlet://0.0.0.0:9090").routePolicy(new ServiceRegistrationRoutePolicy())
//		.log("Route ${routeId} has been invoked");

	// OK
	from("servlet:http://0.0.0.0:9092/api/book").routeId("foo-1")
		.routeProperty(ServiceDefinition.SERVICE_META_ID, "foo-2")
		.routeProperty(ServiceDefinition.SERVICE_META_NAME, "mockClient")
		.routePolicy(new ServiceRegistrationRoutePolicy()).log("Route ${routeId} has been invoked");

	rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().bean(BookMockRouter.class, "getAll(})");

//	from("direct:registerFooService").setBody()
//		.constant(
//			ImmutableRegistration.builder().id("foo-1").name("foo").address("localhost").port(8500).build())
//		.to("consul:agent?action=REGISTER");

//	from("servlet:http://0.0.0.0:9090").routeId("foo-1")
//		.routeProperty(ServiceDefinition.SERVICE_META_ID, "foo-1")
//		.routeProperty(ServiceDefinition.SERVICE_META_NAME, "foo")
//		.routePolicy(new ServiceRegistrationRoutePolicy());

//	from("servlet:http://0.0.0.0:9090").routePolicy(new ServiceRegistrationRoutePolicy()).routeGroup("my-service")
//		.routeId("my-id").routeProperty(ServiceDefinition.SERVICE_META_PORT, "8080")
//		.log("Route ${routeId} has been invoked");

    }

    public Collection<Book> getAll() {
	return books.values();
    }
}
