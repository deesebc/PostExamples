package es.home.example.feignexample.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import es.home.example.feignexample.pojo.Book;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignTest {

	private static BookClient bookClient;
	private static WireMockServer wireMockServer;

	private final static String FOLDER = "src/test/resources/wiremock";

	@BeforeAll
	public static void setup() {
		bookClient = Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.logger(new Slf4jLogger(BookClient.class)).logLevel(Logger.Level.FULL)
				.target(BookClient.class, "http://localhost:57010/api/books");

		wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER).port(57010)
				.notifier(new ConsoleNotifier(true))); // .httpsPort(57005).keystorePath(FOLDER + "/identity.jks")
		wireMockServer.start();
	}

	@AfterAll
	public static void teardown() {
		wireMockServer.stop();
	}

	@Test
	public void findAll() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books")).willReturn(aResponse().withBodyFile("books.json")));
		log.info("Wiremock configured");

		List<Book> books = bookClient.findAll();
		assertThat(books.size(), equalTo(3));
	}

	@Test
	public void findById() throws Exception {
		wireMockServer.stubFor(get(urlPathEqualTo("/api/books/1")).willReturn(aResponse().withBodyFile("book1.json")));
		log.info("Wiremock configured");

		Book book = bookClient.findById(1);
		assertThat(book.getAuthor(), equalTo("Orson S. Card"));
		wireMockServer.verify(1,
				getRequestedFor(urlEqualTo("/api/books/1")).withHeader("Accept", WireMock.equalTo("application/json")));
	}

	@Test
	public void remove() throws Exception {
		wireMockServer.stubFor(delete(urlPathEqualTo("/api/books/2")).willReturn(aResponse().withStatus(204)));
		log.info("Wiremock configured");

		bookClient.remove(2);
		wireMockServer.verify(1, deleteRequestedFor(urlEqualTo("/api/books/2")));
	}

	@Test
	public void update() throws Exception {
		wireMockServer
				.stubFor(put(urlPathEqualTo("/api/books/3")).willReturn(aResponse().withBodyFile("book3_update.json")));
		log.info("Wiremock configured");

		Book book = new Book(3, "Dune", "Frank Herbert.");

		bookClient.update(3, book);
		wireMockServer.verify(1,
				putRequestedFor(urlEqualTo("/api/books/3"))
						.withRequestBody(equalToJson("{\"id\":3,\"author\":\"Frank Herbert.\",\"title\":\"Dune\"}"))
						.withHeader("Accept", WireMock.equalTo("application/json"))
						.withHeader("Content-Type", WireMock.equalTo("application/json")));
	}

}
