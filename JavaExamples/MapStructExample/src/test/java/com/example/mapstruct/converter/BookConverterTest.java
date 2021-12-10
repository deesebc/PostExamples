package com.example.mapstruct.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.example.mapstruct.dto.BookDto;
import com.example.mapstruct.dto.BookResumeDto;
import com.example.mapstruct.dto.SagaDto;
import com.example.mapstruct.pojo.Book;
import com.example.mapstruct.pojo.Saga;

public class BookConverterTest {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void convertBook() {
		String date = "16/08/2016";

		Saga sourceS = new Saga();
		sourceS.setId(1);
		sourceS.setName("NameS");
		sourceS.setNumberNovels(2L);

		Book sourceB = new Book();
		sourceB.setAuthor("Author");
		sourceB.setId(0);
		sourceB.setIsbn("123-45-789");
		sourceB.setName("Name");
		sourceB.setPublicationDate(LocalDate.parse(date, formatter));
		sourceB.setSaga(sourceS);

		BookDto destination = BookConverter.MAPPER.convertBook(sourceB);

		assertThat(destination.getAuthor(), equalTo("Author"));
		assertThat(destination.getId(), equalTo(0));
		assertThat(destination.getName(), equalTo("Name"));
		assertThat(dateFormat.format(destination.getPublicationDate()), equalTo(date));

		assertThat(destination.getSerie().getName(), equalTo("NameS"));
		assertThat(destination.getSerie().getNumberNovels(), equalTo(2));
	}

	@Test
	public void convertBookDto() {
		String date = "16/08/2016";

		SagaDto sourceS = new SagaDto();
		sourceS.setName("NameS");
		sourceS.setNumberNovels(2);

		BookDto sourceB = new BookDto();
		sourceB.setAuthor("Author");
		sourceB.setId(0);
		sourceB.setName("Name");
//		sourceB.setPublicationDate(LocalDate.parse(date, formatter));
		sourceB.setSerie(sourceS);

		Book destination = BookConverter.MAPPER.convertBookDto(sourceB);

		assertThat(destination.getAuthor(), equalTo("Author"));
		assertThat(destination.getId(), equalTo(0));
		assertThat(destination.getName(), equalTo("Name"));

		assertThat(destination.getSaga().getName(), equalTo("NameS"));
		assertThat(destination.getSaga().getNumberNovels(), equalTo(2L));
	}

	@Test
	public void convertBookResume() {

		Saga sourceS = new Saga();
		sourceS.setName("NameS");

		Book sourceB = new Book();
		sourceB.setAuthor("Author");
		sourceB.setName("Name");

		BookResumeDto destination = BookConverter.MAPPER.convertResume(sourceB, sourceS);

		assertThat(destination.getBookName(), equalTo("Name"));
		assertThat(destination.getSagaName(), equalTo("NameS"));
	}

	@Test
	public void convertSaga() {
		Saga sourceS = new Saga();
		sourceS.setId(1);
		sourceS.setName("NameS");
		sourceS.setNumberNovels(2L);

		SagaDto destination = BookConverter.MAPPER.convertSaga(sourceS);

		assertThat(destination.getName(), equalTo("NameS"));
		assertThat(destination.getNumberNovels(), equalTo(2));
	}
}
