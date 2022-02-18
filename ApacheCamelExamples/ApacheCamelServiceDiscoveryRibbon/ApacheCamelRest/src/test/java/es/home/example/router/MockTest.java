package es.home.example.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
class MockTest {

	@Configuration
	static class TestConfig {
		@Bean
		RoutesBuilder route() {
			return new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("direct:directTest").log("${body}").transform(simple("Hello ${body}")).to("mock:testA");

					from("seda:next?multipleConsumers=true").routeId("testA").log("${body}")
							.transform(simple("Hello ${body}")).to("mock:testA");
					from("seda:next?multipleConsumers=true").routeId("testB").to("mock:testB");
				}
			};
		}
	}

	@Autowired
	ProducerTemplate producerTemplate;

	@EndpointInject("mock:testA")
	MockEndpoint mockAEndpoint;

	@EndpointInject("mock:testB")
	MockEndpoint mockBEndpoint;

	@Test
	public void requestDirectTest() throws InterruptedException {
		final String ret = producerTemplate.requestBody("direct:directTest", "World", String.class);
		assertEquals("Hello World", ret);
	}

	@Test
	public void sedaTest() throws InterruptedException {
		mockAEndpoint.setExpectedMessageCount(1);
		mockAEndpoint.expectedBodiesReceived("Hello World");
		mockAEndpoint.expectedHeaderReceived("Content-Type", "text/plain");

		mockBEndpoint.setExpectedMessageCount(1);
		mockBEndpoint.expectedBodiesReceived("World");

		producerTemplate.sendBodyAndHeader("seda:next", "World", "Content-Type", "text/plain");
		mockAEndpoint.assertIsSatisfied();
		mockBEndpoint.assertIsSatisfied();

		final String ret = producerTemplate.requestBody("direct:directTest", "World", String.class);
		assertEquals("Hello World", ret);
	}

	@Test
	public void sendDirectTest() throws InterruptedException {
		mockAEndpoint.setExpectedMessageCount(1);
		mockAEndpoint.expectedBodiesReceived("Hello World");
		mockAEndpoint.expectedHeaderReceived("Content-Type", "text/plain");
		producerTemplate.sendBodyAndHeader("direct:directTest", "World", "Content-Type", "text/plain");
		mockAEndpoint.assertIsSatisfied();
	}
}
