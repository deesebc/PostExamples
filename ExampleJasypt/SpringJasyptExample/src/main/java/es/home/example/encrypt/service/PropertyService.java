package es.home.example.encrypt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
@PropertySource("classpath:configuration.properties")
public class PropertyService {

	@Value("${datasource.usr}")
	private String usr;

	@Value("${datasource.pwd}")
	private String pwd;

}
