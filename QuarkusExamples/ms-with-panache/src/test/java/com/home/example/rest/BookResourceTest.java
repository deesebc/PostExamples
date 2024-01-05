package com.home.example.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.home.example.entity.Book;

import io.quarkus.logging.Log;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class BookResourceTest {

    // @InjectSpy
    // BookRepository repository;

    @Test
    public void getById() {
        when()
            .get("/1").andReturn()
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("name", is("Ender Game"))
            .body("author", is("Orson S. Card"));
    }

    @Test
    public void getById2() {
        Response response = when().get("/1").thenReturn();
        Log.info(response.asPrettyString());
        assertEquals(200, response.getStatusCode(), "Status code is not 200");
        assertEquals("Ender Game", response.getBody().as(Book.class).getName(), "Name is not Ender Game");

        // Mockito.verify(repository, Mockito.times(1)).findById(1l);
    }

    @Test
    public void getAll() {
        Response response = when().get("/").thenReturn();
        Log.info(response.asPrettyString());

        when()
            .get("/")
        .then()
            .statusCode(200)
            .body( "size()", is( 3 ) )
            .body("[0].name", is("Ender Game"))
            .body("[0].author", is("Orson S. Card"));

    }

    @Test
    public void createAndDelete() {
        ValidatableResponse vResponse = when().get("/").then();
        int size = vResponse.extract().jsonPath().getList("$").size();
        Log.info("Size: "+size);

        Response response = given()
        .body( new Book("Robert Holdstock", "Bosque Mitago") )
        .contentType( ContentType.JSON )
        .post()
        .thenReturn();

        Book book = response.as(Book.class);
        long id = book.getId();
        Log.info("id: "+id);

        when()
            .get("/")
        .then()
            .statusCode(200)
            .body( "size()", is( size+1 ) );

        given()
            .delete("/"+id);

        when()
            .get("/")
        .then()
            .statusCode( 200 )
            .body( "size()", is( size ) );
    }
}
