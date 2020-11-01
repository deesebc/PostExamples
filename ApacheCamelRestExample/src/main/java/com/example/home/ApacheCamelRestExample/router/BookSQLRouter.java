package com.example.home.ApacheCamelRestExample.router;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

@Component
public class BookSQLRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        rest().get("sql/book").produces(MediaType.APPLICATION_JSON_VALUE).route().to("sql:{{sql.selectAll}}")
        .log("--- SQL select all books---");

        rest().get("sql/book/{id}").description("Details of an book by id").outType(Book.class)
        .produces(MediaType.APPLICATION_JSON_VALUE).route().log("--- 1 select a book ${body} ---")
        .to("sql:{{sql.selectById}}").log("--- 2 select a book ${body} ---");

        rest().post("sql/book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("postBookRoute")
        .to("sql:{{sql.insert}}").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
        .setBody(constant(null));

        rest().delete("sql/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().to("sql:{{sql.delete}}")
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));

        // remove .type(Book.class)
        rest().put("sql/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().choice().when()
        .simple("${header.id} < 1").bean(BookEHCacheRouter.class, "negativeId").otherwise()
        .to("sql:{{sql.update}}").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .setBody(constant(null));
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

}
