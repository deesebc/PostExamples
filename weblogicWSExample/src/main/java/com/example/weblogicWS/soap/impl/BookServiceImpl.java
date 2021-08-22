package com.example.weblogicWS.soap.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.weblogicWS.pojo.Book;
import com.example.weblogicWS.ws.BookService;

@WebService(serviceName = "BookSoapService")
public class BookServiceImpl implements BookService {

    private static Map<Integer, Book> books = new HashMap<>();

    static {
	books.put(1, new Book(1, "Dune", "Frank Herbert"));
	books.put(2, new Book(2, "The stars my destination", "Alfred Bester"));
	books.put(3, new Book(3, "Ender's game", "Orson S. Card"));
    }

    @Override
    @WebMethod(operationName = "delete")
    public void delete(final Integer id) {
	books.remove(id);
    }

    @Override
    @WebMethod(operationName = "getAll")
    public List<Book> getAll() {
	return new ArrayList<Book>(books.values());
    }

    @Override
    @WebMethod(operationName = "getById")
    public Book getById(final Integer id) {
	return books.get(id);
    }

}
