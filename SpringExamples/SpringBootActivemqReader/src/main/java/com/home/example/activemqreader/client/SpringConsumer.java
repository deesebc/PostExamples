package com.home.example.activemqreader.client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpringConsumer {

  @JmsListener(destination = "${activemq.queue-name}")
  public void listen(final String message) {
    log.info("Inside spring-java-client");
    log.info("message: " + message);
  }
  
  @JmsListener(destination = "Consumer.SPRTX.VirtualTopic.testingQ",  containerFactory = "jmsTx")
  @Transactional(rollbackFor = RuntimeException.class)
  public void listenTx(final String message) {
    log.info("Inside spring-java-client tx");
    log.info("tx message: " + message);
    throw new RuntimeException("Rolling back!");
  }

}
