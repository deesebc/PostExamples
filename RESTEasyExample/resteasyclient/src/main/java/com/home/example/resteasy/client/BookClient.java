package com.home.example.resteasy.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.home.example.resteasy.bean.Book;
import com.home.example.resteasy.service.BookService;

public class BookClient {
    final static String path; // = "http://localhost:8080/RestEasyService/library/";
    private static BookService proxy;
    static {
	Config config = ConfigProvider.getConfig();
	path = config.getValue("BOOKWS_URL", String.class);
	WebTarget target = ClientBuilder.newClient().target(config.getValue("BOOKWS_URL", String.class));
	proxy = ((ResteasyWebTarget) target).proxy(BookService.class);
    }

    public Book read_proxy(final Integer id) {
	return proxy.read(id);
    }

    public Book read_target(final Integer id) {
	Book retorno = null;
	WebTarget target = ClientBuilder.newClient().target(path + "book/" + id);
	try (Response response = target.request().get()) {
	    retorno = response.readEntity(Book.class);
	}
	return retorno;
    }

}
