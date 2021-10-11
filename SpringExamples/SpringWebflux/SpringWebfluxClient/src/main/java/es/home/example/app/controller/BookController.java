package es.home.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.home.example.app.service.BookService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BookController {

	@Autowired
	BookService service;

	@RequestMapping("/bookList")
	public String bookList(final Model modelo) {
		Long init = System.currentTimeMillis();
		modelo.addAttribute("books", service.findAll());
		Long end = System.currentTimeMillis();
		log.info("bookList : " + (end - init) + " miliseconds");
		return "index";
	}

	@RequestMapping("/bookListFlux")
	public String bookListFlux(final Model modelo) {
		Long init = System.currentTimeMillis();
		modelo.addAttribute("books", service.findAllFlux().collectList().block());
		Long end = System.currentTimeMillis();
		log.info("bookListFlux : " + (end - init) + " miliseconds");
		return "index";
	}
}
