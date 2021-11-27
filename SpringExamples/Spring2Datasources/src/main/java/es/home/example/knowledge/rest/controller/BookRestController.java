package es.home.example.knowledge.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.knowledge.dev.repository.DevBookDao;
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

	@Autowired
	private DevBookDao devRepository;

	@GetMapping
	public List<Book> findAll() {
		List<Book> pre = findAllPre();
		pre.addAll(findAllPro());
		pre.addAll(findAllDev());
		return pre;
	}

	@GetMapping("/dev")
	public List<Book> findAllDev() {
		return devRepository.findAll();
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
