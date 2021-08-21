package com.home.example.dbjunittest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.home.example.dbjunittest.entity.Book;

public class GenericDaoImplUnitTest {
    private static BookDaoImpl dao;

    @BeforeAll
    public static void setUp() {
	dao = new BookDaoImpl();
	dao.em = Mockito.mock(EntityManager.class);
    }

//	@Mock
//	TypedQuery mockQuery;

//	@Mock
//	EntityManager em;

    @Mock
    Stream<Book> stream;

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void findAll() {
	TypedQuery mockQuery = mock(TypedQuery.class);
	Mockito.when(dao.em.createNamedQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Book.class))).thenReturn(mockQuery);
	Mockito.when(mockQuery.getResultList()).thenReturn(getBooks());

	List<Book> lst = dao.findAll();
	assertEquals(1, lst.size());
	Mockito.verify(dao.em).createNamedQuery("Book.findAll", Book.class);
    }

    private Optional<Book> getBook() {
	Book book = new Book();
	book.setId(1);
	return Optional.of(book);
    }

    private List<Book> getBooks() {
	List<Book> lst = new ArrayList<Book>();
	lst.add(new Book());
	return lst;
    }

    @Test
    public void getByField() {
	Stream<Book> stream = mock(Stream.class);
	TypedQuery mockQuery = mock(TypedQuery.class);
	when(dao.em.createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Book.class))).thenReturn(mockQuery);
	when(mockQuery.setParameter("value", "Orson S. Card")).thenReturn(mockQuery);
	when(mockQuery.getResultStream()).thenReturn(stream);
	when(stream.findFirst()).thenReturn(getBook());

	Book object = dao.getByField("author", "Orson S. Card");
	assertThat(object.getId(), equalTo(1));
	Mockito.verify(dao.em).createQuery("SELECT t FROM Book t where t.author = :value", Book.class);
    }
}
