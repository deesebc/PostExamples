package es.home.example.knowledge.pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import es.home.example.knowledge.entity.Book;

@Transactional(value = "preTransactionManager")
public interface PreBookDao extends JpaRepository<Book, Integer> {
}