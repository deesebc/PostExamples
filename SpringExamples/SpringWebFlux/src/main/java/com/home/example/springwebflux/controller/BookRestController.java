package com.home.example.springwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.example.springwebflux.dao.BookDao;

@RestController
@RequestMapping(path = "/book")
public class BookRestController {
    @Autowired
    private BookDao repository;

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