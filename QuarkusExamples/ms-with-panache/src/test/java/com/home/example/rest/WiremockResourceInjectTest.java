package com.home.example.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.example.entity.Movie;
import com.home.example.service.ExternalService;
import com.home.example.test.WiremockResourceConfigurable;

import io.quarkus.logging.Log;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
@TestHTTPEndpoint(WiremockResource.class)
public class WiremockResourceInjectTest {

	@InjectMock
	@RestClient
	ExternalService extService;

	@Test
	public void getById() {
		Log.info("WiremockResourceInjectTest - getById");
		Mockito.when(extService.getMovieById(Mockito.anyLong())).thenReturn(new Movie(1L, "Denis Villeneuve", "Dune"));

		when().get("/1").andReturn().then().statusCode(200).body("id", is(1)).body("name", is("Dune")).body("director",
				is("Denis Villeneuve"));
	}
}
