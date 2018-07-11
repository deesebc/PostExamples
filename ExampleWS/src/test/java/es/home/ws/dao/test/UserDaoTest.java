package es.home.ws.dao.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.home.ws.example.dao.UserDao;
import es.home.ws.example.dao.impl.UserDaoImpl;
import es.home.ws.example.model.User;

public class UserDaoTest {

	private final static Logger LOGGER = Logger.getLogger(UserDaoTest.class);

	private UserDao dao;

	@Before
	public void before() {
		dao = new UserDaoImpl();
	}

	@Test
	public void metodo() {
		List<User> list = dao.getUsers();
		if (list == null || list.isEmpty()) {
			Assert.fail();
		} else {
			for (User user : list) {
				LOGGER.debug("Name: " + user.getUsername());
			}
			Assert.assertTrue(true);
		}
	}

}
