package es.home.example.knowledge.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.knowledge.entity.Book;
import es.home.example.knowledge.pre.repository.PreBookDao;
import es.home.example.knowledge.pro.repository.ProBookDao;
import lombok.NonNull;

@RestController
@RequestMapping(path = "/book")
public class BookRestController {
	@Autowired
	private PreBookDao preRepository;

	@Autowired
	private ProBookDao proRepository;

	@GetMapping
	public List<Book> findAll() {
		List<Book> pre = preRepository.findAll();
		List<Book> pro = proRepository.findAll();
		pre.addAll(pro);
		return pre;
	}

	@GetMapping("/pre")
	public List<Book> findAllPre() {
		return preRepository.findAll();
	}

	@GetMapping("/pro")
	public List<Book> findAllPro() {
		return proRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Optional<Book> findById(@NonNull @PathVariable("id") final Integer id) {
		return preRepository.findById(id);
	}
}
