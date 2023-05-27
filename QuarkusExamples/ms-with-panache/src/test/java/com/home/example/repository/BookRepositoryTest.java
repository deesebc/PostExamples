package com.home.example.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.home.example.entity.Book;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class BookRepositoryTest {
    
    @Inject
    BookRepository repository;

    @Test
    public void findAll(){
        List<Book> list = repository.listAll();
        //assertThat(list.size(), equalTo(0));    
        assertThat(list.size(), equalTo(3));    
    }
}
