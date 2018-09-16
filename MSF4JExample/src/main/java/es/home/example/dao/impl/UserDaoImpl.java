package es.home.example.dao.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.home.example.dao.UserDao;
import es.home.example.pojo.User;

public class UserDaoImpl extends AbstractRepositoryImpl<User, Integer> implements UserDao {

	public UserDaoImpl() {
		super();
	}

	public UserDaoImpl(final EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public User findByNameAndPassword(final String name, final String password) {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(clazz);
			Root<User> rootEntry = cq.from(clazz);
			Predicate predicate = cb.and(cb.equal(rootEntry.get("name"), name),
					cb.equal(rootEntry.get("password"), password));
			CriteriaQuery<User> where = cq.select(rootEntry).where(predicate);
			return getEntityManager().createQuery(where).getSingleResult();
		} catch (javax.persistence.NoResultException exception) {
			return null;
		}
	}
}
