package com.baeldung.springbootadminclient.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baeldung.springbootadminclient.pojo.Book;

@Controller
@RequestMapping("/book")
public class BookController {

	public static Map<Integer, Book> books = new HashMap<>();

	static {
		books.put(1, new Book(1, "Ender's Game", "Orson S. Card"));
		books.put(2, new Book(2, "The starts my destination", "Alfred Bester"));
		books.put(3, new Book(3, "Dune", "Frank Herber"));
	}

	@RequestMapping("")
	public List<Book> getAll() {
		return new ArrayList<>(books.values());
	}

	@RequestMapping("/{id}")
	public Book getById(@RequestParam final Integer id) {
		return books.get(id);
	}

}
