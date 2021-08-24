package com.home.example.springwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.home.example.springwebflux.dao.BookDao;
import com.home.example.springwebflux.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

@Controller
public class BookListController {

    // https://windoctor7.github.io/Programacion-Reactiva-Spring5.html

    // https://github.com/chang-chao/spring-webflux-reactive-jdbc-sample

    @Autowired
    private BookDao repository;

    @Autowired
    @Qualifier("jdbcScheduler")
    private Scheduler jdbcScheduler;

    @GetMapping("/list-students")
    public String listStudents(final Model model) {
	Flux<Book> flux = Flux.defer(() -> Flux.fromIterable(repository.findAll()));
	model.addAttribute("books", flux.subscribeOn(jdbcScheduler));
	return "books"; // direccionamos al students.html
    }

}
