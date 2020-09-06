package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

@Component
public class BookRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .to("jpa://com.example.home.ApacheCamelRestExample.pojo.Book?resultClass="
                        + Book.class.getName() + "&namedQuery=findAll")
                .log("---select all books---");

        rest().get("book/{id}").description("Details of an book by id").outType(Book.class)
                .produces(MediaType.APPLICATION_JSON_VALUE).route().log("--- 1 select a book ${body} ---")
                .toD("jpa://" + Book.class.getName() + "?query=select b from " + Book.class.getName()
                        + " b where b.id= ${header.id}", 5)
                .log("--- 2 select a book ${body} ---");

        rest().post("book").produces(MediaType.APPLICATION_JSON_VALUE).type(Book.class).route()
                .routeId("postBookRoute").log("--- binded ${body} ---")
                .to("jpa:" + Book.class.getName() + "?usePersist=true")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201)).setBody(constant(null));

        rest().delete("book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().toD("jpa:"
                + Book.class.getName()
                + "?nativeQuery=DELETE FROM library.BOOK where id = ${header.id}&useExecuteUpdate=true")
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));

        rest().put("book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).type(Book.class).route().choice()
                .when().simple("${header.id} < 1").bean(BookRouter.class, "negativeId").otherwise()
                .process(new Processor() {
                    @Override
                    public void process(final Exchange exchange) {
                        Book book = exchange.getIn().getBody(Book.class);
                        Integer id = Integer.valueOf(exchange.getIn().getHeader("id").toString());
                        book.setId(id);
                        exchange.getIn().setBody(book, Book.class);
                    }
                }).to("jpa:" + Book.class.getName() + "?useExecuteUpdate=true")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

}
