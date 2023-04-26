package com.example.home.apachecamel.router;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
