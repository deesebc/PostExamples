package es.home.example.encrypt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestEncryption {

	private String toEncrypt = "pAsSw0Rd";
	private String PBES_PWD = System.getenv("JASYPT_PWD");
	private Long init;
	private Long end;

	@Test
	public void basic() {
		log.info("basic");
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(toEncrypt);
		assertThat(passwordEncryptor.checkPassword(toEncrypt, encryptedPassword), equalTo(true));
	}

	@AfterEach
	public void end() {
		end = System.currentTimeMillis();
		log.info("Time: " + (end - init));
	}

	@BeforeEach
	public void init() {
		init = System.currentTimeMillis();
	}

	@Test
	public void pooledPbes() {
		log.info("pooledPbes");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setPoolSize(4);
		encryptor.setPassword(PBES_PWD); // we HAVE TO set a password
		String encryptedPassword = encryptor.encrypt(toEncrypt);
		assertThat(encryptor.decrypt(encryptedPassword), equalTo(toEncrypt));
	}

	@Test
	public void pooledPbes_configure() {
		log.info("pooledPbes_configure");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());
		encryptor.setPoolSize(4);
		encryptor.setPassword(PBES_PWD); // we HAVE TO set a password
		String encryptedPassword = encryptor.encrypt(toEncrypt);
		assertThat(encryptor.decrypt(encryptedPassword), equalTo(toEncrypt));
	}

	@Test
	public void pooledPbes_configure_config() {
		log.info("pooledPbes_configure_config");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setIvGenerator(new RandomIvGenerator());
		config.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		config.setPasswordCharArray(PBES_PWD.toCharArray());
		encryptor.setConfig(config);
		encryptor.setPoolSize(4);
		String encryptedPassword = encryptor.encrypt(toEncrypt);
		assertThat(encryptor.decrypt(encryptedPassword), equalTo(toEncrypt));
	}

	@Test
	public void standarPbes() {
		log.info("standarPbes");
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PBES_PWD); // we HAVE TO set a password
		encryptor.setIvGenerator(new RandomIvGenerator());
		String encryptedPassword = encryptor.encrypt(toEncrypt);
		assertThat(encryptor.decrypt(encryptedPassword), equalTo(toEncrypt));
	}

	@Test
	public void strong() {
		log.info("strong");
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(toEncrypt);
		assertThat(passwordEncryptor.checkPassword(toEncrypt, encryptedPassword), equalTo(true));
	}
}
