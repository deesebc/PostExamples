package com.example.weblogicWS.ws;

import java.util.List;

import com.example.weblogicWS.pojo.Book;

public interface BookService {
    // http://localhost:7001/weblogicWS/BookSoapService

    void delete(final Integer id);

    List<Book> getAll();

    Book getById(final Integer id);

}
