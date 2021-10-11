package es.home.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.app.pojo.Book;
import jdk.internal.org.jline.utils.Log;

@RestController
public class BookRestController {

	@GetMapping("/book")
	public List<Book> findAll() {
		List<Book> lista = new ArrayList<>();
		lista.add(new Book(1, "Ende's game", "Orson S. Card"));
		lista.add(new Book(2, "Dune", "Frank Herbert"));
		lista.add(new Book(3, "the starts my destination", "Alfred Bester"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Log.warn("Error while waiting");
		}
		return lista;
	}

}
