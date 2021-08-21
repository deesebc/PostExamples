package com.home.example.dbjunittest.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<K, E> {

    List<E> findAll();

    E getByField(String field, String value);

    E getById(K key);

    EntityManager getEntityManager();

}