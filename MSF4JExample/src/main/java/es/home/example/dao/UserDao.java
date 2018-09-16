package es.home.example.dao;

import es.home.example.pojo.User;

public interface UserDao extends AbstractRepository<User, Integer> {
	User findByNameAndPassword(final String name, final String password);
}
