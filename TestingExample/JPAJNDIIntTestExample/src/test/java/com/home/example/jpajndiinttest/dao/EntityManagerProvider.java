package com.home.example.jpajndiinttest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class EntityManagerProvider implements TestRule {

    public static EntityManagerProvider withUnit(final String unitName) {
	return new EntityManagerProvider(unitName);
    }

    private EntityManager em;

    private EntityTransaction tx;

    private EntityManagerProvider(final String unitName) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
	em = emf.createEntityManager();
	tx = em.getTransaction();
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
	return new Statement() {
	    @Override
	    public void evaluate() throws Throwable {
		base.evaluate();
		em.clear();
	    }
	};
    }

    public void begin() {
	tx.begin();
    }

    public void commit() {
	tx.commit();
    }

    public EntityManager em() {
	return em;
    }

    public EntityTransaction tx() {
	return tx;
    }

}
