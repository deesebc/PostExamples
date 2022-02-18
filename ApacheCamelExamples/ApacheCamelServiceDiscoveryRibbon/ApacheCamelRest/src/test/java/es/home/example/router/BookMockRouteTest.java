package es.home.example.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.home.ApacheCamelRestExample.router.BookMockRouter;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(properties = { "rest.get.libro.id = direct:start" })
class BookMockRouteTest {

	@Configuration
	static class TestConfig {
		@Bean
		RoutesBuilder route() {
			return new BookMockRouter();
		}
	}

	@Autowired
	ProducerTemplate producerTemplate;

	@Test
	public void restGetAllBooks() {
		producerTemplate.sendBody("direct:restGetAllBooks", "");
		final String ret = producerTemplate.requestBody("direct:restGetAllBooks", "", String.class);
		assertEquals(
				"[{\"id\":1,\"name\":\"Dune\",\"author\":\"Frank Herbert\"},{\"id\":2,\"name\":\"The stars my destination\",\"author\":\"Alfred Bester\"},{\"id\":3,\"name\":\"Ender's game\",\"author\":\"Orson S. Card\"},{\"id\":4,\"name\":\"Configure Ribbon\",\"author\":\"Server 1\"}]",
				ret);
	}

	@Test
	public void restGetById() {
		final String ret = producerTemplate.requestBodyAndHeader("direct:restGetById", "", "id", "1", String.class);
		assertEquals("{\"id\":1,\"name\":\"Dune\",\"author\":\"Frank Herbert\"}", ret);
	}

	@Test
	public void variableRestGetById() {
		final String ret = producerTemplate.requestBodyAndHeader("direct:start", "", "id", "1", String.class);
		assertEquals("{\"id\":1,\"name\":\"Dune\",\"author\":\"Frank Herbert\"}", ret);
	}
}
