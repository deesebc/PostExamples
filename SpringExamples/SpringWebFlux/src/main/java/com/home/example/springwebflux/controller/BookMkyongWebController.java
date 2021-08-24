package com.home.example.springwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.home.example.springwebflux.dao.BookDao;

@Controller
public class BookMkyongWebController {
    // https://mkyong.com/spring-boot/spring-boot-webflux-thymeleaf-reactive-example/
    @Autowired
    private BookDao repository;

    @RequestMapping("/mkyong")
    public String index(final Model model) {

	// loads 1 and display 1, stream data, data driven mode.
	IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(repository.findAll(), 1);

	model.addAttribute("books", reactiveDataDrivenMode);

	// classic, wait repository loaded all and display it.
	// model.addAttribute("movies", movieRepository.findAll());

	return "index";

    }
}