package com.example.home.apachecamel.router;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApacheMySQLRouterTest extends TestcontainersConf{

    @Autowired
    ProducerTemplate producer;

    @Test
    public void getBookById() throws InterruptedException {
        log.info("getBookById - init");
        producer.sendBodyAndHeader("direct:getBookById", null, "id", 1);
        Thread.sleep(5000);
        log.info("getBookById - end");
    }
}
