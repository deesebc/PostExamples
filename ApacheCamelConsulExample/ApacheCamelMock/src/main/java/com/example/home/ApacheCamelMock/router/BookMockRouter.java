package com.example.home.ApacheCamelMock.router;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cloud.ServiceDefinition;
import org.apache.camel.impl.cloud.ServiceRegistrationRoutePolicy;
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
//	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

//	from("servlet://0.0.0.0:9090").routePolicy(new ServiceRegistrationRoutePolicy())
//		.log("Route ${routeId} has been invoked");

//	from("servlet:http://0.0.0.0:9092").routePolicy(new ServiceRegistrationRoutePolicy()).routeGroup("my-service")
//		.routeId("my-id").routeProperty(ServiceDefinition.SERVICE_META_PORT, "8080")
//		.log("Route ${routeId} has been invoked");

//	from("servlet:http://0.0.0.0:9092").routePolicy(new ServiceRegistrationRoutePolicy()).routeGroup("my-service")
//		.routeId("my-id").routeProperty(ServiceDefinition.SERVICE_META_PORT, "8080")
//		.log("Route ${routeId} has been invoked");

//	from("direct:start").routePolicy(new ServiceRegistrationRoutePolicy()).routeGroup("client").routeId("mock")
//		.log("Route ${routeId} has been invoked");

//	from("direct:start").routeId("account-consul").marshal().json(JsonLibrary.Jackson)
//		.setHeader(Exchange.HTTP_METHOD, constant("PUT"))
//		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//		.setHeader("CamelConsulAction", constant("register"))
//		.toD("http://localhost:8500/v1/agent/service/register");
//	from("direct:stop").shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
//		.toD("http://192.168.99.100:8500/v1/agent/service/deregister/${header.id}");

	// OK
//	from("servlet:http://localhost:9092/api/book").routeId("foo-1")
	from("direct:start").routeId("foo-1").routeProperty(ServiceDefinition.SERVICE_META_ID, "foo-2")
		.routeProperty(ServiceDefinition.SERVICE_META_NAME, "mockClient")
		.routeProperty(ServiceDefinition.SERVICE_META_PORT, "9092")
		.routeProperty(ServiceDefinition.SERVICE_META_HOST, "localhost")
		.routeProperty("ServiceAddress", "localhost")
//		.routeProperty(ServiceDefinition.SERVICE_META_PATH, "//localhost:9092/api/book")
		.routeProperty(ServiceDefinition.SERVICE_META_PATH, "api/book")
		.routePolicy(new ServiceRegistrationRoutePolicy()).log("Route ${routeId} has been invoked");

	rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("mockClientGetAll_1")
		.id("mockClientGetAll").log("mockClient - get - book").bean(BookMockRouter.class, "getAll(})");

	rest().get("book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("mockClientGetBook_1")
		.id("mockClientGetBook")
//		.log("mockClient - get - book").bean(BookMockRouter.class, "getById(${exchangeProperty.id})");
		.log("mockClient - get - book").bean(BookMockRouter.class, "getById(})");

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

    public Book getById() {// final Integer id) {
	return books.get(1);
    }
}
