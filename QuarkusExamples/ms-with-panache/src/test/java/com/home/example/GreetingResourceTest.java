package com.home.example;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;

//@QuarkusTest
public class GreetingResourceTest {

//    @InjectSpy
//    GreetingService greetingService;
//
//    @Test
//    public void testDefaultGreeting() {
//        given()
//                .when().get("/greeting")
//                .then()
//                .statusCode(200)
//                .body(is("hello"));
//
//        Mockito.verify(greetingService, Mockito.times(1)).greet(); 
//    }
//
//    @Test
//    public void testOverrideGreeting() {
//        doReturn("hi").when(greetingService).greet(); 
//        given()
//                .when().get("/greeting")
//                .then()
//                .statusCode(200)
//                .body(is("hi")); 
//    }

}