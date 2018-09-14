package es.home.example.dao.impl;

import javax.persistence.EntityManagerFactory;

import es.home.example.dao.BookDao;
import es.home.example.pojo.Book;

public class BookDaoImpl extends AbstractRepositoryImpl<Book, Integer> implements BookDao {

	public BookDaoImpl() {
		super();
	}

	public BookDaoImpl(final EntityManagerFactory emf) {
		super(emf);
	}

}
