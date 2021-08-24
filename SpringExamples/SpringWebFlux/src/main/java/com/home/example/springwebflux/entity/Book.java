package com.home.example.springwebflux.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BOOK")
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book extends GenericEntity<Integer> {

    private static final long serialVersionUID = 1L;
    @Setter
    private String name;
    @Setter
    private String author;
    @Setter
    private Date releaseDate;

    public Book(final Integer id, final String name) {
	this.id = id;
	this.name = name;
    }

    @Column(name = "AUTHOR", length = 255)
    public String getAuthor() {
	return author;
    }

    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
	return id;
    }

    @Column(name = "NAME", length = 255)
    public String getName() {
	return name;
    }

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getReleaseDate() {
	return releaseDate;
    }

    @Override
    public void setId(final Integer id) {
	this.id = id;
    }

}
