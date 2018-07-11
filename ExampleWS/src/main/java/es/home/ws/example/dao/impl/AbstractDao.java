package es.home.ws.example.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao {

	private static EntityManagerFactory emf;

	private final ThreadLocal<EntityManager> em = new ThreadLocal<EntityManager>() {
		@Override
		protected EntityManager initialValue() {
			return getEntityManagerFactory().createEntityManager();
		}
	};

	private EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		}
		return emf;
	}

	protected EntityManager getEntityManager() {
		return em.get();
	}
}
