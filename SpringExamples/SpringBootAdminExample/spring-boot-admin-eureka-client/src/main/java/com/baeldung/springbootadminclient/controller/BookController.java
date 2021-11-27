package com.baeldung.springbootadminclient.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.springbootadminclient.pojo.Book;

@RestController
@RequestMapping(path = "/book")
public class BookController {

	public static Map<Integer, Book> books = new HashMap<>();

	static {
		books.put(1, new Book(1, "Ender's Game", "Orson S. Card"));
		books.put(2, new Book(2, "The starts my destination", "Alfred Bester"));
		books.put(3, new Book(3, "Dune", "Frank Herber"));
	}

	@GetMapping
	public List<Book> getAll() {
		return new ArrayList<>(books.values());
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Book getById(@NonNull @PathVariable("id") final Integer id) {
		return books.get(id);
	}

}
