package es.home.example.feignexample.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import es.home.example.feignexample.pojo.Book;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.ribbon.RibbonClient;
import feign.slf4j.Slf4jLogger;

public class FeignRibbonTest {

	private static BookClient bookClient;
	private static WireMockServer wireMockServer1;
	private static WireMockServer wireMockServer2;

	private final static String FOLDER = "src/test/resources/wiremock";

	@BeforeAll
	public static void setup() {
		// config.properties include: discovery-client.ribbon.listOfServers=localhost:57010,localhost:57011
//		getConfigInstance().setProperty("discovery-client.ribbon.listOfServers", "localhost:57010,localhost:57011");

		bookClient = Feign.builder().client(RibbonClient.create()).encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.logger(new Slf4jLogger(BookClient.class)).logLevel(Logger.Level.FULL)
				.target(BookClient.class, "http://discovery-client/api/books");

		wireMockServer1 = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER)
				.port(57010).notifier(new ConsoleNotifier(true)));
		wireMockServer1.start();
		wireMockServer1.stubFor(get(urlPathEqualTo("/api/books")).willReturn(aResponse().withBodyFile("books.json")));

		wireMockServer2 = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER)
				.port(57011).notifier(new ConsoleNotifier(true)));
		wireMockServer2.start();
		wireMockServer2.stubFor(get(urlPathEqualTo("/api/books")).willReturn(aResponse().withBodyFile("books.json")));
	}

	@Test
	public void findAll() throws Exception {
		List<Book> books1 = bookClient.findAll();
		List<Book> books2 = bookClient.findAll();
		assertThat(books1.size(), equalTo(3));
		assertThat(books2.size(), equalTo(3));

		wireMockServer1.verify(1, getRequestedFor(urlEqualTo("/api/books")));
		wireMockServer2.verify(1, getRequestedFor(urlEqualTo("/api/books")));
	}

}
