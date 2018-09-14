package es.home.example.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends GenericEntity<Integer> {

	private static final long serialVersionUID = 1L;
	private String author;
	private String title;

	public Book() {
		super();
	}

	public Book(final Integer id, final String author, final String title) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
	}

	@Column(name = "AUTHOR", length = 255)
	public String getAuthor() {
		return author;
	}

	@Column(name = "TITLE", length = 255)
	public String getTitle() {
		return title;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
