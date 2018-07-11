package es.home.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "es.home.example")
public class Application {
	public static void main(final String[] args) throws Throwable {
		SpringApplication.run(Application.class, args);
	}
}
