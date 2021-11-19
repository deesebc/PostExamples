/**
 * 
 */
package es.home.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * @author daniel
 * 
 */
@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/almacen");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}

	@Bean(name = "userDetailsService")
	public UserDetailsService userDetailsService() {
		JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
		jdbcImpl.setDataSource(dataSource());
		String usersByUserNameQuery = "select username,password, enabled from users where username=?";
		String authoritiesByUsernameQuery = "select u.username, ur.authority from user_roles ur, users u where username=? and u.user_id = ur.user_id";
		jdbcImpl.setUsersByUsernameQuery(usersByUserNameQuery);
		jdbcImpl.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
		return jdbcImpl;
	}
}
