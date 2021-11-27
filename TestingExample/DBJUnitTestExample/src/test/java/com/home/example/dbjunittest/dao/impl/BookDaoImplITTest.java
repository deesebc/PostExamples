package com.home.example.dbjunittest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.home.example.dbjunittest.entity.Book;

public class BookDaoImplITTest {

    private static BookDaoImpl dao;

    @BeforeAll
    public static void before() {
	dao = new BookDaoImpl();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit-test");
	dao.em = emf.createEntityManager();
    }

    @Test
    public void findAll() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(1));
    }

    @Test
    public void getByField() {
	Book obj = dao.getByField("author", "Orson S. Card");
	assertThat(obj.getId(), equalTo(1));
    }

    @Test
    public void getById() {
	Book obj = dao.getById(1);
	assertThat(obj.getAuthor(), equalTo("Orson S. Card"));
    }
}
