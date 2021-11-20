package es.home.example.knowledge.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import es.home.example.knowledge.entity.Book;

@Transactional(value = "devTransactionManager")
public interface DevBookDao extends JpaRepository<Book, Integer> {
}