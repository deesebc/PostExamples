package com.example.home.ApacheCamelQuarkus.router;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class BookRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("netty-http").port(8099).bindingMode(RestBindingMode.json);

        //OK
        //        from("netty-http:0.0.0.0:8099/book")
        //        .to("sql:SELECT ID, NAME, AUTHOR FROM BOOK").marshal().json().log("--- select all books. Body: ${body} ---");

        rest("/book").get().route().to("sql:SELECT ID, NAME, AUTHOR FROM BOOK").log("--- select all books. Body: ${body} ---");

        rest("/book/{id}").get().route().to("sql:SELECT ID, NAME, AUTHOR FROM BOOK WHERE ID = :#id").log("--- 2 select a book ${body} ---");



        rest().post("book").route().routeId("postBookRoute")
        .to("sql:INSERT INTO BOOK (NAME, AUTHOR) VALUES (:#name, :#author)").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
        .setBody(constant(null));

        rest().delete("book/{id}").route().to("sql:DELETE FROM BOOK WHERE ID = :#id")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));

        //need camel-bean
        rest().put("book/{id}").route().choice().when()
        .simple("${header.id} < 1").bean(BookRouter.class, "negativeId").otherwise()
        .to("sql:UPDATE BOOK SET NAME = :#name, AUTHOR = :#author WHERE ID = :#id").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .setBody(constant(null));
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

}
