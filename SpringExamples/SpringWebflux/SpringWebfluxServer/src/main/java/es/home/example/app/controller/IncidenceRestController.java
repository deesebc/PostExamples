package es.home.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.app.pojo.Incidence;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class IncidenceRestController {

	@GetMapping("/incidence/{env}/{server}")
	public List<Incidence> findAll(@PathVariable final String env, @PathVariable final String server) {

		List<Incidence> lst = new ArrayList<>();
		lst.add(new Incidence(env, server, "info", "method called"));
		lst.add(new Incidence(env, server, "warning", "incorrect data"));
		lst.add(new Incidence(env, server, "info", "method ended"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.warn("Error while waiting");
		}
		return lst;
	}

}
