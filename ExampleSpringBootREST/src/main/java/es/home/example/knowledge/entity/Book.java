package es.home.example.knowledge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
public class Book extends GenericEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private String title;
    private String author;

    public Book() {
    }

    public Book(final Integer id, final String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    @Column(name = "TITLE", length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Column(name = "AUTHOR", length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

}