package com.bridgelabz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	/**
	 * 
	 * @returns the Object of BCryptPasswordEncoder class
	 */
	
	@Bean
	public BCryptPasswordEncoder getpasswordEncryption() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 
	 * @returns the Object of ModelMapper class
	 */
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
