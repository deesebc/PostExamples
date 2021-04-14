package es.home.example.encrypt.helper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class EncryptorHelperTest {

	String root = "root";

	@Test
	public void encripted() {
		assertThat(EncryptorHelper.getINSTANCE().getProperty("datasource.usr"), equalTo(root));
	}

	@Test
	public void non_encripted() {
		assertThat(EncryptorHelper.getINSTANCE().getProperty("datasource.pwd"), equalTo(root));
	}
}
