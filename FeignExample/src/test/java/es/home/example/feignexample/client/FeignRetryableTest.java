package es.home.example.feignexample.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import es.home.example.feignexample.customize.CustomErrorDecode;
import es.home.example.feignexample.customize.CustomException;
import es.home.example.feignexample.pojo.Book;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RetryableException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignRetryableTest {

	private static BookClient bookClient;
	private static WireMockServer wireMockServer;

	private final static String FOLDER = "src/test/resources/wiremock";

	@BeforeAll
	public static void setup() {
		bookClient = Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.logger(new Slf4jLogger(BookClient.class)).logLevel(Logger.Level.FULL)
				.retryer(new feign.Retryer.Default(100, 1000, 3)).errorDecoder(new CustomErrorDecode())
				.options(new Request.Options(2, TimeUnit.SECONDS, 4, TimeUnit.SECONDS, true))
				.target(BookClient.class, "http://localhost:57005/api/books");

		wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER).port(57005)
				.notifier(new ConsoleNotifier(true)));
		wireMockServer.start();
	}

	@AfterAll
	public static void teardown() {
		wireMockServer.stop();
	}

	@Test
	public void findById_ko() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books/1")).willReturn(aResponse().withStatus(500)));
		log.info("Wiremock configured");

		Throwable exceptionThatWasThrown = Assertions.assertThrows(CustomException.class, () -> {
			bookClient.findById(1);
		});

		assertThat(exceptionThatWasThrown.getMessage(), equalTo("Error detected in backend"));

	}

	@Test
	public void findById_ok() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books/1")).willReturn(aResponse().withBodyFile("book1.json")));
		log.info("Wiremock configured");

		Book book = bookClient.findById(1);
		assertThat(book.getAuthor(), equalTo("Orson S. Card"));
	}

	@Test
	public void findById_timeout() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books/2"))
				.willReturn(aResponse().withBodyFile("book1.json").withFixedDelay(5000)));
		log.info("Wiremock configured");

		Assertions.assertThrows(RetryableException.class, () -> {
			bookClient.findById(2);
		});

		wireMockServer.verify(3, getRequestedFor(urlEqualTo("/api/books/2")));
	}
}
