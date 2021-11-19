package es.home.example.knowledge.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity(name = "BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String author;

	private Integer id;

	public Book() {
	}

	public Book(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}

	@Column(name = "AUTHOR", length = 255)
	public String getAuthor() {
		return author;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "NAME", length = 255)
	public String getName() {
		return name;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}