package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;
import com.example.home.ApacheCamelRestExample.repository.DBMockRepository;

@Component
public class BookRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().bean(DBMockRepository.class,
                "getAll(})");

        rest().get("book/{id}").description("Details of an book by id").outType(Book.class)
                .produces(MediaType.APPLICATION_XML_VALUE).route()
                .bean(DBMockRepository.class, "findById(${header.id})");

        rest().post("book").type(Book.class).produces(MediaType.APPLICATION_JSON_VALUE).route()
                .routeId("postBookRoute").bean(DBMockRepository.class, "create(${body})")
                .log("---creating a book---");

        rest().delete("book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .bean(DBMockRepository.class, "remove(${header.id})");

        rest().put("book/{id}").type(Book.class).produces(MediaType.APPLICATION_JSON_VALUE).route().choice()
                .when().simple("${header.id} < 1").bean(BookRouter.class, "negativeId").otherwise()
                .bean(DBMockRepository.class, "update(${header.id}, ${body})");
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

}
