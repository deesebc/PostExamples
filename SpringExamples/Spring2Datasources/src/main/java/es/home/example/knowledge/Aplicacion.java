package es.home.example.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "es.home.example.knowledge" })
public class Aplicacion {

	public static void main(final String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}
}
