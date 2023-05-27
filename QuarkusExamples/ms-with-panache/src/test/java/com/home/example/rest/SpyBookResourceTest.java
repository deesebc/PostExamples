package com.home.example.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.home.example.entity.Book;
import com.home.example.mapper.BookMapper;
import com.home.example.repository.BookRepository;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class SpyBookResourceTest {

    // maybe not works because its a interface, greetingResource works
    // @InjectSpy
    // BookMapper mapper;

    // @Test
    // public void getByIdSpy() {
    //     given()
    //             .body(new Book(1l, "Orson S. Card", "Ender Game"))
    //             .contentType(ContentType.JSON)
    //             .put("/1");

    //     Mockito.verify(mapper, Mockito.times(1)).update(Mockito.any(), Mockito.any());
    // }

}
