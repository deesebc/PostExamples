package es.home.example.encrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.home.example.encrypt.service.PropertyService;

@SpringBootTest
public class PropertyServiceTest {

	@Autowired
	PropertyService service;

	// executing in eclipse with argument: -Djasypt_encryptor_password=XXXX
	// Dont need @EnableEncryptableProperties

	@Test
	public void testProps() {
		assertEquals(service.getUsr(), "root");
		assertEquals(service.getPwd(), "root");
	}
}
