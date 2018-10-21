package es.home.example.test.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import es.home.example.client.WebServiceClient;
import es.home.ws.example.Saludo;

public class WebServiceClientTest {
	WebServiceClient client;

	@Before
	public void configure() {
		client = new WebServiceClient();
	}

	@Test
	public void metodo() {
		Saludo saluda = client.saluda();
		assertNotNull(saluda);
		assertEquals("John", saluda.getNombre());
	}
}
