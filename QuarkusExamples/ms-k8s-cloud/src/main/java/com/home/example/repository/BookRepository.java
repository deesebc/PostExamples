package com.home.example.repository;

import com.home.example.entity.Book;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

//warning: class not interface
@ApplicationScoped
@Transactional
public class BookRepository implements PanacheRepository<Book> {

}
