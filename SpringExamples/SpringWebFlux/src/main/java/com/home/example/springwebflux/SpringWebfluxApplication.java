package com.home.example.springwebflux;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(final String[] args) {
	SpringApplication.run(SpringWebfluxApplication.class, args);
    }

    @Value("${spring.datasource.maximum-pool-size}")
    private int connectionPoolSize;

    @Bean
    public Scheduler jdbcScheduler() {
	return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }

    @Bean
    public TransactionTemplate transactionTemplate(final PlatformTransactionManager transactionManager) {
	return new TransactionTemplate(transactionManager);
    }

}
