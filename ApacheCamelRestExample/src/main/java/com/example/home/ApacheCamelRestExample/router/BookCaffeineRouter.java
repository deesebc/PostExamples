package com.example.home.ApacheCamelRestExample.router;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.caffeine.CaffeineConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

@Component
public class BookCaffeineRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        rest("/cache").get("book").produces(MediaType.APPLICATION_JSON_VALUE).route()
        .to("sql:{{sql.selectAll}}")
        .log("--- cache select all books---");

        rest().get("cache/book/{id}").description("Details of an book by id").outType(Book.class)
        .produces(MediaType.APPLICATION_JSON_VALUE).route()
        .log("Cache : Select Book By Id: ${header.id}")
        .setHeader(CaffeineConstants.ACTION, constant(CaffeineConstants.ACTION_GET))
        .setHeader(CaffeineConstants.KEY, header("id"))
        .toF("caffeine-cache://%s", "BookCache")
        .log("Has Result ${header.CamelCaffeineActionHasResult} ActionSucceeded ${header.CamelCaffeineActionSucceeded}")
        .choice().when(header(CaffeineConstants.ACTION_HAS_RESULT).isEqualTo(Boolean.FALSE))
        .to("sql:{{sql.selectById}}")
        .setHeader(CaffeineConstants.ACTION, constant(CaffeineConstants.ACTION_PUT))
        .setHeader(CaffeineConstants.KEY, header("id"))
        .toF("caffeine-cache://%s", "BookCache")
        .otherwise().log("Cache is working");

        rest().post("cache/book").produces(MediaType.APPLICATION_JSON_VALUE).route().routeId("postBookRoute")
        .to("sql:{{sql.insert}}")
        .log("POST BODY ${body}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
        .setBody(constant(null));

        rest().delete("cache/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().to("sql:{{sql.delete}}")
        .setHeader(CaffeineConstants.ACTION, constant(CaffeineConstants.ACTION_INVALIDATE))
        .setHeader(CaffeineConstants.KEY, header("id"))
        .toF("caffeine-cache://%s", "BookCache")
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant(null));

        rest().put("cache/book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().choice().when()
        .simple("${header.id} < 1").bean(BookSQLRouter.class, "negativeId").otherwise()
        .to("sql:{{sql.update}}")
        .log("PUT BODY ${body}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .setBody(constant(null));
    }

    public void negativeId(final Exchange exchange) {
        exchange.getIn().setBody("{\"msg\":\"id can not be negative\"}");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }
}
