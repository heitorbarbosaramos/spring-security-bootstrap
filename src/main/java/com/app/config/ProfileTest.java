package com.app.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTest {

	@Autowired
	private DbService dbService;
	
	private final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	@Bean
	public void TesteConfiguration() throws ParseException {
		
		LOG.info("PROFILE DE TESTE CARREGADO");
		Banner.ambienteTeste();
		
		dbService.dbInstatiationTest();

	}
}
