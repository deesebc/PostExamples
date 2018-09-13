package es.home.example.service;

import java.util.List;

import es.home.example.pojo.Book;

public interface BookService {
	Book getBookById(final Integer id);

	List<Book> getList();
}