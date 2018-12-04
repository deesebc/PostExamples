package es.home.example.testing.controller;

import es.home.example.testing.pojo.Book;
import es.home.example.testing.ws.client.BookWSClient;
import es.home.example.testing.ws.helper.BookWSHelper;

public class BookController {
	private static BookWSClient client = new BookWSClient();

	public Book getBookByStaticAttribute(final Integer id) {
		return client.getBook(id);
	}

	public Book getBookByStaticClass(final Integer id) {
		return BookWSHelper.getBook(id);
	}
}
