package es.home.example.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class ClientApplication {

    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
	    http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
	}
    }

    public static void main(final String[] args) {
	SpringApplication.run(ClientApplication.class, args);
    }
}
