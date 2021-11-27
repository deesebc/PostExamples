package es.home.example.knowledge.pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.home.example.knowledge.entity.Book;

public interface PreBookDao extends JpaRepository<Book, Integer> {
}