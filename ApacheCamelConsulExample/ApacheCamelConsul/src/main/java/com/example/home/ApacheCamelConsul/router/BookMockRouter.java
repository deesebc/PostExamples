package com.example.home.ApacheCamelConsul.router;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelConsul.pojo.Book;

@Component
public class BookMockRouter extends RouteBuilder {

    @Autowired
    CamelContext context;

    @Override
    public void configure() throws Exception {

	rest("/serviceCall").get().produces(MediaType.APPLICATION_JSON_VALUE).outType(Book.class).route()
		.removeHeader(Exchange.HTTP_URI).serviceCall("mockClient").log("Body: ${body}").endRest();

    }

}
