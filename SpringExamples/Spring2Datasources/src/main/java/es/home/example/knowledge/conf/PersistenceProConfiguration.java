package es.home.example.knowledge.conf;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(basePackages = "es.home.example.knowledge.pro.repository", entityManagerFactoryRef = "proEntityManager", transactionManagerRef = "proTransactionManager")
public class PersistenceProConfiguration {
	@Autowired
	private Environment env;

	@Bean
	public DataSource proDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("pro.jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean proEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(proDataSource());
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
	public PlatformTransactionManager proTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(proEntityManager().getObject());
		return transactionManager;
	}
}