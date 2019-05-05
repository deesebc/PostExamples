package es.home.example.knowledge.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.knowledge.pojo.Saludo;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class SaludoRestController {
    @GetMapping("/saludo")
    public Saludo Saludo(@RequestParam(value = "nombre", defaultValue = "World") final String nombre) {
        log.info("Saludo - init");
        return new Saludo("Hola", nombre);
    }
}
