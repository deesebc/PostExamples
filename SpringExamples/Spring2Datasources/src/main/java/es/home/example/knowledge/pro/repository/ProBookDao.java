package es.home.example.knowledge.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.home.example.knowledge.entity.Book;

public interface ProBookDao extends JpaRepository<Book, Integer> {
}