package com.example.home.apachecamel.router;

import java.io.IOException;

import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Testcontainers
@CamelSpringBootTest
@Slf4j
public class TestcontainersConf {
    
    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7.34");

    //1.18 generate NoSuchMethodError optionallyMapResourceParameterAsVolume. 
    //1.17 java.lang.ClassNotFoundException: org.testcontainers.shaded.org.apache.commons.lang.StringUtils

    static MySQLContainer<?> database = new MySQLContainer<>(MYSQL_57_IMAGE)
    .withInitScript("scripts/init_mysql.sql")
    .withDatabaseName("library").withLogConsumer(new Slf4jLogConsumer(log));

    static {
        database.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
     
     
     
        registry.add("spring.datasource.password", database::getPassword);
    }
}
