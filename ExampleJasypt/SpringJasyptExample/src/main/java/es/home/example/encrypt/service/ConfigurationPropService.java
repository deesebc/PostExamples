package es.home.example.encrypt.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "datasource")
@Configuration("configuration")
@Data
public class ConfigurationPropService {
	private String usr;
	private String pwd;
}
