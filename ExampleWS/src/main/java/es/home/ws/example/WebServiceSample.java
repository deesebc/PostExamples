package es.home.ws.example;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.home.ws.example.model.ws.Saludo;
import es.home.ws.example.model.ws.UserXml;

@WebService
public interface WebServiceSample {

	@WebMethod
	Saludo saluda();

	@WebMethod
	String despide();

	@WebMethod
	List<UserXml> getUsers();

}
