package com.home.example.activemqreader.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
public class JmsErrorHandler implements ErrorHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(JmsErrorHandler.class);

    public JmsErrorHandler() {
        LOGGER.info("Creating error handler");
    }

    @Override
    public void handleError(Throwable t) {
        LOGGER.error("An error occurred during processing of the JMS message", t);
    }
}