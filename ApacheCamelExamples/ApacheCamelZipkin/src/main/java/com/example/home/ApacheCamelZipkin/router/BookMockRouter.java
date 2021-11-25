package com.example.home.ApacheCamelZipkin.router;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelZipkin.pojo.Book;

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

		rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("mockClientGetAll")
				.bean(BookMockRouter.class, "getAll(})").marshal().json();

		rest().get("book/{id}").description("Details of an book by id").produces(MediaType.APPLICATION_JSON_VALUE)
				.route().routeId("mockGetById").bean(BookMockRouter.class, "getById(${header.id})").marshal().json();
	}

	public Collection<Book> getAll() {
		return books.values();
	}

	public Book getById(final Integer id) {
		return books.get(id);
	}

}
