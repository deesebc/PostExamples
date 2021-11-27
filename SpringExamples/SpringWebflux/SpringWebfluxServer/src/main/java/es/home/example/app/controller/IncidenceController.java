package es.home.example.app.controller;

import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.home.example.app.pojo.Incidence;
import reactor.core.publisher.Flux;

@Controller
public class IncidenceController {

	@GetMapping("/incidenceFlux/{env}/{server}")
	public Flux<Incidence> findAllFlux(@PathVariable final String env, @PathVariable final String server) {
		return Flux.just(new Incidence(env, server, "info", "method called"),
				new Incidence(env, server, "warning", "incorrect data"),
				new Incidence(env, server, "info", "method ended")).delaySequence(Duration.ofSeconds(3));
	}
}
