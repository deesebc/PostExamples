package com.home.example.jpajndiinttest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.home.example.jpajndiinttest.dao.AbstractDBTest;
import com.home.example.jpajndiinttest.entity.Book;

public class BookDaoImplJNDITest extends AbstractDBTest {

    private static BookDaoImpl dao;

    @BeforeAll
    public static void before() throws NamingException, SQLException {
	AbstractDBTest.setup();
	dao = new BookDaoImpl();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit-test");
	dao.em = emf.createEntityManager();
    }

    @Test
    public void findAll() throws InterruptedException {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(1));
    }

    @Test
    public void getByField() throws InterruptedException {
	Book obj = dao.getByField("author", "Orson S. Card");
	assertThat(obj.getId(), equalTo(1));
    }

    @Test
    public void getById() throws InterruptedException {
	Book obj = dao.getById(1);
	assertThat(obj.getAuthor(), equalTo("Orson S. Card"));
    }
}
