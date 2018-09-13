package es.home.example.config;

import org.wso2.msf4j.MicroservicesRunner;

import es.home.example.service.impl.BookServiceImpl;

public class Application {
	public static void main(final String[] args) {
		new MicroservicesRunner().deploy(new BookServiceImpl()).start();
	}
}
