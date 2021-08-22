package es.home.example.testing.pojo;

public class Book {

	private String name;
	private String author;

	public Book(final String name, final String author) {
		super();
		this.name = name;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
