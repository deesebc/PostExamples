package es.home.example.service;

import java.util.List;

import org.wso2.msf4j.Request;

import es.home.example.pojo.Book;

public interface BookService {
    Book getBookById(final Integer id);

    List<Book> getList(Request request);

}