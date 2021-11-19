package es.home.example.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import es.home.example.knowledge.entity.Book;

@Transactional(value = "preTransactionManager")
public interface BookDao extends JpaRepository<Book, Integer> {
}