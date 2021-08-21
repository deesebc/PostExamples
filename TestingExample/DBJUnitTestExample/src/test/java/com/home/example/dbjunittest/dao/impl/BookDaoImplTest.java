package com.home.example.dbjunittest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.home.example.dbjunittest.dao.EntityManagerProvider;
import com.home.example.dbjunittest.entity.Book;

public class BookDaoImplTest {

    // https://arquillian.org/guides/testing_java_persistence_es/ -> arquillian
    // https://www.christophbrill.de/en/posts/unit-testing-with-junit-and-mockito/ -> mock

    private static BookDaoImpl dao;

    // USE persistence-url.xml

    @Rule
    public static EntityManagerProvider provider = EntityManagerProvider.withUnit("persistence-unit-test");

    @BeforeAll
    public static void before() {
	dao = new BookDaoImpl();
	dao.em = provider.em();
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
