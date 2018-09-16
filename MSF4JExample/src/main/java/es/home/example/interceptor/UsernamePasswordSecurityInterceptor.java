package es.home.example.interceptor;

import java.util.Base64;

import org.wso2.msf4j.security.basic.AbstractBasicAuthSecurityInterceptor;

import es.home.example.dao.UserDao;
import es.home.example.pojo.User;

public class UsernamePasswordSecurityInterceptor extends AbstractBasicAuthSecurityInterceptor {

	UserDao dao;

	public UsernamePasswordSecurityInterceptor(final UserDao userDao) {
		dao = userDao;
	}

	@Override
	protected boolean authenticate(final String username, final String password) {
		String pwdEconded = Base64.getEncoder().encodeToString(password.getBytes());
		User result = dao.findByNameAndPassword(username, pwdEconded);
		if (result != null) {
			return true;
		}
		return false;
	}

}
