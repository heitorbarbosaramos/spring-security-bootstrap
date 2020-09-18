package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.config.Banner;

@SpringBootApplication
public class SpringBootThymeleafLayoutApplication {
// template bootstrap - https://github.com/rahulmoundekar/spring-boot-thymeleaf-layout
	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafLayoutApplication.class, args);
		Banner.boasVindas();
		Banner.mostraIp();
		Banner.mostraMarca();
	}

}
