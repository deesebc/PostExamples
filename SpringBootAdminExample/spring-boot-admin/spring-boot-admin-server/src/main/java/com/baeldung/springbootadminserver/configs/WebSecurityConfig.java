package com.baeldung.springbootadminserver.configs;

import java.util.UUID;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final AdminServerProperties adminServer;

	public WebSecurityConfig(final AdminServerProperties adminServer) {
		this.adminServer = adminServer;
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminServer.getContextPath() + "/");

		http.authorizeRequests().antMatchers(adminServer.getContextPath() + "/assets/**").permitAll()
				.antMatchers(adminServer.getContextPath() + "/login").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage(adminServer.getContextPath() + "/login").successHandler(successHandler).and()
				.logout().logoutUrl(adminServer.getContextPath() + "/logout").and().httpBasic().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringRequestMatchers(
						new AntPathRequestMatcher(adminServer.getContextPath() + "/instances",
								HttpMethod.POST.toString()),
						new AntPathRequestMatcher(adminServer.getContextPath() + "/instances/*",
								HttpMethod.DELETE.toString()),
						new AntPathRequestMatcher(adminServer.getContextPath() + "/actuator/**"))
				.and().rememberMe().key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600);
	}
}
