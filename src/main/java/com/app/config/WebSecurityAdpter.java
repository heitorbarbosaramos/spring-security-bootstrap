package com.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityAdpter extends WebSecurityConfigurerAdapter{


	private final String[] PUBLIC_MATCHES = {"/h2-novo/**", "/css/**", "/js/**", "/vendor/**"};
	
	@Autowired
	private ImplementsUser userDetailsService;
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(env.getActiveProfiles().toString().equals("test")) != null) {
			http.headers().frameOptions().disable();
		}
		
		http.csrf().disable()
		.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/", true).permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}

