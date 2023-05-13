package com.home.example.repository;

import com.home.example.entity.Book;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

//warning: class not interface
@ApplicationScoped
public class BookRepository implements PanacheRepository<Book>{
    
}
