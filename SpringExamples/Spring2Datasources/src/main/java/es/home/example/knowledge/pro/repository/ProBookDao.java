package es.home.example.knowledge.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import es.home.example.knowledge.entity.Book;

@Transactional(value = "proTransactionManager")
public interface ProBookDao extends JpaRepository<Book, Integer> {
}