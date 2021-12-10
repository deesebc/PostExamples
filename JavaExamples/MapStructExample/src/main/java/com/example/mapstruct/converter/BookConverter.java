package com.example.mapstruct.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.mapstruct.dto.BookDto;
import com.example.mapstruct.dto.BookResumeDto;
import com.example.mapstruct.dto.SagaDto;
import com.example.mapstruct.pojo.Book;
import com.example.mapstruct.pojo.Saga;

@Mapper
public interface BookConverter {
	BookConverter MAPPER = Mappers.getMapper(BookConverter.class);

	@Mapping(source = "saga", target = "serie")
	BookDto convertBook(Book sourceBook);

	@InheritInverseConfiguration
	Book convertBookDto(BookDto sourceBook);

	@Mapping(source = "sourceBook.name", target = "bookName")
	@Mapping(source = "sourceSaga.name", target = "sagaName")
	BookResumeDto convertResume(Book sourceBook, Saga sourceSaga);

	SagaDto convertSaga(Saga sourceSaga);
}
