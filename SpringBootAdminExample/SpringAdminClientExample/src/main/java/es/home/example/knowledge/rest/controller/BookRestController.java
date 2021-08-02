package es.home.example.knowledge.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.knowledge.entity.Book;
import es.home.example.knowledge.repository.BookDao;
import lombok.NonNull;

@RestController
@RequestMapping(path = "/book")
public class BookRestController {
    @Autowired
    private BookDao repository;

    @GetMapping
    public List<Book> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Optional<Book> findById(@NonNull @PathVariable("id") final Integer id) {
        return repository.findById(id);
    }
}
