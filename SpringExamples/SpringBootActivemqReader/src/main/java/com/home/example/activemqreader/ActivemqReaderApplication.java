package com.home.example.activemqreader;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.home.example.activemqreader.config.JmsErrorHandler;

@SpringBootApplication
public class ActivemqReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqReaderApplication.class, args);
	}
	
	  @Bean
	    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	            DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
	        defaultJmsListenerContainerFactory.setTransactionManager(jmsTransactionManager(connectionFactory));
	        defaultJmsListenerContainerFactory.setSessionTransacted(true);
	        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(javax.jms.Session.SESSION_TRANSACTED);//Session.SESSION_TRANSACTED
	        configurer.configure(defaultJmsListenerContainerFactory, connectionFactory);
	        return defaultJmsListenerContainerFactory;
	    }
	  
	  @Bean
	    JmsListenerContainerFactory<?> jmsTx(ConnectionFactory connectionFactory, JmsErrorHandler errorHandler) {
		  DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setConnectionFactory(connectionFactory);
	        factory.setTransactionManager(jmsTransactionManager(connectionFactory));
	        factory.setSessionTransacted(true);
	        factory.setSessionAcknowledgeMode(javax.jms.Session.SESSION_TRANSACTED);//Session.SESSION_TRANSACTED
	        factory.setErrorHandler(errorHandler);
	        return factory;
	    }

	    @Bean
	    public PlatformTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
	        return new JmsTransactionManager(connectionFactory);
	    }

}
