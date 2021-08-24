package com.home.example.springwebflux.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.home.example.springwebflux.entity.Book;

public interface BookDao extends GenericDao<Book, Integer> {

    @Query(value = "SELECT b.* FROM book b INNER JOIN (SELECT author, MAX(release_date) AS MaxRelease FROM book GROUP BY author) groupedbook ON b.author = groupedbook.author AND b.release_date = groupedbook.MaxRelease", nativeQuery = true)
    List<Book> findLastReleasesByAuthor();
}
