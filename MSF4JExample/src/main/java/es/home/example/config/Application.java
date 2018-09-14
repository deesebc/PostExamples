package es.home.example.config;

import javax.persistence.Persistence;

import org.wso2.msf4j.MicroservicesRunner;
import org.wso2.msf4j.analytics.metrics.MetricsInterceptor;

import es.home.example.dao.impl.BookDaoImpl;
import es.home.example.exception.ServiceExceptionMapper;
import es.home.example.service.impl.BookServiceImpl;

public class Application {
	public static void main(final String[] args) {
		MetricsInterceptor mInterceptor = new MetricsInterceptor();
		new MicroservicesRunner().addInterceptor(mInterceptor).addExceptionMapper(new ServiceExceptionMapper())
				.deploy(new BookServiceImpl(
						new BookDaoImpl(Persistence.createEntityManagerFactory("es.home.example.jpa.config"))))
				.start();
	}
}
