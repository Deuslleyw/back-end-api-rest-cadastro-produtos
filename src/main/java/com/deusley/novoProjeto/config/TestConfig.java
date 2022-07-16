package com.deusley.novoProjeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.deusley.novoProjeto.services.DBService;
import com.deusley.novoProjeto.services.EmailService;
import com.deusley.novoProjeto.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbsService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbsService.instantiateTestDatabase();
		return true;
	}
  
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
	
	//@Bean
	//public EmailService emailService() {                          //AtiveOServiçoDeEmal da Sua preferencia (SMTP)
		//return new SmtpEmailService();
	
	//}}
