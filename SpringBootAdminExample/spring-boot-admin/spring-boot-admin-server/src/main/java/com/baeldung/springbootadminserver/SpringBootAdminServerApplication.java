package com.baeldung.springbootadminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.codecentric.boot.admin.server.config.AdminServerHazelcastAutoConfiguration;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication(exclude = AdminServerHazelcastAutoConfiguration.class)
public class SpringBootAdminServerApplication {

    // to allow us permission to inspect our URL. Necessary to auto register the server itself.
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
	    http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
	}
    }

    public static void main(final String[] args) {
	SpringApplication.run(SpringBootAdminServerApplication.class, args);
    }

}
