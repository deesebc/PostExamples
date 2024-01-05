package com.home.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOVIE")
@NamedQuery(name = "Movie.findAll", query = "SELECT b FROM Movie b")
public class Movie {
    //If we dont want to extends from PanacheEntity, we must create getters/setters for Hibernate working correctly
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    Long id;
    @JsonProperty
    String director;
    @JsonProperty
    String name;

    public Movie(){
        super();
    }

    public Movie(final Long id, final String director, final String name){
        this.id = id;
        this.director=director;
        this.name=name;
    }

    public Movie(final String director, final String name){
        this.director=director;
        this.name=name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
