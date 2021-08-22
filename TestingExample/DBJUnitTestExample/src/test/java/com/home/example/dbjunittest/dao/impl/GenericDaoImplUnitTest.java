package com.home.example.dbjunittest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.home.example.dbjunittest.entity.Book;

@ExtendWith(MockitoExtension.class)
public class GenericDaoImplUnitTest {
    BookDaoImpl dao;

    @Mock
    EntityManager eManager;

    @Mock
    TypedQuery<Book> mockQuery;

    @Mock
    Stream<Book> stream;

    private Book _getBook() {
	Book book = new Book();
	book.setId(1);
	return book;
    }

    private List<Book> _getBooks() {
	List<Book> lst = new ArrayList<Book>();
	lst.add(new Book());
	return lst;
    }

    private Optional<Book> _getOptBook() {
	Book book = new Book();
	book.setId(1);
	return Optional.of(book);
    }

    @Test
    public void findAll() {
	when(dao.em.createNamedQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Book.class))).thenReturn(mockQuery);
	when(mockQuery.getResultList()).thenReturn(_getBooks());

	List<Book> lst = dao.findAll();
	assertEquals(1, lst.size());
	verify(dao.em).createNamedQuery("Book.findAll", Book.class);
    }

    @Test
    public void getByField() {
	when(dao.em.createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Book.class))).thenReturn(mockQuery);
	when(mockQuery.setParameter("value", "Orson S. Card")).thenReturn(mockQuery);
	when(mockQuery.getResultStream()).thenReturn(stream);
	when(stream.findFirst()).thenReturn(_getOptBook());

	Book object = dao.getByField("author", "Orson S. Card");
	assertThat(object.getId(), equalTo(1));
	verify(dao.em).createQuery("SELECT t FROM Book t where t.author = :value", Book.class);
    }

    @Test
    public void getById() {
	when(dao.em.find(ArgumentMatchers.eq(Book.class), ArgumentMatchers.eq(1))).thenReturn(_getBook());

	Book object = dao.getById(1);
	assertThat(object.getId(), equalTo(1));
	verify(dao.em).find(Book.class, 1);
    }

    @BeforeEach
    public void setUp() {
	dao = new BookDaoImpl();
	dao.em = eManager;
    }
}
