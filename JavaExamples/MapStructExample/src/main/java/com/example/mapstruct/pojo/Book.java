package com.example.mapstruct.pojo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Book {

	private Integer id;
	private LocalDate publicationDate;
	private String author;
	private String name;
	private String isbn;
	private Saga saga;

}
