package es.home.example.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.wso2.msf4j.MicroservicesRunner;
import org.wso2.msf4j.analytics.metrics.MetricsInterceptor;

import es.home.example.dao.impl.BookDaoImpl;
import es.home.example.dao.impl.UserDaoImpl;
import es.home.example.exception.ServiceExceptionMapper;
import es.home.example.interceptor.UsernamePasswordSecurityInterceptor;
import es.home.example.service.impl.BookServiceImpl;

public class Application {
	private static UsernamePasswordSecurityInterceptor getBasicInterceptor(final EntityManagerFactory emf) {
		return new UsernamePasswordSecurityInterceptor(new UserDaoImpl(emf));
	}

	public static void main(final String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("es.home.example.jpa.config");
		MetricsInterceptor mInterceptor = new MetricsInterceptor();
		new MicroservicesRunner().addInterceptor(mInterceptor).addGlobalRequestInterceptor(getBasicInterceptor(emf))
				.addExceptionMapper(new ServiceExceptionMapper()).deploy(getBookService(emf))
				.start();
	}

	private static BookServiceImpl getBookService(EntityManagerFactory emf) {
		return new BookServiceImpl(new BookDaoImpl(emf));
	}
}
