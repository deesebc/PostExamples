package es.home.example.encrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.home.example.encrypt.service.ConfigurationPropService;

@SpringBootTest
public class ConfigurationPropServiceTest {

	@Autowired
	ConfigurationPropService service;

	// Setting env JASYPT_ENCRYPTOR_PASSWORD=XXXX
	@Test
	public void testProps() {
		assertEquals(service.getUsr(), "root");
		assertEquals(service.getPwd(), "root");
	}
}
