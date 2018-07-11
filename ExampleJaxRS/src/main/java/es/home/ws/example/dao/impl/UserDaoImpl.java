package es.home.ws.example.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.home.ws.example.dao.UserDao;
import es.home.ws.example.model.User;

public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		EntityManager em = getEntityManager();
		Query qry = em.createQuery("select p from User p");
		qry.setMaxResults(100);
		return qry.getResultList();
	}
}
