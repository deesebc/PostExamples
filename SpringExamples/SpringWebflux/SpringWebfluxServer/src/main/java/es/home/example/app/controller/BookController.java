package es.home.example.app.controller;

import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.home.example.app.pojo.Book;
import reactor.core.publisher.Flux;

@Controller
public class BookController {

	/*
	 * Este tipo representa una lista asíncrona de objetos. Ademas obligamos a esperar 3 segundos antes de devolver la
	 * lista. Con esto abrimos las capacidades noblocking de Spring.
	 */

	@GetMapping("/bookFlux")
	public Flux<Book> findAllFlux() {
		return Flux.just(new Book(1, "Ende's game", "Orson S. Card"), new Book(2, "Dune", "Frank Herbert"),
				new Book(3, "the starts my destination", "Alfred Bester")).delaySequence(Duration.ofSeconds(3));
	}
}
