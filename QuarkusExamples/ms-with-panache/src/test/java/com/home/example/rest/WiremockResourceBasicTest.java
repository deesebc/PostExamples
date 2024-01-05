package com.home.example.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.home.example.entity.Movie;
import com.home.example.test.WiremockTestResource;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(WiremockResource.class)
@QuarkusTestResource(WiremockTestResource.class)
public class WiremockResourceBasicTest {

	@Test
	public void getById() {
		Log.info("WiremockResourceBasicTest - getById");
		ObjectMapper mapper = new ObjectMapper();
		Movie movie = new Movie(1L, "Denis Villeneuve", "Dune");
		Log.info("WM port: " + WiremockTestResource.wireMockServer.getOptions().portNumber());
		WiremockTestResource.wireMockServer.stubFor(
				WireMock.get("/imdb/film/1").willReturn(WireMock.aResponse().withJsonBody(mapper.valueToTree(movie))
						.withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));

		WiremockTestResource.wireMockServer.getStubMappings()
				.forEach(s -> Log.info("name: " + s.getRequest().toString()));
		when().get("/1").andReturn().then().statusCode(200).body("id", is(1)).body("name", is("Dune")).body("director",
				is("Denis Villeneuve"));
	}
}
