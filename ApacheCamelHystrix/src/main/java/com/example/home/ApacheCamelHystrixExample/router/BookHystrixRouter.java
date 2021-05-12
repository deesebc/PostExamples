package com.example.home.ApacheCamelHystrixExample.router;

import java.util.ArrayList;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookHystrixRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
	restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

	rest().get("hystrix/sql").produces(MediaType.APPLICATION_JSON_VALUE).route().circuitBreaker().id("hystrix/sql")
		.to("sql:{{sql.selectAll}}").onFallback().setBody(bodyAs(ArrayList.class)).endCircuitBreaker()
		.log("--- SQL select all books---");

	rest().get("hystrix/http").produces(MediaType.APPLICATION_JSON_VALUE).route().circuitBreaker()
		.id("hystrix/http").toD("http://localhost:57001/book?bridgeEndpoint=true").onFallback()
		.setBody(constant("[]")).end().unmarshal().json().log("--- Wiremock select all books---");

	rest().get("hystrix/fail").produces(MediaType.APPLICATION_JSON_VALUE).route().circuitBreaker()
		.id("hystrix/fail").toD("http://localhost:57001/fail?bridgeEndpoint=true").onFallbackViaNetwork()
		.toD("http://localhost:57001/book?bridgeEndpoint=true").end().unmarshal().json()
		.log("--- Wiremock select all books---");
    }
}
