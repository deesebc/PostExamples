package com.home.example.jpajndiinttest.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.home.example.jpajndiinttest.dao.GenericDao;

public abstract class GenericDaoImpl<K, E> implements GenericDao<K, E> {

    private Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
	Type genericSuperClass = getClass().getGenericSuperclass();

	ParameterizedType parametrizedType = null;
	while (parametrizedType == null) {
	    if (genericSuperClass instanceof ParameterizedType) {
		parametrizedType = (ParameterizedType) genericSuperClass;
	    } else {
		genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
	    }
	}

	this.persistentClass = (Class<E>) parametrizedType.getActualTypeArguments()[1];
    }

    @Override
    public List<E> findAll() {
	return getEntityManager().createNamedQuery(String.format("%s.findAll", persistentClass.getSimpleName()), persistentClass)
		.getResultList();
    }

    @Override
    public E getByField(final String field, final String value) {
	return getEntityManager()
		.createQuery(String.format("SELECT t FROM %s t where t.%s = :value", persistentClass.getSimpleName(), field),
			persistentClass)
		.setParameter("value", value).getResultStream().findFirst().orElse(null);
    }

    @Override
    public E getById(final K key) {
	return getEntityManager().find(persistentClass, key);
    }

}
