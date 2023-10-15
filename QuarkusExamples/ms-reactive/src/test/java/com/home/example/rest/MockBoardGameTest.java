package com.home.example.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.home.example.entity.Book;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

//@QuarkusTest
public class MockBoardGameTest {
    
    // @InjectMock
    // //require quarkus-rest-client-jackson
    // @RestClient
    // BookResource resource;

    // @Test
    // public void testExample() {

    //     Mockito
    //         .when(resource.getBookById(Mockito.anyLong())) 
    //         .thenReturn(new Book(1l, "Orson S. Card", "Ender Game")); 

    //     when()
    //         .get("/1").andReturn()
    //     .then()
    //         .statusCode(200)
    //         .body("id", is(1))
    //         .body("name", is("Ender Game"))
    //         .body("author", is("Orson S. Card"));
    // }
    
}
