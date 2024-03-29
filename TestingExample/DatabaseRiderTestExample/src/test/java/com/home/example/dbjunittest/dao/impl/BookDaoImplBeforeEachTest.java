package com.home.example.dbjunittest.dao.impl;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static com.github.database.rider.core.util.EntityManagerProvider.instance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.home.example.dbjunittest.entity.Book;

@ExtendWith(DBUnitExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class BookDaoImplBeforeEachTest {

    private BookDaoImpl dao;
    private ConnectionHolder connectionHolder = () -> instance("persistence-unit-test").connection();

    @Test
    public void a_findAll_precreate() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(3));
    }

    @Test
    public void b_create() {
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
    public void c_findAll_postcreate() {
	List<Book> lst = dao.findAll();
	assertThat(lst.size(), equalTo(3));
    }

    @BeforeEach
    @DataSet("books.yml")
    public void setUpSeveral() {
	dao = new BookDaoImpl();
	System.out.println("limpio ??? ");
	dao.em = em();
    }
}
