package es.home.ws.example.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import es.home.ws.example.WebServiceSample;
import es.home.ws.example.dao.UserDao;
import es.home.ws.example.dao.impl.UserDaoImpl;
import es.home.ws.example.model.User;
import es.home.ws.example.model.ws.Saludo;
import es.home.ws.example.model.ws.UserXml;

@WebService(endpointInterface = "es.home.ws.example.WebServiceSample")
public class WebServiceSampleImpl implements WebServiceSample {

	private final UserDao dao = new UserDaoImpl();

	@Override
	public Saludo saluda() {
		Saludo s = new Saludo();
		s.setNombre("John");
		s.setSaludo("Doe");
		return s;
	}

	@Override
	public String despide() {
		return "Goodbye";
	}

	@Override
	public List<UserXml> getUsers() {
		List<User> list = dao.getUsers();
		List<UserXml> result = new ArrayList<UserXml>();
		for (User p : list) {
			result.add(UserXml.valueOf(p));
		}
		return result;
	}
}
