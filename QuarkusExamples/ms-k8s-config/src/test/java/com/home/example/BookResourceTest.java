package com.home.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.home.example.entity.Book;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@QuarkusTest
@Slf4j
public class BookResourceTest {

	@Test
	void getBook() {
		List<Book> books = given().when().get("/book").then().statusCode(200).extract().as(new TypeRef<>() {
		});
		log.info("Books size: " + books.size());
		assertFalse(books.isEmpty());
	}

	@Test
	void shouldCreateCustomerSuccessfully() {
		Book book = new Book(null, "author", "name");
		given().contentType(ContentType.JSON).body(book).when().post("/book").then().statusCode(201)
				.body("name", is("name")).body("author", is("author"));
	}
}
