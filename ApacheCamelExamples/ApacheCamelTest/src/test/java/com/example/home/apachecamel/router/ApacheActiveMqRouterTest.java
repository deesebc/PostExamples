package com.example.home.apachecamel.router;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.home.apachecamel.ApacheCamelTestApplication;
import lombok.extern.log4j.Log4j2;

@Testcontainers
@CamelSpringBootTest
@SpringBootTest(classes = ApacheCamelTestApplication.class)
@Log4j2
public class ApacheActiveMqRouterTest {

    @Autowired
    ProducerTemplate producer;

    @Container
    private static GenericContainer container = new GenericContainer("rmohr/activemq").withExposedPorts(61616, 8161);
    private static Integer tcpPort;

    @BeforeAll
    public static void beforeAll() {
        container.start();
        tcpPort = container.getMappedPort(61616);
    }

    @DynamicPropertySource
    static void replaceProperties(DynamicPropertyRegistry registry) {
        registry.add("activemq.broker-url", () -> "tcp://localhost:" + tcpPort);
    }

    @Test
    public void amqTo01() throws InterruptedException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        log.info("amqTo01 - init");
        producer.sendBody("direct:SendToPublic", "mensaje " + timeStamp);
        Thread.sleep(5000);
        log.info("amqTo01 - end");
    }
}
