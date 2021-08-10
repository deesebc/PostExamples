package com.example.home.ApacheCamelConsul.router;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelConsul.pojo.Book;

@Component
public class BookServiceRouter extends RouteBuilder {

    @Autowired
    CamelContext context;

    @Override
    public void configure() throws Exception {

	rest("/serviceCall").get().produces(MediaType.APPLICATION_JSON_VALUE).route().removeHeader(Exchange.HTTP_URI)
		.serviceCall("mockGetAll").log("Body: ${body}").endRest();

	rest("/serviceCall/{id}").get().produces(MediaType.APPLICATION_JSON_VALUE).outType(Book.class).route()
		.removeHeader(Exchange.HTTP_PATH).setHeader(Exchange.HTTP_PATH, simple("${header.id}"))
		.removeHeader(Exchange.HTTP_URI).serviceCall("mockGetById").log("Body: ${body}").marshal().json()
		.endRest();

    }

}
