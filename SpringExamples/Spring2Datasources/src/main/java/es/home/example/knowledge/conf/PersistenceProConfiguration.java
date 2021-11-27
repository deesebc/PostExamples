package es.home.example.knowledge.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "es.home.example.knowledge.pro.repository", entityManagerFactoryRef = "proEntityManager", transactionManagerRef = "proTransactionManager")
public class PersistenceProConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.pro")
	public DataSource proDataSource() throws Exception {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean proEntityManager(final EntityManagerFactoryBuilder builder,
			final @Qualifier("proDataSource") DataSource proDataSource) {
		return builder.dataSource(proDataSource).packages("es.home.example.knowledge.entity").build();
	}

	@Bean
	public PlatformTransactionManager proTransactionManager(
			final @Qualifier("proEntityManager") LocalContainerEntityManagerFactoryBean proEntityManager) {
		return new JpaTransactionManager(proEntityManager.getObject());
	}
}