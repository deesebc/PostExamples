package com.example.home.ApacheCamelRestExample.router;

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
        restConfiguration().component("servlet").port(9090).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest().get("book/{id}").description("Details of an book by id")
                .produces(MediaType.APPLICATION_JSON_VALUE).route()
                .bean(DBMockRepository.class, "findById(${header.id})");

        rest().get("book").produces(MediaType.APPLICATION_JSON_VALUE).route().bean(DBMockRepository.class,
                "getAll(})");

        rest().post("book").type(Book.class).produces(MediaType.APPLICATION_JSON_VALUE).route()
                .bean(DBMockRepository.class, "create(${body})").log("---create---");

        rest().put("book/{id}").type(Book.class).produces(MediaType.APPLICATION_JSON_VALUE).route()
                .bean(DBMockRepository.class, "update(${header.id}, ${body})");

        rest().delete("book/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .bean(DBMockRepository.class, "remove(${header.id})");

    }
}
