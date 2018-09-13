package es.home.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.wso2.msf4j.Request;

import es.home.example.pojo.Book;
import es.home.example.service.BookService;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookServiceImpl implements BookService {

	public BookServiceImpl() {
		System.out.println("BookServiceImpl");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy");

	}

	@Override
	@GET
	@Path("/{bookId}")
	public Book getBookById(@PathParam("bookId") final Integer bookId) {
		return new Book(bookId, "Alfred Bester", "The starts my destination");
	}

	@Override
	@GET
	@Path("/list")
	public List<Book> getList(@Context final Request request) {
		System.out.println("HttpMethod: " + request.getHttpMethod());
		System.out.println("ContentType " + request.getContentType());
		List<Book> exit = new ArrayList<>();
		exit.add(new Book(1, "Alfred Bester", "The starts my destination"));
		exit.add(new Book(2, "Orson Scott Card", "Ender's game"));
		return exit;
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
	}
}
