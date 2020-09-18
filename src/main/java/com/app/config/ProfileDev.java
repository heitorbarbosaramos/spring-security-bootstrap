package com.app.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ProfileDev {

	@Autowired
	private DbService dbService;
	
	private final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	@Value("${spring.jpa.properties.javax.persistence.schema-generation.scripts.action}")
	private String strategy;
	
	@Bean
	public void TesteConfiguration() throws ParseException {
		
		LOG.info("PROFILE DE DESENVOLVIMENTO CARREGANDO");
		LOG.info("STRATEGY: " + strategy);
		Banner.ambienteDesenvolvimento();
		
		if(strategy.equals("create-drop") ||  strategy.equals("create")) {
			LOG.info("BANCO DE DESENVOLVIMENTO CARREGANDO");
			dbService.dbInstatiationTest();
			LOG.info("BANCO DE DESENVOLVIMENTO CARREGADO");
		}

		LOG.info("PROFILE DE DESENVOLVIMENTO CARREGADO");
	}
}
