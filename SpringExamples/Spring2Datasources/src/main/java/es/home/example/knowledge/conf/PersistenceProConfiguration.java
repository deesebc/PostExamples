package es.home.example.knowledge.conf;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(basePackages = "es.home.example.knowledge.pro.repository", entityManagerFactoryRef = "proEntityManager", transactionManagerRef = "proTransactionManager")
public class PersistenceProConfiguration {
	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties("app.datasource.pro.configuration")
	public DataSource proDataSource() {
		return proDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties("app.datasource.pro")
	public DataSourceProperties proDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean proEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(proDataSource());
		// we can also asociate the entities through schema property in @Entity annotation
		// Example: @Table(name = "BOOK", schema = "library")
		em.setPackagesToScan(new String[] { "es.home.example.knowledge.entity" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public PlatformTransactionManager proTransactionManager(
			final @Qualifier("proEntityManager") LocalContainerEntityManagerFactoryBean proEntityManager) {
		return new JpaTransactionManager(proEntityManager.getObject());
	}
}