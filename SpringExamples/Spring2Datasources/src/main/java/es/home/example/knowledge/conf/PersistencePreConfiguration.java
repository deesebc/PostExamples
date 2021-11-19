package es.home.example.knowledge.conf;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "es.home.example.knowledge.pre.repository", entityManagerFactoryRef = "preEntityManager", transactionManagerRef = "preTransactionManager")
public class PersistencePreConfiguration {
	@Autowired
	private Environment env;

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.pre.configuration")
	public DataSource preDataSource() {
		return preDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.pre")
	public DataSourceProperties preDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean preEntityManager(final @Qualifier("preDataSource") DataSource preDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(preDataSource);
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
	@Primary
	public PlatformTransactionManager preTransactionManager(
			final @Qualifier("preEntityManager") LocalContainerEntityManagerFactoryBean preEntityManager) {
		return new JpaTransactionManager(preEntityManager.getObject());
	}
}
