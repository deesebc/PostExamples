package es.home.example.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class Aplicacion {

    public static void main(final String[] args) {
	SpringApplication.run(Aplicacion.class, args);
    }
}
