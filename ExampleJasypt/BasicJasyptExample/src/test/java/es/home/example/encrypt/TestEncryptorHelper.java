package es.home.example.encrypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestEncryptorHelper {

	@Getter
	private static TestEncryptorHelper INSTANCE = new TestEncryptorHelper();

	public static void main(final String[] args) {
		log.info(TestEncryptorHelper.getINSTANCE().encrypt("root"));
	}

	private PooledPBEStringEncryptor encryptor;

	private TestEncryptorHelper() {
		encryptor = new PooledPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());
		encryptor.setPoolSize(4);
		encryptor.setPassword(System.getenv("JASYPT_PWD")); // we HAVE TO set a password
	}

	public String encrypt(final String sToEncrypt) {
		return encryptor.encrypt(sToEncrypt);
	}

}
