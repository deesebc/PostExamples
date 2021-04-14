package es.home.example.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@ComponentScan(basePackages = { "es.home.example.encrypt" })
@EnableEncryptableProperties
public class Aplicacion {
	public static void main(final String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}
}
