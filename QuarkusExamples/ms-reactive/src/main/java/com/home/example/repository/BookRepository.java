package com.home.example.repository;

import com.home.example.entity.Book;

//Warning: We modify the packagae of PanacheRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

//warning: class not interface
@ApplicationScoped
@Transactional
//Warning we have to add to the methods work propertly
@WithSessionOnDemand
public class BookRepository implements PanacheRepository<Book>{
    
}
