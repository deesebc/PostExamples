package com.home.example.repository;

import java.util.ArrayList;
import java.util.List;

import com.home.example.entity.Book;

import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
// @Mock
//Incompatible with integration tests (BookResourceTest), will use always this mock
//old approach : https://es.quarkus.io/blog/mocking/
public class MockBookRepository extends BookRepository{

    @Override
    public List<Book> listAll(){
        List<Book> list = new ArrayList<>();
        return list;
    }
    
}
