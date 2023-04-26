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

@Slf4j
public class ApacheActiveMqRouterTest extends TestcontainersConf {

    @Autowired
    ProducerTemplate producer;

    @Test
    public void amqTo01() throws InterruptedException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        log.info("amqTo01 - init");
        producer.sendBody("direct:SendToPublic", "mensaje " + timeStamp);
        Thread.sleep(5000);
        log.info("amqTo01 - end");
    }
}
