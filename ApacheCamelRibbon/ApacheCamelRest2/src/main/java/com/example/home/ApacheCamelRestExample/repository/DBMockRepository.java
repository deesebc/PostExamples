package com.example.home.ApacheCamelRestExample.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DBMockRepository {

    private static Map<Integer, Book> books = new HashMap<>();
    static {
	books.put(1, new Book(1, "Dune", "Frank Herbert"));
	books.put(2, new Book(2, "The stars my destination", "Alfred Bester"));
	books.put(3, new Book(3, "Ender's game", "Orson S. Card"));
	books.put(4, new Book(4, "Configure Ribbon", "Server 2"));
    }

    public Book create(final Book book) {
	log.info("INSIDE CREATE: " + book);
	log.info("INSIDE ID: " + book.getId());
	Book retorno = books.put(book.getId(), book);
	log.info("INSIDE SIZE: " + books.size());
	return retorno;

    }

    public Book findById(final Integer id) {
	log.info("INSIDE FINBYID");
	return books.get(id);
    }

    public Collection<Book> getAll() {
	return books.values();
    }

    public void remove(final Integer id) {
	books.remove(id);
    }

    public Book update(final Integer id, final Book book) {
	return books.put(id, book);
    }
}
