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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.home.apachecamel.ApacheCamelTestApplication;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Testcontainers
@CamelSpringBootTest
@SpringBootTest(classes = ApacheCamelTestApplication.class)
@Slf4j
public class ApacheActiveMqRouterTest extends TestcontainersConf {

    @Autowired
    ProducerTemplate producer;

    @Container
    private static GenericContainer<?> container = new GenericContainer<>("rmohr/activemq").withExposedPorts(61616, 8161);
    private static Integer tcpPort;

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7.34");

    //1.18 generate NoSuchMethodError optionallyMapResourceParameterAsVolume. 
    //1.17 java.lang.ClassNotFoundException: org.testcontainers.shaded.org.apache.commons.lang.StringUtils

    static MySQLContainer<?> database = new MySQLContainer<>(MYSQL_57_IMAGE)
    .withInitScript("scripts/init_mysql.sql")
    .withDatabaseName("library").withLogConsumer(new Slf4jLogConsumer(log));

    @BeforeAll
    public static void beforeAll() {
        container.start();
        database.start();
        tcpPort = container.getMappedPort(61616);
    }

    @DynamicPropertySource
    static void replaceProperties(DynamicPropertyRegistry registry) {
        registry.add("activemq.broker-url", () -> "tcp://localhost:" + tcpPort);

        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
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
