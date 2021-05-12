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

	rest().get("hystrix/book").produces(MediaType.APPLICATION_JSON_VALUE).route().circuitBreaker()
		.to("sql:{{sql.selectAll}}").onFallback().setBody(bodyAs(ArrayList.class)).endCircuitBreaker()
		.log("--- SQL select all books---");

    }

}
