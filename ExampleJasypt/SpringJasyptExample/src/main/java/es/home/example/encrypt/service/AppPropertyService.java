package es.home.example.encrypt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class AppPropertyService {

	@Value("${spring.datasource.username}")
	private String usr;

	@Value("${spring.datasource.password}")
	private String pwd;

}
