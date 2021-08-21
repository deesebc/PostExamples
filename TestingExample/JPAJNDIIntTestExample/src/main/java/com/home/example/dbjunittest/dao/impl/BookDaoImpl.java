package com.home.example.dbjunittest.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.home.example.dbjunittest.entity.Book;

@Stateless
public class BookDaoImpl extends GenericDaoImpl<Integer, Book> {

    @PersistenceContext(unitName = "puDSsct")
    protected EntityManager em;

    @Override
    public EntityManager getEntityManager() {
	return em;
    }

}
