package com.example.springbootadminserver.config;

import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

	private final AdminServerProperties adminServer;

	private final SecurityProperties security;

	public SecuritySecureConfig(final AdminServerProperties adminServer, final SecurityProperties security) {
		this.adminServer = adminServer;
		this.security = security;
	}

	// Required to provide UserDetailsService for "remember functionality"
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(security.getUser().getName())
				.password("{noop}" + security.getUser().getPassword()).roles("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminServer.path("/"));

		http.authorizeRequests((authorizeRequests) -> authorizeRequests.antMatchers(adminServer.path("/assets/**"))
				.permitAll().antMatchers(adminServer.path("/actuator/info")).permitAll()
				.antMatchers(adminServer.path("/actuator/health")).permitAll().antMatchers(adminServer.path("/login"))
				.permitAll().anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin.loginPage(adminServer.path("/login")).successHandler(successHandler)
						.and())
				.logout((logout) -> logout.logoutUrl(adminServer.path("/logout"))).httpBasic(Customizer.withDefaults())
				.csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						.ignoringRequestMatchers(
								new AntPathRequestMatcher(adminServer.path("/instances"), HttpMethod.POST.toString()),
								new AntPathRequestMatcher(adminServer.path("/instances/*"),
										HttpMethod.DELETE.toString()),
								new AntPathRequestMatcher(adminServer.path("/actuator/**"))))
				.rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
	}

}
