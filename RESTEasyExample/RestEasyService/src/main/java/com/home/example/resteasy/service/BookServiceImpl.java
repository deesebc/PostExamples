package com.home.example.resteasy.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.home.example.resteasy.bean.Book;

@Path("/book")
public class BookServiceImpl implements BookService {

    private static Map<Integer, Book> library = new HashMap<Integer, Book>();

    static {
	library.put(1, new Book(1, "Ender's Game", "Orson S. Card"));
	library.put(2, new Book(2, "The stars my destination", "Alfred Bester"));
    }

    @Override
    public Response create(final Book book) {
	if (null != library.get(book.getId())) {
	    return Response.status(Response.Status.NOT_MODIFIED).entity("Book is already in the library.").build();
	}

	library.put(book.getId(), book);
	return Response.status(Response.Status.CREATED).build();
    }

    @Override
    public Book read(final Integer id) {
	if (library.containsKey(id)) {
	    return library.get(id);
	} else {
	    return null;
	}
    }

}
