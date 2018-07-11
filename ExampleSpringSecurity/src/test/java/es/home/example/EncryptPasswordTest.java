package es.home.example;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import es.home.example.config.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EncryptPasswordTest {

	private final static Logger LOGGER = Logger.getLogger(EncryptPasswordTest.class);

	@Test
	public void createEncrypt() {
		String cryptedPassword = new BCryptPasswordEncoder().encode("user");
		LOGGER.debug(cryptedPassword);
	}
}
