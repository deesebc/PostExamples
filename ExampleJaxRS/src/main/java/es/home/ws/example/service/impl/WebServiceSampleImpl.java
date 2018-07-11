package es.home.ws.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.home.ws.example.dao.UserDao;
import es.home.ws.example.dao.impl.UserDaoImpl;
import es.home.ws.example.model.User;
import es.home.ws.example.model.ws.UserXml;
import es.home.ws.example.service.WebServiceSample;

@Path("/webserviceSample")
@Produces(MediaType.APPLICATION_JSON)
public class WebServiceSampleImpl implements WebServiceSample {

	public WebServiceSampleImpl() {
		super();
	}

	@GET
	@Path("/users")
	public List<UserXml> getPersonas() {
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.getUsers();
		List<UserXml> result = new ArrayList<UserXml>();
		for (User p : list) {
			result.add(UserXml.valueOf(p));
		}
		return result;
	}

}
