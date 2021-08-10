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
    }

    @Override
    public void configure() throws Exception {
	ServiceRegistrationRoutePolicy policy = new ServiceRegistrationRoutePolicy();

	rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("mockClientGetAll")
		.routeProperty(ServiceDefinition.SERVICE_META_NAME, "bookGetAll")
		.routeProperty(ServiceDefinition.SERVICE_META_PORT, "9092")
		.routeProperty(ServiceDefinition.SERVICE_META_PATH, "api/book").routePolicy(policy)
		.bean(BookMockRouter.class, "getAll(})").marshal().json();

	rest().get("book/{id}").description("Details of an book by id").produces(MediaType.APPLICATION_JSON_VALUE)
		.route().routeId("mockGetById").routeProperty(ServiceDefinition.SERVICE_META_NAME, "bookGetById")
		.routeProperty(ServiceDefinition.SERVICE_META_PORT, "9092")
		.routeProperty(ServiceDefinition.SERVICE_META_PATH, "api/book/").routePolicy(policy)
		.bean(BookMockRouter.class, "getById(${header.id})").marshal().json();
    }

    public Collection<Book> getAll() {
	return books.values();
    }

    public Book getById(final Integer id) {
	return books.get(id);
    }

}
