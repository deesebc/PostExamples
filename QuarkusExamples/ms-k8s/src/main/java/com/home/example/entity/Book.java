package com.home.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOK")
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book {
	// If we dont want to extends from PanacheEntity, we must create getters/setters
	// for Hibernate working correctly
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String author;
	String name;

	public Book() {
		super();
	}

	public Book(final Long id, final String author, final String name) {
		this.id = id;
		this.author = author;
		this.name = name;
	}

	public Book(final String author, final String name) {
		this.author = author;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

}
