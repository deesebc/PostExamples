package com.home.example.springwebflux.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.home.example.springwebflux.entity.Book;

import reactor.core.publisher.Flux;

public interface BookReactiveDao extends ReactiveCrudRepository<Book, Integer> {

    @Query(value = "SELECT b.* FROM book b INNER JOIN (SELECT author, MAX(release_date) AS MaxRelease FROM book GROUP BY author) groupedbook ON b.author = groupedbook.author AND b.release_date = groupedbook.MaxRelease")
    Flux<Book> findLastReleasesByAuthor();

}
