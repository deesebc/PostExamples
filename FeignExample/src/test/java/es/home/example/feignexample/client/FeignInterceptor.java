package es.home.example.feignexample.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import es.home.example.feignexample.customize.CustomTokenGeneratorInterceptor;
import es.home.example.feignexample.pojo.Book;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class FeignInterceptor {
	private static BookClient bookClient;
	private static WireMockServer wireMockServer;

	private final static String FOLDER = "src/test/resources/wiremock";

	@BeforeAll
	public static void setup() {
		bookClient = Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.logger(new Slf4jLogger(BookClient.class)).logLevel(Logger.Level.FULL)
				.requestInterceptor(new CustomTokenGeneratorInterceptor("123456", "654321"))
				.target(BookClient.class, "http://localhost:57010/api/books");
		wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER).port(57010)
				.notifier(new ConsoleNotifier(true)));
		wireMockServer.start();
	}

	public static void teardown() {
		wireMockServer.stop();
	}

	@Test
	public void findById() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books/1")).willReturn(aResponse().withBodyFile("book1.json")));

		Book book = bookClient.findById(1);
		String token = "eyJjbGllbnRJZCI6IjEyMzQ1NiIsImNsaWVudFNlY3JldCI6IjY1NDMyMSJ9";
		assertThat(book.getAuthor(), equalTo("Orson S. Card"));
		wireMockServer.verify(1,
				getRequestedFor(urlEqualTo("/api/books/1")).withHeader("Accept", WireMock.equalTo("application/json"))
						.withHeader("X-Auth-Token", WireMock.equalTo(token)));
	}
}
