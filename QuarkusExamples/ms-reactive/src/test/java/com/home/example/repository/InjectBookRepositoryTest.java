package com.home.example.repository;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.home.example.entity.Book;
import com.home.example.rest.BookResource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.main.QuarkusMainTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.mockito.Mockito;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class InjectBookRepositoryTest {
    
    //require quarkus-junit5-mockito
    @InjectMock
    BookRepository repository;

    @Test
    public void findAll(){
        Mockito.when(repository.listAll()).thenReturn(new ArrayList<Book>());

        List<Book> list = repository.listAll();
        assertThat(list.size(), equalTo(0));       
    }

    @Test
    public void getById() {
        Mockito.when(repository.findById(1l)).thenReturn(new Book(1l, "Orson S. Card", "Ender Game"));

        when()
            .get("/1").andReturn()
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("name", is("Ender Game"))
            .body("author", is("Orson S. Card"));

        Mockito.verify(repository, Mockito.times(1)).findById(1l);
    }
}
