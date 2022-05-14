package com.example.mapstruct.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookDto {

	private Integer id;
	private Date publicationDate;
	private String author;
	private String name;
	private SagaDto serie;
}
