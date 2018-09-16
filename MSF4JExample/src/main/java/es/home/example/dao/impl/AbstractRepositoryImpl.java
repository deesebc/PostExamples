package es.home.example.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import es.home.example.dao.AbstractRepository;
import es.home.example.pojo.GenericEntity;

public abstract class AbstractRepositoryImpl<P extends GenericEntity<? extends Serializable>, K>
		implements AbstractRepository<P, K> {

	protected EntityManagerFactory emf;

	protected Class<P> clazz;

	@SuppressWarnings("unchecked")
	public AbstractRepositoryImpl() {
		clazz = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public AbstractRepositoryImpl(final EntityManagerFactory emf) {
		this.emf = emf;
		clazz = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void create(final P t) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		manager.close();
	}

	@Override
	public P findById(final K id) {
		return getEntityManager().find(clazz, id);
	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public List<P> getResultList() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<P> cq = cb.createQuery(clazz);
		Root<P> rootEntry = cq.from(clazz);
		CriteriaQuery<P> all = cq.select(rootEntry);
		TypedQuery<P> allQuery = getEntityManager().createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public void remove(final P t) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		manager.close();
	}
}
