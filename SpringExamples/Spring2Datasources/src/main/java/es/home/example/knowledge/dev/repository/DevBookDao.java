package es.home.example.knowledge.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.home.example.knowledge.entity.Book;

public interface DevBookDao extends JpaRepository<Book, Integer> {
}