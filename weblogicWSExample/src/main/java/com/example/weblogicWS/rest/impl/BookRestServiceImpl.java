package com.example.weblogicWS.rest.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.weblogicWS.pojo.Book;
import com.example.weblogicWS.ws.BookService;

@Path("/book")
public class BookRestServiceImpl implements BookService {

    private static Map<Integer, Book> books = new HashMap<>();

    static {
	books.put(1, new Book(1, "Dune", "Frank Herbert"));
	books.put(2, new Book(2, "The stars my destination", "Alfred Bester"));
	books.put(3, new Book(3, "Ender's game", "Orson S. Card"));
    }

    @Override
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") final Integer id) {
	books.remove(id);
    }

    // http://localhost:7001/weblogicWS/resources/book/1
    @Override
    @GET
    @Produces("application/json")
    public List<Book> getAll() {
	return new ArrayList<Book>(books.values());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Book getById(@PathParam("id") final Integer id) {
	return books.get(id);
    }

}
