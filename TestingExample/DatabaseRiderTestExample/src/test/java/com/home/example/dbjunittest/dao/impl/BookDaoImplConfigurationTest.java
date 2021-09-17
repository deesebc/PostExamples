package com.home.example.dbjunittest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import com.github.database.rider.junit5.DBUnitExtension;
import com.home.example.dbjunittest.entity.Book;

@ExtendWith(DBUnitExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class BookDaoImplConfigurationTest {

    private static BookDaoImpl dao;
    private static ConnectionHolder connectionHolder = () -> EntityManagerProvider.instance("persistence-unit-test").connection();

    @BeforeAll
    @DataSet("books.yml")
    public static void setUpOne() {
	dao = new BookDaoImpl();
	dao.em = EntityManagerProvider.em();
    }

    @Test
    @ExpectedDataSet(value = "expectedBooks.yml", ignoreCols = "id")
    public void create() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(3));
	Book book = new Book(4, "Foundation", "Isaac Asimov");
	dao.em.getTransaction().begin(); // we are testing with non-jta datasource
	dao.create(book);
	dao.em.getTransaction().commit();
	lst = dao.findAll();
	assertThat(lst.size(), equalTo(4));
    }

    @Test
    @DataSet(cleanBefore = true, executeScriptsAfter = { "book1.sql" })
    public void createRegEx() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(0));
    }

    @Test
    @DataSet
    public void createRegEx2() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(1));
    }

}
