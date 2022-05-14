package es.home.example.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.app.pojo.Msg;

@RestController
public class DummyController {
	@GetMapping("/hello")
	public ResponseEntity<Msg> msg() {
		return new ResponseEntity<>(new Msg("World"), HttpStatus.OK);
	}
}
