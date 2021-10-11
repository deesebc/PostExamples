package es.home.example.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import es.home.example.app.pojo.Book;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class BookService {

	public List<Book> findAll() {
		log.info("findAll - init");
		RestTemplate restTemplate = new RestTemplate();
		log.info("findAll - call 1");
		List<Book> lista = new ArrayList<>(
				Arrays.asList(restTemplate.getForObject("http://localhost:8085/book", Book[].class)));
		log.info("findAll - call 2");
		lista.addAll(Arrays.asList(restTemplate.getForObject("http://localhost:8085/book", Book[].class)));
		return lista;
	}

	/**
	 * El framework nos permitirá construir un servicio REST no bloqueante de tal forma que podamos hacer varias
	 * peticiones simultaneas y las procese en paralelo.
	 */

	public Flux<Book> findAllFlux() {
		log.info("findAllFlux - init");
		WebClient cliente = WebClient.create("http://localhost:8085/bookFlux");
		log.info("findAllFlux - call 1");
		Flux<Book> Books = cliente.get().retrieve().bodyToFlux(Book.class);
		log.info("findAllFlux - call 2");
		Flux<Book> Books2 = cliente.get().retrieve().bodyToFlux(Book.class);
		return Flux.merge(Books, Books2);
	}
}
