package com.home.example.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpHeaders;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.home.example.entity.Movie;
import com.home.example.test.WiremockResourceConfigurable;
import com.home.example.test.WiremockTestAnnotation;

import io.quarkus.logging.Log;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(WiremockResource.class)
@WiremockTestAnnotation(port = "57005")
public class WiremockResourceTest {

	@Inject
	@ConfigProperty(name = "quarkus.rest-client.\"com.home.example.service.ExternalService\".url")
	private String serverUrl;

	@Test
	public void getById() {
		Log.info("WiremockResourceTest - getById");
		Log.info("serverUrl: " + serverUrl);
		ObjectMapper mapper = new ObjectMapper();
		Movie movie = new Movie(1L, "Denis Villeneuve", "Dune");
		Log.info("serverPort: " + WiremockResourceConfigurable.server.getOptions().portNumber());
		WiremockResourceConfigurable.server.stubFor(
				WireMock.get("/imdb/film/1").willReturn(WireMock.aResponse().withJsonBody(mapper.valueToTree(movie))
						.withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
		
		when().get("/1").andReturn().then().statusCode(200).body("id", is(1)).body("name", is("Dune")).body("director",
				is("Denis Villeneuve"));
	}
}
