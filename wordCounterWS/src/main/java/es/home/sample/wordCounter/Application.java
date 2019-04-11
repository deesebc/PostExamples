package es.home.sample.wordCounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = { "es.home.sample.wordCounter" })
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
