package es.home.example.config;

import org.wso2.msf4j.MicroservicesRunner;

import es.home.example.exception.ServiceExceptionMapper;
import es.home.example.service.impl.BookServiceImpl;

public class Application {
	public static void main(final String[] args) {
		new MicroservicesRunner().addExceptionMapper(new ServiceExceptionMapper()).deploy(new BookServiceImpl())
				.start();
	}
}
