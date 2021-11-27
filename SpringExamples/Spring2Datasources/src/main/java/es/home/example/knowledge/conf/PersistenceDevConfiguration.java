package es.home.example.knowledge.conf;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(basePackages = "es.home.example.knowledge.dev.repository", entityManagerFactoryRef = "devEntityManager", transactionManagerRef = "devTransactionManager")
public class PersistenceDevConfiguration {
	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties("app.datasource.dev")
	public DataSource devDataSource() throws Exception {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean devEntityManager(final @Qualifier("devDataSource") DataSource devDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(devDataSource);
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
	public PlatformTransactionManager devTransactionManager(
			final @Qualifier("devEntityManager") LocalContainerEntityManagerFactoryBean devEntityManager) {
		return new JpaTransactionManager(devEntityManager.getObject());
	}
}