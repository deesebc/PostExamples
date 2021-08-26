package com.home.example.springwebflux.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BookListController {

    // https://windoctor7.github.io/Programacion-Reactiva-Spring5.html

    // https://github.com/chang-chao/spring-webflux-reactive-jdbc-sample

//    @Autowired
//    private BookDao repository;
//
//    @Autowired
//    @Qualifier("jdbcScheduler")
//    private Scheduler jdbcScheduler;
//
//    @GetMapping("/maxWithDelay")
//    public String maxWithDelay(final Model model) {
//	Flux<Book> flux = Flux.defer(() -> Flux.fromIterable(repository.findLastReleasesByAuthor())).delayElements(Duration.ofSeconds(1));
//	model.addAttribute("books", new ReactiveDataDriverContextVariable(flux, 1));
//	return "books"; // direccionamos al students.html
//    }
//
//    @GetMapping("/withDelay")
//    public String withDelay(final Model model) {
//	Flux<Book> flux = Flux.defer(() -> Flux.fromIterable(repository.findAll())).delayElements(Duration.ofSeconds(1));
//	model.addAttribute("books", new ReactiveDataDriverContextVariable(flux, 1));
//	return "books"; // direccionamos al students.html
//    }
//
//    @GetMapping("/withoutDealy")
//    public String withoutDelay(final Model model) {
//	Flux<Book> flux = Flux.defer(() -> Flux.fromIterable(repository.findLastReleasesByAuthor()));
//	model.addAttribute("books", flux.subscribeOn(jdbcScheduler));
//	return "books"; // direccionamos al students.html
//    }

}
