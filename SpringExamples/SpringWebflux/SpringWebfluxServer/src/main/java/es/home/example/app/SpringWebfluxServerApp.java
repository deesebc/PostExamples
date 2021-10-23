package es.home.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringWebfluxServerApp extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(SpringWebfluxServerApp.class, args);
	}
}