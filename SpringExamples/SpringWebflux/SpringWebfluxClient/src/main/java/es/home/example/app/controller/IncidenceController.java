package es.home.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.home.example.app.service.IncidenceService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IncidenceController {

	@Autowired
	IncidenceService service;

	@RequestMapping("/incidenceList")
	public String incidenceList(final Model modelo) {
		Long init = System.currentTimeMillis();
		modelo.addAttribute("incidences", service.findAll());
		Long end = System.currentTimeMillis();
		log.info("incidence List : " + (end - init) + " miliseconds");
		return "index";
	}

	@RequestMapping("/incidenceListFlux")
	public String incidenceListFlux(final Model modelo) {
		Long init = System.currentTimeMillis();
		modelo.addAttribute("incidences", service.findAllFlux().collectList().block());
		Long end = System.currentTimeMillis();
		log.info("incidence ListFlux : " + (end - init) + " miliseconds");
		return "index";
	}
}
