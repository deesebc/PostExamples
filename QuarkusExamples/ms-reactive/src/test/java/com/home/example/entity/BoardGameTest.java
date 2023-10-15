package com.home.example.entity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.home.example.rest.BoardGameResource;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(BoardGameResource.class)
public class BoardGameTest {
    
    @BeforeAll
    public static void setup(){
        //require quarkus-panache-mock
        PanacheMock.mock(BoardGame.class);
    }

    @Test
    public void listAll() {
        Mockito
            .when(BoardGame.listAll()) 
            .thenReturn(Collections.emptyList()); 

        given() 
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body("$.size()", is(0));
    }
}
