package es.home.example.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import es.home.example.app.pojo.Incidence;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class IncidenceService {

	public List<Incidence> findAll() {
		log.info("findAll - init");
		List<Incidence> retorno = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		List<Incidence> lstPre1 = new ArrayList<>(
				Arrays.asList(restTemplate.getForObject("http://localhost:8085/incidence/pre/1", Incidence[].class)));
		retorno.addAll(lstPre1);
		List<Incidence> lstPre2 = new ArrayList<>(
				Arrays.asList(restTemplate.getForObject("http://localhost:8085/incidence/pre/2", Incidence[].class)));
		retorno.addAll(lstPre2);
		List<Incidence> lstPro1 = new ArrayList<>(
				Arrays.asList(restTemplate.getForObject("http://localhost:8085/incidence/pro/1", Incidence[].class)));
		retorno.addAll(lstPro1);
		List<Incidence> lstPro2 = new ArrayList<>(
				Arrays.asList(restTemplate.getForObject("http://localhost:8085/incidence/pro/2", Incidence[].class)));
		retorno.addAll(lstPro2);
		return retorno;
	}

	// We can call to another non blocking rest service. So we can make parallel calls and process them
	public Flux<Incidence> findAllFlux() {
		log.info("findAllFlux - init");
		Flux<Incidence> lstPre1 = WebClient.create("http://localhost:8085/incidenceFlux/pre/1").get().retrieve()
				.bodyToFlux(Incidence.class);
		Flux<Incidence> lstPre2 = WebClient.create("http://localhost:8085/incidenceFlux/pre/2").get().retrieve()
				.bodyToFlux(Incidence.class);
		Flux<Incidence> lstPro1 = WebClient.create("http://localhost:8085/incidenceFlux/pro/1").get().retrieve()
				.bodyToFlux(Incidence.class);
		Flux<Incidence> lstPro2 = WebClient.create("http://localhost:8085/incidenceFlux/pro/2").get().retrieve()
				.bodyToFlux(Incidence.class);
		return Flux.merge(lstPre1, lstPre2, lstPro1, lstPro2);
	}
}
