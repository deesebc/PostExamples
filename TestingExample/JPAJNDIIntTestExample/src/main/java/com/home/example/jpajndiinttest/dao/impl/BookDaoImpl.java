package com.home.example.jpajndiinttest.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.home.example.jpajndiinttest.entity.Book;

@Stateless
public class BookDaoImpl extends GenericDaoImpl<Integer, Book> {

    @PersistenceContext(unitName = "persistence-unit")
    protected EntityManager em;

    @Override
    public EntityManager getEntityManager() {
	return em;
    }

}
