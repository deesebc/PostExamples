package es.home.example.encrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.home.example.encrypt.service.AppPropertyService;

@SpringBootTest
public class AppPropertyServiceTest {

	@Autowired
	AppPropertyService service;

	// need to include main-resource configuration in pom>build>resources
	// clean test -Djasypt.encryptor.password=XXXX

	@Test
	public void testProps() {
		assertEquals(service.getUsr(), "root");
		assertEquals(service.getPwd(), "root");
	}
}
