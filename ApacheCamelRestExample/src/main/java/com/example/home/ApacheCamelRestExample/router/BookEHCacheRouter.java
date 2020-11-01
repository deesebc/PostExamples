package com.example.home.ApacheCamelRestExample.router;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.caffeine.CaffeineConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

@Component
public class BookEHCacheRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        // cache configuration
        //        from("caffeine-cache://BookCache?createCacheIfNotExist=true").routeId("book-cache").end();

        //        rest("/cache/book").get().route().log("1").end();
        //        rest().get("/cache/book").route().log("2").end();
        //        rest("/cache").get("/book").route().log("3").end();
        //        rest("/cache").get("book").route().log("4").end();
        //        rest("cache").get("book").route().log("5").end();

        rest("/cache").get("book").produces(MediaType.APPLICATION_JSON_VALUE).route()
        .to("sql:{{sql.selectAll}}")
        .log("--- cache select all books---");

        rest().get("cache/book/{id}").description("Details of an book by id").outType(Book.class)
        .produces(MediaType.APPLICATION_JSON_VALUE).route()
        .log("--- cache select a book ${body}. Header Id: ${header.id} ---")
        .setProperty("bookId", simple("${header.id}"))
        .log("--- set Property ---")
        // Prepare headers
        //        .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_GET))
        //        .setHeader(EhcacheConstants.KEY, exchangeProperty("bookId"))
        .to("caffeine-cache://BookCache?action=GET&key=id")
        .log("--- get cache ---")
        .log("--- ACTION_HAS_RESULTs --- ${header.CamelCaffeineActionHasResult}")
        .log("--- ACTION_HAS_RESULTs --- "+simpleF("${header.%s}",CaffeineConstants.ACTION_HAS_RESULT))
        .choice().when(header(CaffeineConstants.ACTION_HAS_RESULT).isEqualTo(Boolean.FALSE))
        .log("--- no cache. we obtain the value ---")
        .to("sql:{{sql.selectById}}")
        .to("caffeine-cache://BookCache?action=PUT&key=id")
        .otherwise().log("--- ACTION_HAS_RESULT is not null ---")
        .end();



        //        rest().get("sql/book/{id}").description("Details of an book by id").outType(Book.class)
        //        .produces(MediaType.APPLICATION_JSON_VALUE).route().log("--- 1 select a book ${body} ---")
        //        .to("sql:{{sql.selectById}}").log("--- 2 select a book ${body} ---");

        rest().post("cache/book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("postBookRoute")
        .to("sql:{{sql.insert}}").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
        .setBody(constant(null));

        rest().delete("cache/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().to("sql:{{sql.delete}}")
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));

        // remove .type(Book.class)
        rest().put("cache/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().choice().when()
        .simple("${header.id} < 1").bean(BookSQLRouter.class, "negativeId").otherwise()
        .to("sql:{{sql.update}}").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .setBody(constant(null));
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }
}
