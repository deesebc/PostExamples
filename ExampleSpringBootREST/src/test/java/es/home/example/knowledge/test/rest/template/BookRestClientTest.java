package es.home.example.knowledge.test.rest.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import es.home.example.knowledge.entity.Book;
import es.home.example.knowledge.rest.template.BookRestClient;

@RunWith(SpringRunner.class)
// @WebMvcTest(BookRestClient.class)
@DataJpaTest
public class BookRestClientTest {

	@Autowired
	private BookRestClient client;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(8085));

	public void finish() {
		wireMockRule.shutdown();
	}

	@Before
	public void setup() {
		String body = "{\"id\":1,\"title\":\"1984\",\"author\":\"George Orwell\",\"insertDate\":null,\"updateDate\":null,\"insertUser\":null,\"updateUser\":null},{\"id\":2,\"title\":\"Fahrenheit 451\",\"author\":\"Ray Bradbury\",\"insertDate\":null,\"updateDate\":null,\"insertUser\":null,\"updateUser\":null},{\"id\":3,\"title\":\"Brave New World\",\"author\":\"Aldous Huxley\",\"insertDate\":null,\"updateDate\":null,\"insertUser\":null,\"updateUser\":null}";
		ResponseDefinitionBuilder response = WireMock.aResponse().withBody(body).withStatus(200)
				.withHeader("Content-Type", "application/json");
		WireMock.stubFor(WireMock.get("/book/1").willReturn(response));
	}

	@Test
	public void testFindByName() {
		try {
			Book book = client.getBook(1);
			Assert.assertEquals(book.getTitle(), "1984");
		} catch (Exception except) {
			except.printStackTrace();
		}
	}
}
