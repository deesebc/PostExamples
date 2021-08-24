package com.home.example.springwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.example.springwebflux.dao.BookDao;
import com.home.example.springwebflux.entity.Book;

import reactor.core.publisher.Flux;

@RestController
public class BookRestController {
    @Autowired
    private BookDao repository;

    @GetMapping(path = "/books/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> feed() {
	System.out.println("----- feed -----");
//	return Flux.defer(() -> Flux.fromIterable(repository.findLastReleasesByAuthor())).delayElements(Duration.ofSeconds(1));
	return Flux.fromIterable(repository.findLastReleasesByAuthor());
    }

    // version with jpa repository
//    @GetMapping
//    public List<Book> findAll() {
//	return repository.findAll();
//    }
//
//    @GetMapping(path = "/{id}", produces = "application/json")
//    public Mono<Book> findById(@NonNull @PathVariable("id") final Integer id) {
//	return Mono.justOrEmpty(repository.findById(id));
//    }
}