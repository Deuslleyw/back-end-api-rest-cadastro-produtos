package com.deusley.novoProjeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.deusley.novoProjeto.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbsService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbsService.instantiateTestDatabase();
		return true;
	}

}
