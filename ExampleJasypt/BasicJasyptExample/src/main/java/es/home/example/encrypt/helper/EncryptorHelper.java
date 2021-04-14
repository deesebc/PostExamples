package es.home.example.encrypt.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EncryptorHelper {

	@Getter
	private static EncryptorHelper INSTANCE = new EncryptorHelper();

	Properties props = null;

	private EncryptorHelper() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());
		encryptor.setPoolSize(4);
		encryptor.setPassword(System.getenv("JASYPT_PWD")); // we HAVE TO set a password

		props = new EncryptableProperties(encryptor);
		try {
			props.load(new FileInputStream("src/main/resources/configuration.properties"));
		} catch (IOException except) {
			log.error("Error loading configuration.properties", except);
		}
	}

	public String getProperty(final String key) {
		return props.getProperty(key);
	}

//	protected String encrypt(final String sToEncrypt) {
//		return encryptor.encrypt(sToEncrypt);
//	}

}
